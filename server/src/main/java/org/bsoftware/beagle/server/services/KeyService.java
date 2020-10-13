package org.bsoftware.beagle.server.services;

import org.bsoftware.beagle.server.dto.KeyDto;
import org.bsoftware.beagle.server.dto.ResponseDto;
import org.bsoftware.beagle.server.entities.KeyEntity;
import org.bsoftware.beagle.server.entities.UserEntity;
import org.bsoftware.beagle.server.exceptions.KeyDoesNotExistsOrAlreadyActivatedException;
import org.bsoftware.beagle.server.repositories.KeyRepository;
import org.bsoftware.beagle.server.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * KeyService is a service used for adding and activates keys
 *
 * @author Rudolf Barbu
 * @version 1.0.0
 */
@Service
public class KeyService
{
    /**
     * Autowired KeyRepository object
     * Used for getting key information
     */
    private final KeyRepository keyRepository;

    /**
     * Autowired UserRepository object
     * Used for getting user information
     */
    private final UserRepository userRepository;

    /**
     * Fills key entity
     *
     * @param keyDto key data
     * @return filled key entity
     */
    private KeyEntity fillKeyEntity(KeyDto keyDto)
    {
        KeyEntity keyEntity = new KeyEntity();

        keyEntity.setKey(keyDto.getKey());
        keyEntity.setProvidedChecks(keyDto.getProvidedChecks());
        keyEntity.setActivated(false);

        return keyEntity;
    }

    /**
     * Activates key
     *
     * @param key key data
     */
    public ResponseDto getKey(String key) throws KeyDoesNotExistsOrAlreadyActivatedException
    {
        KeyEntity keyEntity = keyRepository.findKeyEntityByKey(key);

        if ((keyEntity != null) && !keyEntity.getActivated())
        {
            UserEntity userEntity = userRepository.findUserEntityByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

            keyEntity.setActivated(true);
            userEntity.setAvailableChecks(userEntity.getAvailableChecks() + keyEntity.getProvidedChecks());

            keyRepository.save(keyEntity);
            userRepository.save(userEntity);
        }
        else
        {
            throw new KeyDoesNotExistsOrAlreadyActivatedException();
        }

        return new ResponseDto("Key activated successfully");
    }

    /**
     * Adds keys to database
     *
     * @param keyDtoList key data
     */
    public ResponseDto putKey(List<KeyDto> keyDtoList)
    {
        keyDtoList.forEach(keyDto ->
        {
            if (keyRepository.findKeyEntityByKey(keyDto.getKey()) != null)
            {
                return;
            }

            keyRepository.save(fillKeyEntity(keyDto));
        });

        return new ResponseDto("Keys added successfully");
    }

    /**
     * Used for autowiring necessary objects
     *
     * @param keyRepository autowired KeyRepository object
     * @param userRepository autowired UserRepository object
     */
    @Autowired
    public KeyService(KeyRepository keyRepository, UserRepository userRepository)
    {
        this.keyRepository = keyRepository;
        this.userRepository = userRepository;
    }
}