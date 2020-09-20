package org.bsoftware.beagle.server.repositories;

import org.bsoftware.beagle.server.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * UserRepository is a repository which used for finding and pushing user data in database
 *
 * @author Rudolf Barbu
 * @version 1.0.0
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>
{
    /**
     * Finds UserEntity for given username
     *
     * @param username string parameter
     * @return Optional which may contain UserEntity
     */
    Optional<UserEntity> findUserEntityByUsername(String username);
}