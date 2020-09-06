package org.bsoftware.beagle.server.services.implementation;

import org.bsoftware.beagle.server.dto.Dto;
import org.springframework.stereotype.Service;

/**
 * PasswordService provides information about password, and provides adding mechanisms to database
 *
 * @author Rudolf Barbu
 * @version 1.0.0
 */
@Service
public class PasswordService implements org.bsoftware.beagle.server.services.Service
{
    @Override
    public <T extends Dto> T get(String parameter) throws Exception
    {
        return null;
    }
}