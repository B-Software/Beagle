package org.bsoftware.beagle.server.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

/**
 * UserDto is a values container for presenting user credentials
 *
 * @author Rudolf Barbu
 * @version 1.0.0
 */
@Getter
@Setter
@JsonIgnoreProperties(value = "password", allowSetters = true)
public class UserDto
{
    /**
     * Username field
     */
    @NotNull
    @NotEmpty
    @Size(min = 4, max = 8)
    private String username;

    /**
     * Password field
     */
    @NotNull
    @NotEmpty
    @Size(min = 6, max = 16)
    private String password;

    /**
     * Field that displays available checks
     */
    @Null
    private Long availableChecks;

    /**
     * Field that displays user authority
     */
    @Null
    private String authority;
}