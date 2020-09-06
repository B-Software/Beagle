package org.bsoftware.beagle.server.configurations;

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
     * @return HttpHeaders object, which already provides Json headers
     */
    @Bean
    public HttpHeaders httpHeaders()
    {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        return httpHeaders;
    }
}