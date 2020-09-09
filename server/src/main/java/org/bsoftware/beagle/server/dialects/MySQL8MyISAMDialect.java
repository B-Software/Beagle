package org.bsoftware.beagle.server.dialects;

import org.hibernate.dialect.MySQL8Dialect;

/**
 * MySQL8MyISAMDialect makes all tables to use MyISAM engine
 *
 * @author Rudolf Barbu
 * @version 1.0.0
 */
public class MySQL8MyISAMDialect extends MySQL8Dialect
{
    /**
     * Indicates type of table engine to use
     *
     * @return String with name of engine
     */
    @Override
    public String getTableTypeString()
    {
        return " engine = MyISAM";
    }
}