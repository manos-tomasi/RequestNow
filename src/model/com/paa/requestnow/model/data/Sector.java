package com.paa.requestnow.model.data;

/**
 *
 * @author lucas
 */
public class Sector 
    extends
        Core<Sector>
{
    private String name;

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

    @Override
    public String toString() 
    {
        return this.name;
    }
    
    
}
