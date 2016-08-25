package com.paa.requestnow.model.data;

/**
 * @author artur
 */
public class Action 
    extends 
        Core<Action>
{
    private String name = "";

    public Action() 
    {
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
