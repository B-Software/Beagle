package org.bsoftware.beagle.server.services;

import org.bsoftware.beagle.server.dto.StatisticsDto;
import org.bsoftware.beagle.server.repositories.HashRepository;
import org.bsoftware.beagle.server.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * StatisticsService is a service used for getting server statistics
 *
 * @author Rudolf Barbu
 * @version 1.0.0
 */
@Service
public class StatisticsService
{
    /**
     * Autowired HashRepository object
     * Used to communicate with database
     */
    private final HashRepository hashRepository;

    /**
     * Gets server statistics
     *
     * @return filled StatisticsDto object
     */
    public StatisticsDto getStatistics()
    {
        StatisticsDto statisticsDto = new StatisticsDto();

        statisticsDto.setHashesCount(hashRepository.count());

        return statisticsDto;
    }

    /**
     * Used for autowiring necessary objects
     *
     * @param hashRepository autowired HashRepository object
     */
    @Autowired
    public StatisticsService(HashRepository hashRepository)
    {
        this.hashRepository = hashRepository;
    }
}