package org.bsoftware.beagle.server.entities;

import lombok.Getter;
import lombok.Setter;
import org.bsoftware.beagle.server.entities.ids.StatisticsId;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import javax.persistence.*;

/**
 * StatisticsEntity is a data model which describes statistics table
 *
 * @author Rudolf Barbu
 * @version 1.0.0
 */
@Getter
@Setter
@Entity
@DynamicInsert
@Table(name = "statistics")
@IdClass(value = StatisticsId.class)
public class StatisticsEntity
{
    /**
     * Total users field
     */
    @Id
    @ColumnDefault(value = "0")
    @Column(name = "total_users")
    private long totalUsers;

    /**
     * Paid users field
     */
    @Id
    @ColumnDefault(value = "0")
    @Column(name = "paid_users")
    private long paidUsers;

    /**
     * Paid users field
     */
    @Id
    @ColumnDefault(value = "0")
    @Column(name = "total_hashes")
    private long totalHashes;

    /**
     * Total checks field
     */
    @Id
    @ColumnDefault(value = "0")
    @Column(name = "total_checks")
    private long totalChecks;

    /**
     * Successful checks field
     */
    @Id
    @ColumnDefault(value = "0")
    @Column(name = "successful_checks")
    private long successfulChecks;
}