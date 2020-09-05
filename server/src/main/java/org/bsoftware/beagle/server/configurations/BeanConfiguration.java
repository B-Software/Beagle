package org.bsoftware.beagle.server.configurations;

import org.bsoftware.beagle.server.components.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

/**
 * BeanConfiguration provides bean configuration for classes, which are not components
 *
 * @author Rudolf Barbu
 * @version 1.0.0
 */
@Configuration
public class BeanConfiguration
{
    /**
     * Autowired Utilities object
     * Used for various utility functions
     */
    private final Utilities utilities;

    /**
     * @return HttpHeaders object, which already provides Json headers
     */
    @Bean
    public HttpHeaders httpHeaders()
    {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        return httpHeaders;
    }

    /**
     * Used for autowiring necessary objects
     *
     * @param utilities autowired Utilities object
     */
    @Autowired
    public BeanConfiguration(Utilities utilities)
    {
        this.utilities = utilities;
    }
}