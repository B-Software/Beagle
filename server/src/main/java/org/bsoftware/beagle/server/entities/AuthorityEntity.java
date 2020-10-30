package org.bsoftware.beagle.server.entities;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

/**
 * AuthorityEntity is a data model which describes authorities table
 *
 * @author Rudolf Barbu
 * @version 1.0.0
 */
@Getter
@Setter
@Entity
@Table(name = "authorities")
public class AuthorityEntity
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
    @Column(name = "username", length = 6, nullable = false)
    private String username;

    /**
     * Authority field
     */
    @Column(name = "authority", length = 16, nullable = false)
    private String authority;
}