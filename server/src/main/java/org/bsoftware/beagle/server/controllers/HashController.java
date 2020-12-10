package org.bsoftware.beagle.server.controllers;

import org.bsoftware.beagle.server.exceptions.InsufficientCheckAmountException;
import org.bsoftware.beagle.server.services.HashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.constraints.Size;

/**
 * HashController displays responses from rest API
 *
 * @author Rudolf Barbu
 * @version 1.0.0
 */
@Validated
@RestController
@RequestMapping(value = "/api/hash")
public class HashController
{
    /**
     * Autowired HashService object
     * Used for working with hashes
     */
    @Autowired
    private HashService hashService;

    /**
     * Searches for password, by hash specified
     *
     * @param hash hash to find password
     * @return ResponseEntity to servlet
     */
    @GetMapping(value = "/{hash}")
    public ResponseEntity<?> getHash(@PathVariable(value = "hash") @Size(min = 32, max = 32) String hash) throws InsufficientCheckAmountException
    {
        return new ResponseEntity<>(hashService.getHash(hash), HttpStatus.OK);
    }

    /**
     * Uploads file to server and transfers if to service
     *
     * @param file uploading file
     * @return ResponseEntity to servlet
     */
    @PutMapping
    public ResponseEntity<?> putHash(@RequestParam(value = "file") MultipartFile file) throws Exception
    {
        return new ResponseEntity<>(hashService.putHash(file), HttpStatus.OK);
    }
}