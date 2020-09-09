package org.bsoftware.beagle.server.services;

import org.bsoftware.beagle.server.dto.Dto;
import org.springframework.web.multipart.MultipartFile;

/**
 * Service interface makes to all services return dto object
 *
 * @author Rudolf Barbu
 * @version 1.0.0
 */
public interface Service
{
    /**
     * Get info from the service
     *
     * @param <T> generic type, which extends Dto class
     * @return Dto object
     */
    default <T extends Dto> T get()
    {
        return null;
    }

    /**
     * Get info from the service, for String parameter
     *
     * @param parameter String type parameter
     * @param <T> generic type, which extends Dto class
     * @return Dto object
     */
    default <T extends Dto> T get(String parameter)
    {
        return null;
    }

    /**
     * Uploads MultipartFile to server and get the response
     *
     * @param multipartFile data to post
     * @param <T> generic type, which extends Dto class
     * @return Dto object
     */
    default <T extends Dto> T post(MultipartFile multipartFile) throws Exception
    {
        return null;
    }
}