package org.bsoftware.beagle.server.services;

import org.bsoftware.beagle.server.dto.Dto;

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
     * @throws Exception unexpected exception, which will forwarded to controller
     */
    default <T extends Dto> T get() throws Exception
    {
        return null;
    }

    /**
     * Get info from the service, for corresponding parameter
     *
     * @param <T> generic type, which extends Dto class
     * @param parameter String parameter
     * @return Dto object
     * @throws Exception unexpected exception, which will forwarded to controller
     */
    default <T extends Dto> T get(String parameter) throws Exception
    {
        return null;
    }

    /**
     * Post data to service and get the response
     *
     * @param dto data to post
     * @param <T> generic type, which extends Dto class
     * @param <K> generic type, which extends Dto class
     * @return @return Dto object
     * @throws Exception unexpected exception, which will forwarded to controller
     */
    default <T extends Dto, K extends Dto> K post(T dto) throws Exception
    {
        return null;
    }
}