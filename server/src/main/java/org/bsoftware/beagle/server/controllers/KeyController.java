package org.bsoftware.beagle.server.controllers;

import org.bsoftware.beagle.server.dto.KeyDto;
import org.bsoftware.beagle.server.exceptions.KeyDoesNotExistsOrAlreadyActivatedException;
import org.bsoftware.beagle.server.services.KeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * KeyController displays responses from rest API
 *
 * @author Rudolf Barbu
 * @version 1.0.0
 */
@Validated
@RestController
@RequestMapping(value = "/api/key")
public class KeyController
{
    /**
     * Autowired HashService object
     * Used for working with keys
     */
    @Autowired
    private KeyService keyService;

    /**
     * Activates key
     *
     * @param key key to activate
     * @return ResponseEntity to servlet
     */
    @GetMapping(value = "/{key}")
    public ResponseEntity<?> getKey(@PathVariable(value = "key") @Size(min = 32, max = 32) String key) throws KeyDoesNotExistsOrAlreadyActivatedException
    {
        return new ResponseEntity<>(keyService.getKey(key), HttpStatus.OK);
    }

    /**
     * Adds activating keys to database
     *
     * @param keyDtoList list of keys
     * @return ResponseEntity to servlet
     */
    @PutMapping
    public ResponseEntity<?> putKey(@RequestBody @Valid List<KeyDto> keyDtoList)
    {
        return new ResponseEntity<>(keyService.putKey(keyDtoList), HttpStatus.CREATED);
    }
}