package org.bsoftware.beagle.server.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * StatisticsDto is a values container for presenting server statistics
 *
 * @author Rudolf Barbu
 * @version 1.0.0
 */
@Getter
@Setter
public class StatisticsDto
{
    /**
     * Check statistics dto field
     */
    private CheckStatisticsDto checkStatistics;

    /**
     * Hash statistics dto field
     */
    private HashStatisticsDto hashStatistics;

    /**
     * User statistics dto field
     */
    private UserStatisticsDto userStatistics;
}