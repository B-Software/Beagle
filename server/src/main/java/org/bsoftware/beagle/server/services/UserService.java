package org.bsoftware.beagle.server.services;

import org.bsoftware.beagle.server.assets.ResponseEntityWrapperAsset;
import org.bsoftware.beagle.server.dto.implementation.ResponseDto;
import org.bsoftware.beagle.server.dto.implementation.UserDto;
import org.bsoftware.beagle.server.entities.AuthorityEntity;
import org.bsoftware.beagle.server.entities.UserEntity;
import org.bsoftware.beagle.server.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * UserService is a service used for authentication and registration
 *
 * @author Rudolf Barbu
 * @version 1.0.0
 */
@Service
public class UserService implements UserDetailsService
{
    /**
     * Autowired UserRepository object
     * Used for getting information about user
     */
    private final UserRepository userRepository;

    /**
     * Autowired AuthenticationManager object
     * Used for user authentication
     */
    private final AuthenticationManager authenticationManager;

    /**
     * Autowired BCryptPasswordEncoder object
     * Used for password encryption
     */
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * Preparing user entity for registration
     *
     * @param userDto user credentials
     * @return completed UserEntity
     */
    private UserEntity getUserEntity(UserDto userDto)
    {
        UserEntity userEntity = new UserEntity();

        userEntity.setUsername(userDto.getUsername());
        userEntity.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        userEntity.setAvailableChecks(0L);
        userEntity.setAuthorities(Collections.singletonList(getAuthorityEntity(userDto)));

        return userEntity;
    }

    /**
     * Preparing user's authorities
     *
     * @param userDto user credentials
     * @return completed AuthorityEntity
     */
    private AuthorityEntity getAuthorityEntity(UserDto userDto)
    {
        AuthorityEntity authorityEntity = new AuthorityEntity();

        authorityEntity.setUsername(userDto.getUsername());
        authorityEntity.setAuthority("USER");

        return authorityEntity;
    }

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
                    .authorities(userEntityOptional.get().getAuthorities().stream().map(AuthorityEntity::getAuthority).toArray(String[]::new))
                    .build();
        }
        else
        {
            throw new UsernameNotFoundException("User not found");
        }
    }

    /**
     * Authenticating user
     *
     * @param userDto user credentials
     * @param httpServletRequest current request
     * @return ResponseEntityWrapperAsset with authentication result
     */
    public ResponseEntityWrapperAsset<?> postUser(UserDto userDto, HttpServletRequest httpServletRequest)
    {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword());
        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetails(httpServletRequest));

        try
        {
            Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(authentication);

            HttpSession httpSession = httpServletRequest.getSession(true);
            httpSession.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);

            return new ResponseEntityWrapperAsset<>(new ResponseDto("Authenticated"), HttpStatus.OK);
        }
        catch (BadCredentialsException exception)
        {
            return new ResponseEntityWrapperAsset<>(new ResponseDto(exception.getMessage()), HttpStatus.FORBIDDEN);
        }
    }

    /**
     * Registering user
     *
     * @param userDto user object
     * @return ResponseEntityWrapperAsset with registration result
     */
    public ResponseEntityWrapperAsset<?> putUser(UserDto userDto)
    {
        if (userRepository.findUserEntityByUsername(userDto.getUsername()).isPresent())
        {
            return new ResponseEntityWrapperAsset<>(new ResponseDto("User with this username already exists"), HttpStatus.CONFLICT);
        }

        userRepository.save(getUserEntity(userDto));

        return new ResponseEntityWrapperAsset<>(new ResponseDto("User successfully registered"), HttpStatus.CREATED);
    }

    /**
     * Used for autowiring necessary objects
     *
     * @param userRepository autowired UserRepository object
     * @param authenticationManager autowired AuthenticationManager object
     * @param bCryptPasswordEncoder autowired BCryptPasswordEncoder object
     */
    @Autowired
    public UserService(UserRepository userRepository, @Lazy AuthenticationManager authenticationManager, BCryptPasswordEncoder bCryptPasswordEncoder)
    {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
}