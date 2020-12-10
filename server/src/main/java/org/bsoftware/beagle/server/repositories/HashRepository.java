package org.bsoftware.beagle.server.repositories;

import org.bsoftware.beagle.server.entities.HashEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

/**
 * HashRepository is a repository which used for finding passwords and pushing hash data to database
 *
 * @author Rudolf Barbu
 * @version 1.0.0
 */
@Repository
public interface HashRepository extends JpaRepository<HashEntity, Long>
{
    /**
     * Finds password in database by fraction and hash
     *
     * @param fraction first 3 characters of hash
     * @param hash encrypted password
     * @return HashEntity or null if password in not present
     */
    HashEntity findHashEntityByFractionAndHash(String fraction, String hash);

    /**
     * Loads data to database using load infile
     *
     * @param filePath path to file with data
     */
    @Modifying
    @Transactional
    @Query(value = "LOAD DATA LOCAL INFILE :filePath INTO TABLE `hashes` FIELDS TERMINATED BY ','", nativeQuery = true)
    void loadDataLocalInfile(@Param("filePath") String filePath);
}