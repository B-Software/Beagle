package org.bsoftware.beagle.server.dto.implementation;

import lombok.Getter;
import lombok.Setter;
import org.bsoftware.beagle.server.dto.Dto;

/**
 * PasswordDto is a values container for presenting password data
 *
 * @author Rudolf Barbu
 * @version 1.0.0
 */
@Getter
@Setter
public class CountDto implements Dto
{
    /**
     * Hashes count field
     */
    long hashesCount;
}