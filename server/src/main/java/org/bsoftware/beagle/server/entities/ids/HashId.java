package org.bsoftware.beagle.server.entities.ids;

import java.io.Serializable;

/**
 * HashId is a object which describes HashEntity composite id
 *
 * @author Rudolf Barbu
 * @version 1.0.0
 */
public class HashId implements Serializable
{
    /**
     * Fraction field
     */
    private String fraction;

    /**
     * Hash field
     */
    private String hash;
}