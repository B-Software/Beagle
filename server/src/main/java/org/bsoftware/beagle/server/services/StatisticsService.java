package org.bsoftware.beagle.server.services;

import org.bsoftware.beagle.server.dto.CheckStatisticsDto;
import org.bsoftware.beagle.server.dto.HashStatisticsDto;
import org.bsoftware.beagle.server.dto.StatisticsDto;
import org.bsoftware.beagle.server.dto.UserStatisticsDto;
import org.bsoftware.beagle.server.entities.StatisticsEntity;
import org.bsoftware.beagle.server.repositories.StatisticsRepository;
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
     * Autowired StatisticsRepository object
     * Used to communicate with database
     */
    @Autowired
    private StatisticsRepository statisticsRepository;

    /**
     * Gets hashes statistics
     *
     * @param statisticsEntity statistics from database
     * @return filled CheckStatisticsDto
     */
    private CheckStatisticsDto getCheckStatistics(StatisticsEntity statisticsEntity)
    {
        CheckStatisticsDto checkStatisticsDto = new CheckStatisticsDto();

        checkStatisticsDto.setTotal(statisticsEntity.getTotalUsers());

        int successfulChecksPercentage = (int) Math.round((((double) statisticsEntity.getSuccessfulChecks() / statisticsEntity.getTotalChecks()) * 100));
        checkStatisticsDto.setSuccessfulChecksPercentage(successfulChecksPercentage);

        return checkStatisticsDto;
    }

    /**
     * Gets hashes statistics
     *
     * @param statisticsEntity statistics from database
     * @return filled HashStatisticsDto
     */
    private HashStatisticsDto getHashStatistics(StatisticsEntity statisticsEntity)
    {
        HashStatisticsDto hashStatisticsDto = new HashStatisticsDto();

        hashStatisticsDto.setTotal(statisticsEntity.getTotalHashes());

        return hashStatisticsDto;
    }

    /**
     * Gets users statistics
     *
     * @param statisticsEntity statistics from database
     * @return filled UserStatisticsDto
     */
    private UserStatisticsDto getUserStatistics(StatisticsEntity statisticsEntity)
    {
        UserStatisticsDto userStatisticsDto = new UserStatisticsDto();

        userStatisticsDto.setTotal(statisticsEntity.getTotalUsers());

        int paidPercentage = (int) Math.round((((double) statisticsEntity.getPaidUsers() / statisticsEntity.getTotalUsers()) * 100));
        userStatisticsDto.setPaidPercentage(paidPercentage);

        return userStatisticsDto;
    }

    /**
     * Gets server statistics
     *
     * @return filled StatisticsDto object
     */
    public StatisticsDto getStatistics()
    {
        StatisticsDto statisticsDto = new StatisticsDto();

        StatisticsEntity statisticsEntity = statisticsRepository.getStatistics();

        statisticsDto.setCheckStatistics(getCheckStatistics(statisticsEntity));
        statisticsDto.setHashStatistics(getHashStatistics(statisticsEntity));
        statisticsDto.setUserStatistics(getUserStatistics(statisticsEntity));

        return statisticsDto;
    }
}