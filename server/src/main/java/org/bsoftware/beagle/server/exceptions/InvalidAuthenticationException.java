package org.bsoftware.beagle.server.exceptions;

/**
 * InvalidAuthenticationException thrown if user don't have valid authentication
 *
 * @author Rudolf Barbu
 * @version 1.0.0
 */
public final class InvalidAuthenticationException extends Exception
{
    /**
     * Call super class with exception message
     */
    public InvalidAuthenticationException()
    {
        super("User in not authenticated");
    }
}