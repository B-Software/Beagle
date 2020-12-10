package org.bsoftware.beagle.server.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
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
@DynamicInsert
@Table(name = "authorities")
public class AuthorityEntity
{
    /**
     * Id field
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    /**
     * Username field
     */
    @Column(name = "username", length = 6, nullable = false)
    private String username;

    /**
     * Authority field
     */
    @ColumnDefault(value = "'ROLE_USER'")
    @Column(name = "authority", length = 16, nullable = false)
    private String authority;
}