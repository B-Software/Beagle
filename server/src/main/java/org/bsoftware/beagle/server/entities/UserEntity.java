package org.bsoftware.beagle.server.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.io.Serializable;
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
@DynamicInsert
@Table(name = "users")
public class UserEntity implements Serializable
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
    @ColumnDefault(value = "0")
    @Column(name = "available_checks", nullable = false)
    private long availableChecks;

    /**
     * Field that displays user authorities
     */
    @JoinColumn(referencedColumnName = "username", name = "username")
    @OneToMany(targetEntity = AuthorityEntity.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<AuthorityEntity> authorities;
}