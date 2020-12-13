package org.bsoftware.beagle.server;

import lombok.Getter;
import org.bsoftware.beagle.server.entities.StatisticsEntity;
import org.bsoftware.beagle.server.repositories.StatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import javax.annotation.PostConstruct;

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
     * Holder for determine first launch of application
     */
    private static boolean isFirstLaunch;

    /**
     * Autowired StatisticsRepository object
     * Used to communicate with database
     */
    @Autowired
    private StatisticsRepository statisticsRepository;

    /**
     * Initializes statistics table
     */
    @PostConstruct
    private void initializeStatisticsTable()
    {
        if (statisticsRepository.findAll().size() == 0)
        {
            statisticsRepository.save(new StatisticsEntity());
        }
    }

    /**
     * Entry point of Beagle application
     *
     * @param args Spring Boot application arguments
     */
    public static void main(String[] args)
    {
        isFirstLaunch = true;
        ApplicationContext applicationContext = SpringApplication.run(Beagle.class, args);

        DispatcherServlet dispatcherServlet = (DispatcherServlet) applicationContext.getBean("dispatcherServlet");
        dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
    }
}