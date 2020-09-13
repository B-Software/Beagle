package org.bsoftware.beagle.server.services;

import org.bsoftware.beagle.server.assets.ResponseEntityWrapperAsset;
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
     * @return Dto object
     */
    default ResponseEntityWrapperAsset<?> get()
    {
        return null;
    }

    /**
     * Get info from the service, for String parameter
     *
     * @param parameter String type parameter
     * @return Dto object
     */
    default ResponseEntityWrapperAsset<?> get(String parameter)
    {
        return null;
    }

    /**
     * Uploads MultipartFile to server and get the response
     *
     * @param multipartFile data to post
     * @return Dto object
     */
    default ResponseEntityWrapperAsset<?> post(MultipartFile multipartFile) throws Exception
    {
        return null;
    }
}