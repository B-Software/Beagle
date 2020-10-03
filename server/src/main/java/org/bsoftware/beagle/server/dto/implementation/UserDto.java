package org.bsoftware.beagle.server.dto.implementation;

import lombok.Getter;
import lombok.Setter;
import org.bsoftware.beagle.server.dto.Dto;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * UserDto is a values container for presenting user credentials
 *
 * @author Rudolf Barbu
 * @version 1.0.0
 */
@Getter
@Setter
public class UserDto implements Dto
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
}