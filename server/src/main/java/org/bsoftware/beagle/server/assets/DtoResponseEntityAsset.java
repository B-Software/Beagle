package org.bsoftware.beagle.server.assets;

import org.bsoftware.beagle.server.dto.Dto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * DtoResponseEntityAsset provides that body will extend dto objects
 *
 * @author Rudolf Barbu
 * @version 1.0.0
 */
public class DtoResponseEntityAsset<T extends Dto> extends ResponseEntity<T>
{
    /**
     * Creates ResponseEntity with body witch extends Dto
     *
     * @param body dto object
     * @param httpStatus status of request
     */
    public DtoResponseEntityAsset(T body, HttpStatus httpStatus)
    {
        super(body, httpStatus);
    }
}