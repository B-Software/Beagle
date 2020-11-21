package org.bsoftware.beagle.server.services;

import org.bsoftware.beagle.server.dto.ResponseDto;
import org.bsoftware.beagle.server.dto.UserDto;
import org.bsoftware.beagle.server.entities.AuthorityEntity;
import org.bsoftware.beagle.server.entities.UserEntity;
import org.bsoftware.beagle.server.exceptions.InvalidAuthenticationException;
import org.bsoftware.beagle.server.exceptions.UserAlreadyExistsException;
import org.bsoftware.beagle.server.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
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
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Collections;

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
     * Used for getting user information
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
    private UserEntity fillUserEntity(UserDto userDto)
    {
        UserEntity userEntity = new UserEntity();

        userEntity.setUsername(userDto.getUsername());
        userEntity.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        userEntity.setAvailableChecks(0L);
        userEntity.setAuthorities(Collections.singletonList(fillAuthorityEntity(userDto)));

        return userEntity;
    }

    /**
     * Preparing user's authorities
     *
     * @param userDto user credentials
     * @return completed AuthorityEntity
     */
    private AuthorityEntity fillAuthorityEntity(UserDto userDto)
    {
        AuthorityEntity authorityEntity = new AuthorityEntity();

        authorityEntity.setUsername(userDto.getUsername());
        authorityEntity.setAuthority("ROLE_USER");

        return authorityEntity;
    }

    /**
     * Get data about current user
     *
     * @return filled UserDto object
     */
    private UserDto fillUserDto(UserDto userDto)
    {
        UserEntity userEntity = userRepository.findUserEntityByUsername(userDto.getUsername());

        if (userEntity != null)
        {
            userDto.setAvailableChecks(userEntity.getAvailableChecks());
            userDto.setAuthorities(userEntity.getAuthorities().stream().map(AuthorityEntity::getAuthority).toArray(String[]::new));
        }

        return userDto;
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
        UserEntity userEntity = userRepository.findUserEntityByUsername(username);

        if (userEntity != null)
        {
            return User
                    .builder()
                    .username(userEntity.getUsername())
                    .password(userEntity.getPassword())
                    .authorities(userEntity.getAuthorities().stream().map(AuthorityEntity::getAuthority).toArray(String[]::new))
                    .build();
        }
        else
        {
            throw new UsernameNotFoundException("User not found");
        }
    }

    /**
     * Logout user
     *
     * @param httpServletRequest current request
     * @param httpServletResponse current response
     * @return ResponseDto with logout result
     */
    public ResponseDto getUser(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws InvalidAuthenticationException
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if ((authentication == null) || !authentication.isAuthenticated() || (authentication instanceof AnonymousAuthenticationToken))
        {
            throw new InvalidAuthenticationException();
        }

        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(httpServletRequest, httpServletResponse, authentication);

        return new ResponseDto("Successfully logout");
    }

    /**
     * Authenticating user
     *
     * @param userDto user credentials
     * @param httpServletRequest current request
     * @return UserDto with filled user
     */
    public UserDto postUser(UserDto userDto, HttpServletRequest httpServletRequest)
    {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword());
        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetails(httpServletRequest));

        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);

        HttpSession httpSession = httpServletRequest.getSession(true);
        httpSession.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);

        return fillUserDto(userDto);
    }

    /**
     * Registering user
     *
     * @param userDto user object
     * @return ResponseDto with registration result
     */
    public ResponseDto putUser(UserDto userDto) throws UserAlreadyExistsException
    {
        if (userRepository.findUserEntityByUsername(userDto.getUsername()) != null)
        {
            throw new UserAlreadyExistsException();
        }

        userRepository.save(fillUserEntity(userDto));
        return new ResponseDto("User successfully registered");
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