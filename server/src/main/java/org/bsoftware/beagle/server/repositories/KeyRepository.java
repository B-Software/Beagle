package org.bsoftware.beagle.server.repositories;

import org.bsoftware.beagle.server.entities.KeyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * KeyRepository is a repository which used for finding activation keys
 *
 * @author Rudolf Barbu
 * @version 1.0.0
 */
@Repository
public interface KeyRepository extends JpaRepository<KeyEntity, Long>
{
    /**
     * Finds KeyEntity by key
     *
     * @param key string parameter
     * @return KeyEntity if key was found
     */
    KeyEntity findKeyEntityByKey(String key);
}