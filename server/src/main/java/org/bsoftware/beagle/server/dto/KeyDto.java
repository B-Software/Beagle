package org.bsoftware.beagle.server.dto;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.*;

/**
 * KeyDto is a values container for presenting activating key data
 *
 * @author Rudolf Barbu
 * @version 1.0.0
 */
@Getter
@Setter
public class KeyDto
{
    /**
     * Key field
     */
    @NotNull
    @NotEmpty
    @Size(min = 32, max = 32)
    private String key;

    /**
     * Provided checks field
     */
    @NotNull
    @Min(value = 100000)
    @Max(value = 1000000000)
    private Long providedChecks;
}