package org.bsoftware.beagle.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Beagle is a Spring Boot application class
 *
 * @author Rudolf Barbu
 * @version 1.0.0
 */
@SpringBootApplication
public class Beagle extends SpringBootServletInitializer
{
    /**
     * Entry point of Beagle application
     *
     * @param args Spring Boot application arguments
     */
    public static void main(String[] args)
    {
        SpringApplication.run(Beagle.class, args);
    }
}