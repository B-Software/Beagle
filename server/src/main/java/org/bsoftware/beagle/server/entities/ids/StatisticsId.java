package org.bsoftware.beagle.server.entities.ids;

import java.io.Serializable;

/**
 * StatisticsId is a object which describes StatisticsEntity composite id
 *
 * @author Rudolf Barbu
 * @version 1.0.0
 */
public class StatisticsId implements Serializable
{
    /**
     * Total users field
     */
    private long totalUsers;

    /**
     * Paid users field
     */
    private long paidUsers;

    /**
     * Paid users field
     */
    private long totalHashes;

    /**
     * Total checks field
     */
    private long totalChecks;

    /**
     * Successful checks field
     */
    private long successfulChecks;
}