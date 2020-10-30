package org.bsoftware.beagle.server.entities;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

/**
 * KeyEntity is a data model which describes purchasable keys
 *
 * @author Rudolf Barbu
 * @version 1.0.0
 */
@Getter
@Setter
@Entity
@Table(name = "keys")
public class KeyEntity
{
    /**
     * Id field
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Key field
     */
    @Column(name = "key", length = 32, nullable = false, unique = true)
    private String key;

    /**
     * Provided checks field
     */
    @Column(name = "provided_checks", nullable = false)
    private Long providedChecks;

    /**
     * Key is activated field
     */
    @Column(name = "activated", nullable = false)
    private Boolean activated;
}