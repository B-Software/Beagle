package org.bsoftware.beagle.server.components;

import org.bsoftware.beagle.server.Beagle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * ServletComponent used for application port changing
 * @author Rudolf Barbu
 * @version 1.0.3
 */
@Component
public class ServletComponent implements WebServerFactoryCustomizer<TomcatServletWebServerFactory>
{
    /**
     * Autowired Environment object
     * Used for get values from property file
     */
    private final Environment environment;

    /**
     * Customizes port of application
     *
     * @param tomcatServletWebServerFactory servlet factory
     */
    @Override
    public void customize(TomcatServletWebServerFactory tomcatServletWebServerFactory)
    {
        String applicationPort = environment.getProperty("application.port");

        tomcatServletWebServerFactory
                .setPort((applicationPort != null && !applicationPort.isEmpty()) ? Integer.parseInt(applicationPort) : Beagle.DEFAULT_PORT);
    }

    /**
     * Used for autowiring necessary objects
     *
     * @param environment autowired Environment object
     */
    @Autowired
    public ServletComponent(Environment environment)
    {
        this.environment = environment;
    }
}