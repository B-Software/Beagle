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
     * Call super class with exception message
     */
    public UserAlreadyExistsException()
    {
        super("User with this username already exists");
    }
}