package org.bsoftware.beagle.server.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * PasswordDto is a values container for presenting password data
 *
 * @author Rudolf Barbu
 * @version 1.0.0
 */
@Getter
@Setter
public class CountDto
{
    /**
     * Hashes count field
     */
    long hashesCount;
}