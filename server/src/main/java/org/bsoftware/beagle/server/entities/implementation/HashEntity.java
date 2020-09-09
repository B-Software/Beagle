package org.bsoftware.beagle.server.entities.implementation;

import lombok.Getter;
import lombok.Setter;
import org.bsoftware.beagle.server.entities.ids.HashId;
import javax.persistence.*;

/**
 * HashEntity is a data model which describes hashes table
 *
 * @author Rudolf Barbu
 * @version 1.0.0
 */
@Getter
@Setter
@Entity
@Table(name = "hashes")
@IdClass(value = HashId.class)
public class HashEntity implements org.bsoftware.beagle.server.entities.Entity
{
    /**
     * Trio field
     */
    @Id
    @Column(name = "fraction", length = 3)
    private String fraction;

    /**
     * Hash field
     */
    @Id
    @Column(name = "hash", length = 32)
    private String hash;

    /**
     * Password field
     */
    @Column(name = "password", length = 16)
    private String password;
}