package org.bsoftware.beagle.server.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * UserStatisticsDto is a values container for presenting users statistics
 *
 * @author Rudolf Barbu
 * @version 1.0.0
 */
@Getter
@Setter
public class UserStatisticsDto
{
    /**
     * Total users field
     */
    private long total;

    /**
     * Paid percentage field
     */
    private int paidPercentage;
}