package org.bsoftware.beagle.server.components;

import org.bsoftware.beagle.server.repositories.StatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * UtilitiesComponent provides various functions, which are used in different classes
 * @author Rudolf Barbu
 * @version 1.0.2
 */
@Component
public class UtilitiesComponent
{
    /**
     * Autowired StatisticsRepository object
     * Used to communicate with database
     */
    @Autowired
    private StatisticsRepository statisticsRepository;

    
}