package org.bsoftware.beagle.server.entities;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;

/**
 * UserEntity is a data model which describes users table
 *
 * @author Rudolf Barbu
 * @version 1.0.0
 */
@Getter
@Setter
@Entity
@Table(name = "users")
public class UserEntity
{
    /**
     * Id field
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Username field
     */
    @Column(name = "username", length = 8, nullable = false, unique = true)
    private String username;

    /**
     * Password field
     */
    @Column(name = "password", length = 60, nullable = false)
    private String password;

    /**
     * Field that displays available checks
     */
    @Column(name = "available_checks", nullable = false)
    private Long availableChecks;

    /**
     * Authority field
     */
    @Column(name = "authority", length = 16, nullable = false)
    private String authority;
}