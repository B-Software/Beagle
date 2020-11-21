package org.bsoftware.beagle.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Beagle is a Spring Boot application class
 *
 * @author Rudolf Barbu
 * @version 1.0.0
 */
@EnableWebMvc
@SpringBootApplication
@EnableAutoConfiguration(exclude = ErrorMvcAutoConfiguration.class)
public class Beagle extends SpringBootServletInitializer
{
    /**
     * Constant for determine default application port, if property is not set
     */
    public static final int DEFAULT_PORT = 4000;

    /**
     * Entry point of Beagle application
     *
     * @param args Spring Boot application arguments
     */
    public static void main(String[] args)
    {
        ApplicationContext applicationContext = SpringApplication.run(Beagle.class, args);

        DispatcherServlet dispatcherServlet = (DispatcherServlet) applicationContext.getBean("dispatcherServlet");
        dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
    }
}