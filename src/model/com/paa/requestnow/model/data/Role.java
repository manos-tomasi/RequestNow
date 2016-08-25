package com.paa.requestnow.model.data;

/**
 * @author artur
 */
public class Role 
    extends 
        Core<Role>
{
    private String name = "";

    public Role() {
    }

    public Role( String name )
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
        return name;
    }
}
