package com.paa.requestnow.model.data;

/**
 * @author artur
 */
public class Group
    extends 
        Core<Group>
{
    private String origin = "";
    private String name = "";

    public Group() 
    {
    }
    
    public Group( String origin, String name )
    {
        this.origin = origin;
        this.name = name;
    }
    
    public String getOrigin() 
    {
        return origin;
    }

    public void setOrigin( String origin )
    {
        this.origin = origin;
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
        return name;
    }
}
