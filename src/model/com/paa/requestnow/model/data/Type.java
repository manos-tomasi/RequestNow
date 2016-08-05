package com.paa.requestnow.model.data;

/**
 * @author artur
 */
public class Type 
    extends 
        Core<Type>
{
    private String name;
    private String info;
    private int category;

    public Type() {}

    public Type( String name, String info, int category )
    {
        this.name = name;
        this.info = info;
        this.category = category;
    }
    
    public String getName() 
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public String getInfo() 
    {
        return info;
    }

    public void setInfo( String info )
    {
        this.info = info;
    }

    public int getCategory() 
    {
        return category;
    }

    public void setCategory( int category )
    {
        this.category = category;
    }

    @Override
    public String toString() 
    {
        return name;
    }
}
