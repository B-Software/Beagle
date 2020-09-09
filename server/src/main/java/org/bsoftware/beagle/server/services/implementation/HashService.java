package org.bsoftware.beagle.server.services.implementation;

import org.apache.tika.Tika;
import org.bsoftware.beagle.server.dto.Dto;
import org.bsoftware.beagle.server.dto.implementation.CountDto;
import org.bsoftware.beagle.server.dto.implementation.PasswordDto;
import org.bsoftware.beagle.server.dto.implementation.ResponseDto;
import org.bsoftware.beagle.server.entities.implementation.HashEntity;
import org.bsoftware.beagle.server.repositories.HashRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.xml.bind.DatatypeConverter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
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

        return hashEntityOptional.map(HashEntity::getPassword).orElse(null);
    }

    /**
     * Reads uploaded file and adds new rows in database
     *
     * @param multipartFile uploaded file
     */
    private void addHashesToDatabase(MultipartFile multipartFile) throws IOException, NoSuchAlgorithmException
    {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(multipartFile.getInputStream()));
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        List<HashEntity> hashEntityList = new ArrayList<>();

        bufferedReader.lines().forEach(password ->
        {
            if (password.length() > 16 || password.contains(" "))
            {
                return;
            }

            String hash = DatatypeConverter.printHexBinary(messageDigest.digest(password.getBytes())).toLowerCase();
            HashEntity hashEntity = new HashEntity();

            hashEntity.setFraction(hash.substring(0, 3));
            hashEntity.setHash(hash);
            hashEntity.setPassword(password);

            hashEntityList.add(hashEntity);
        });

        hashRepository.saveAll(hashEntityList);
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
     * @param parameter String type parameter
     * @param <T> generic type, which extends Dto class
     * @return HashDto witch may contain password
     */
    @Override
    public <T extends Dto> T get(String parameter)
    {
        PasswordDto passwordDto = new PasswordDto();

        passwordDto.setPassword(getPassword(parameter.toLowerCase()));

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
                // Todo: Add here exception
            }
        }
        else
        {
            // Todo: Add here exception
        }

        return (T) new ResponseDto("File uploaded successfully");
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