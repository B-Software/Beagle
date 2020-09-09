package org.bsoftware.beagle.server.repositories;

import org.bsoftware.beagle.server.entities.implementation.HashEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

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
     * Counts all rows in table
     *
     * @return cows count
     */
    long count();

    /**
     * Finds password in database by trio and hash
     *
     * @param fraction first 3 characters of hash
     * @param hash encrypted password
     * @return Optional<HashEntity> which may contain decrypted password
     */
    Optional<HashEntity> findHashEntityByFractionAndHash(String fraction, String hash);
}