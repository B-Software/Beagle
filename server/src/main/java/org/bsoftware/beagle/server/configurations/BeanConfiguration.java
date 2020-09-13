package org.bsoftware.beagle.server.configurations;

import org.apache.tika.Tika;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
     * @return Tika object as bean
     */
    @Bean
    public Tika tika()
    {
        return new Tika();
    }
}