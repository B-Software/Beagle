package org.bsoftware.beagle.server.exceptions;

import org.apache.tika.Tika;

public class WrongFileExtensionException extends Exception
{
    public WrongFileExtensionException(byte[] multipartFileBytes, Tika tika)
    {
        super("File extension is invalid: " + tika.detect(multipartFileBytes));
    }
}