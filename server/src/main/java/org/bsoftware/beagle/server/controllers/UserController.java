package org.bsoftware.beagle.server.controllers;

import org.bsoftware.beagle.server.dto.UserDto;
import org.bsoftware.beagle.server.exceptions.InvalidAuthenticationException;
import org.bsoftware.beagle.server.exceptions.UserAlreadyExistsException;
import org.bsoftware.beagle.server.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * UserController displays responses from rest API
 *
 * @author Rudolf Barbu
 * @version 1.0.0
 */
@RestController
@RequestMapping(value = "/api/user")
public class UserController
{
    /**
     * Autowired UserService object
     * Used for working with user data
     */
    private final UserService userService;

    /**
     * Logout user
     *
     * @param httpServletRequest current request
     * @param httpServletResponse current response
     * @return ResponseEntity to servlet
     */
    @GetMapping
    public ResponseEntity<?> getUser(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws InvalidAuthenticationException
    {
        return new ResponseEntity<>(userService.getUser(httpServletRequest, httpServletResponse), HttpStatus.OK);
    }

    /**
     * Authenticating user
     *
     * @param userDto user credentials
     * @param httpServletRequest current request
     * @return ResponseEntity to servlet
     */
    @PostMapping
    public ResponseEntity<?> postUser(@RequestBody @Valid UserDto userDto, HttpServletRequest httpServletRequest)
    {
        return new ResponseEntity<>(userService.postUser(userDto, httpServletRequest), HttpStatus.OK);
    }

    /**
     * Registering user
     *
     * @param userDto user credentials
     * @return ResponseEntity to servlet
     */
    @PutMapping
    public ResponseEntity<?> putUser(@RequestBody @Valid UserDto userDto) throws UserAlreadyExistsException
    {
        return new ResponseEntity<>(userService.putUser(userDto), HttpStatus.CREATED);
    }

    /**
     * Used for autowiring necessary objects
     *
     * @param userService autowired UserService object
     */
    @Autowired
    public UserController(UserService userService)
    {
        this.userService = userService;
    }
}