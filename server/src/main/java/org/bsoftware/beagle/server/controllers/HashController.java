package org.bsoftware.beagle.server.controllers;

import org.bsoftware.beagle.server.services.implementation.HashService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final HashService hashService;

    /**
     * Searches for password if hash specified, else shows hashes row count
     *
     * @param hash hash to find password
     * @return ResponseEntity to servlet
     */
    @GetMapping(value = {"", "/{hash}"})
    public ResponseEntity<?> getHash(@PathVariable(value = "hash", required = false) @Size(min = 32, max = 32) String hash)
    {
        if (hash != null)
        {
            return hashService.get(hash).wrap();
        }
        else
        {
            return hashService.get().wrap();
        }
    }

    /**
     * Uploads file to server and transfers if to service
     *
     * @param file uploading file
     * @return ResponseEntity to servlet
     */
    @PostMapping
    public ResponseEntity<?> postHash(@RequestParam(value = "file") MultipartFile file) throws Exception
    {
        return hashService.post(file).wrap();
    }

    /**
     * Used for autowiring necessary objects
     *
     * @param hashService autowired passwordService object
     */
    @Autowired
    public HashController(HashService hashService)
    {
        this.hashService = hashService;
    }
}