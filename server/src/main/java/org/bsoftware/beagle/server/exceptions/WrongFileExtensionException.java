package org.bsoftware.beagle.server.exceptions;

import org.apache.tika.Tika;

/**
 * WrongFileExtensionException is a exception which may be thrown if file have wrong extension
 *
 * @author Rudolf Barbu
 * @version 1.0.0
 */
public final class WrongFileExtensionException extends Exception
{
    /**
     * Used for exception message construction
     *
     * @param multipartFileBytes file bytes
     * @param tika tika object
     */
    public WrongFileExtensionException(byte[] multipartFileBytes, Tika tika)
    {
        super("File extension is invalid: " + tika.detect(multipartFileBytes));
    }
}