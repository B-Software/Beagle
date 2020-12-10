package org.bsoftware.beagle.server.services;

import org.bsoftware.beagle.server.dto.HashStatisticsDto;
import org.bsoftware.beagle.server.dto.StatisticsDto;
import org.bsoftware.beagle.server.dto.UserStatisticsDto;
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
    @Autowired
    private HashRepository hashRepository;

    /**
     * Autowired UserRepository object
     * Used to communicate with database
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * Gets hashes statistics
     *
     * @return filled HashStatisticsDto
     */
    private HashStatisticsDto getHashStatistics()
    {
        HashStatisticsDto hashStatisticsDto = new HashStatisticsDto();

        hashStatisticsDto.setTotal(hashRepository.count());

        return hashStatisticsDto;
    }

    /**
     * Gets users statistics
     *
     * @return filled UserStatisticsDto
     */
    private UserStatisticsDto getUserStatistics()
    {
        UserStatisticsDto userStatisticsDto = new UserStatisticsDto();

        userStatisticsDto.setTotal(userRepository.count());

        int paidPercentage = (int) Math.round((((double) userRepository.countPaidUsers() / userRepository.count()) * 100));
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

        statisticsDto.setHashStatistics(getHashStatistics());
        statisticsDto.setUserStatistics(getUserStatistics());

        return statisticsDto;
    }
}