package org.bsoftware.beagle.server.exceptions;

/**
 * UserAlreadyExistsException thrown if user already exists
 *
 * @author Rudolf Barbu
 * @version 1.0.0
 */
public final class UserAlreadyExistsException extends Exception
{
    /**
     * @param message exception message
     */
    public UserAlreadyExistsException(String message)
    {
        super(message);
    }
}