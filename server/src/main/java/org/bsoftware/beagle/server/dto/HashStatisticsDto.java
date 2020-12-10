package org.bsoftware.beagle.server.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * HashStatisticsDto is a values container for presenting hashes statistics
 *
 * @author Rudolf Barbu
 * @version 1.0.0
 */
@Getter
@Setter
public class HashStatisticsDto
{
    /**
     * Total Hashes field
     */
    private long total;
}