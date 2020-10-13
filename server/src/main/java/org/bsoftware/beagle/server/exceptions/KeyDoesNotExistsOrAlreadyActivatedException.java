package org.bsoftware.beagle.server.exceptions;

/**
 * KeyDoesNotExistsOrAlreadyActivatedException thrown if activation key does not exists, or already activated
 *
 * @author Rudolf Barbu
 * @version 1.0.0
 */
public final class KeyDoesNotExistsOrAlreadyActivatedException extends Exception
{
    /**
     * Call super class with exception message
     */
    public KeyDoesNotExistsOrAlreadyActivatedException()
    {
        super("Key already activated or does not exists");
    }
}