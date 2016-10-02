package com.paa.requestnow.model.data;

import java.io.Serializable;

/**
 *
 * @author lucas
 */
public class Sector 
        extends
            Core<Sector>
{
    private String name;
    private int    state;

    public Sector(){}
    
    public Sector( String name ) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }

    public void setName( String name ) 
    {
        this.name = name;
    }
    
    public void setState( int state )
    {
        this.state = state;
    }
    
    public int getState()
    {
        return this.state;
    }

    @Override
    public String toString() 
    {
        return this.name;
    }
}
