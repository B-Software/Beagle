package org.bsoftware.beagle.server.repositories;

import org.bsoftware.beagle.server.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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
     * @return UserEntity if it was found
     */
    UserEntity findUserEntityByUsername(String username);
}