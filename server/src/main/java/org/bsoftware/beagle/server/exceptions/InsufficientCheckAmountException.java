package org.bsoftware.beagle.server.exceptions;

/**
 * InsufficientCheckAmountException thrown if user don't have available checks
 *
 * @author Rudolf Barbu
 * @version 1.0.0
 */
public final class InsufficientCheckAmountException extends Exception
{
    /**
     * Call super class with exception message
     */
    public InsufficientCheckAmountException()
    {
        super("You don't have available checks");
    }
}