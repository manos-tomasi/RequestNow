package com.paa.requestnow.control.socket;

import java.io.Serializable;

/**
 * @author artur
 */
public class SocketData 
    implements 
        Serializable
{
    private String name;

    public SocketData( String name ) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    @Override
    public String toString() 
    {
        return name;
    }
}
