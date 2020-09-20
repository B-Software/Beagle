package org.bsoftware.beagle.server.services.implementation;

import org.bsoftware.beagle.server.entities.UserEntity;
import org.bsoftware.beagle.server.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;

/**
 * UserService is a service used for authentication and registration
 *
 * @author Rudolf Barbu
 * @version 1.0.0
 */
@Service
public class UserService implements org.bsoftware.beagle.server.services.Service, UserDetailsService
{
    /**
     * Autowired UserRepository object
     * Used for getting information about user
     */
    private final UserRepository userRepository;

    /**
     * Gets UserEntity from database and fills UserDetails object
     *
     * @param username string passed to UserDetailsService
     * @return filled UserDetails object
     * @throws UsernameNotFoundException if user not found in database
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        Optional<UserEntity> userEntityOptional = userRepository.findUserEntityByUsername(username);

        if (userEntityOptional.isPresent())
        {
            return User
                    .builder()
                    .username(userEntityOptional.get().getUsername())
                    .password(userEntityOptional.get().getPassword())
                    .authorities((String[]) userEntityOptional.get().getAuthorities().toArray())
                    .build();
        }
        else
        {
            throw new UsernameNotFoundException("User not found");
        }
    }

    /**
     * Used for autowiring necessary objects
     *
     * @param userRepository autowired UserRepository object
     */
    @Autowired
    public UserService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }
}