package org.bsoftware.beagle.server.services.implementation;

import org.apache.tika.Tika;
import org.bsoftware.beagle.server.dto.Dto;
import org.bsoftware.beagle.server.dto.implementation.CountDto;
import org.bsoftware.beagle.server.dto.implementation.PasswordDto;
import org.bsoftware.beagle.server.dto.implementation.ResponseDto;
import org.bsoftware.beagle.server.entities.implementation.HashEntity;
import org.bsoftware.beagle.server.exceptions.WrongFileExtensionException;
import org.bsoftware.beagle.server.repositories.HashRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

/**
 * HashService provides information about password, and provides adding mechanisms to database
 *
 * @author Rudolf Barbu
 * @version 1.0.0
 */
@Service
@SuppressWarnings(value = "unchecked")
public class HashService implements org.bsoftware.beagle.server.services.Service
{
    /**
     * Autowired HashRepository object
     * Used to communicate with database
     */
    private final HashRepository hashRepository;

    /**
     * Autowired Tika object
     * Used to determine uploaded file extension
     */
    private final Tika tika;

    /**
     * Returns password from database
     *
     * @param hash hash to get password
     * @return password from database, or null
     */
    private String getPassword(String hash)
    {
        Optional<HashEntity> hashEntityOptional = hashRepository.findHashEntityByFractionAndHash(hash.substring(0, 3), hash);

        return hashEntityOptional.map(hashEntity -> hashEntity.getPassword().substring(0, hashEntity.getPassword().length() - 1)).orElse(null);
    }

    /**
     * Reads uploaded file and adds new rows in database
     *
     * @param multipartFile uploaded file
     */
    @SuppressWarnings(value = "ResultOfMethodCallIgnored")
    private void addHashesToDatabase(MultipartFile multipartFile) throws IOException, NoSuchAlgorithmException
    {
        File tempFile = File.createTempFile("temp", ".txt");
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(multipartFile.getInputStream()));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(tempFile));

        for (String password : (Iterable<String>) bufferedReader.lines()::iterator)
        {
            if ((password.length() < 6 || password.length() > 16) || (password.contains(" ") || password.matches(".*[,;:].*")))
            {
                continue;
            }
            String hash = DatatypeConverter.printHexBinary(messageDigest.digest(password.getBytes())).toLowerCase();

            bufferedWriter.write(hash.substring(0, 3) + "," + hash + "," + password);
            bufferedWriter.newLine();
        }

        hashRepository.loadDataLocalInfile(tempFile.getAbsolutePath());
        tempFile.delete();
    }

    /**
     * Get hashes count from table
     *
     * @param <T> generic type, which extends Dto class
     * @return CountDto which contain hashes count
     */
    @Override
    public <T extends Dto> T get()
    {
        CountDto countDto = new CountDto();

        countDto.setHashesCount(hashRepository.count());

        return (T) countDto;
    }

    /**
     * Trying to retrieve password from database
     *
     * @param hash String type parameter
     * @param <T> generic type, which extends Dto class
     * @return HashDto witch may contain password
     */
    @Override
    public <T extends Dto> T get(String hash)
    {
        PasswordDto passwordDto = new PasswordDto();

        passwordDto.setPassword(getPassword(hash.toLowerCase()));

        return (T) passwordDto;
    }

    /**
     * Reads file and saves all passwords from it to the database
     *
     * @param multipartFile data to post
     * @param <T> generic type, which extends Dto class
     * @return ResponseDto then file is valid and loaded
     */
    @Override
    public <T extends Dto> T post(MultipartFile multipartFile) throws Exception
    {
        if (!multipartFile.isEmpty())
        {
            if (tika.detect(multipartFile.getBytes()).equals("text/plain"))
            {
                addHashesToDatabase(multipartFile);
            }
            else
            {
                throw new WrongFileExtensionException(multipartFile.getBytes(), tika);
            }
        }

        return (T) new ResponseDto("File processed successfully");
    }

    /**
     * Used for autowiring necessary objects
     *
     * @param hashRepository autowired HashRepository object
     * @param tika autowired Tika object
     */
    @Autowired
    public HashService(HashRepository hashRepository, Tika tika)
    {
        this.hashRepository = hashRepository;
        this.tika = tika;
    }
}