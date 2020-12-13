package org.bsoftware.beagle.server.repositories;

import org.bsoftware.beagle.server.entities.StatisticsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * StatisticsRepository is a repository which used store and view application statistics
 *
 * @author Rudolf Barbu
 * @version 1.0.0
 */
@Repository
public interface StatisticsRepository extends JpaRepository<StatisticsEntity, Long>
{
    /**
     * Gets server statistics
     *
     * @return StatisticsEntity object with statistics
     */
    @Query(value = "SELECT * FROM `statistics` LIMIT 1", nativeQuery = true)
    StatisticsEntity getStatistics();
}