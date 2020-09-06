package org.bsoftware.beagle.server.controllers;

import org.bsoftware.beagle.server.components.wrappers.RestResponseEntityWrapper;
import org.bsoftware.beagle.server.services.implementation.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * PasswordController displays responses from rest API
 *
 * @author Rudolf Barbu
 * @version 1.0.0
 */
@RestController
@RequestMapping(value = "/api/password")
public class PasswordController
{
    /**
     * Autowired InfoService object
     * Used for getting information about server
     */
    private final PasswordService passwordService;

    /**
     * Autowired RestResponseEntityWrapper object
     * Used as response wrapper bean, which provides Json headers automatically
     */
    private final RestResponseEntityWrapper restResponseEntityWrapper;

    @GetMapping(value = "/{hash}")
    public ResponseEntity<?> getPassword(@PathVariable(value = "hash") String hash) throws Exception
    {
        return restResponseEntityWrapper.wrap(passwordService.get(hash));
    }

    /**
     * Used for autowiring necessary objects
     *
     * @param restResponseEntityWrapper autowired RestResponseEntityWrapper object
     * @param passwordService autowired passwordService object
     */
    @Autowired
    public PasswordController(PasswordService passwordService, RestResponseEntityWrapper restResponseEntityWrapper)
    {
        this.passwordService = passwordService;
        this.restResponseEntityWrapper = restResponseEntityWrapper;
    }
}