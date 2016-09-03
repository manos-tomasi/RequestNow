package com.paa.requestnow.model.data;

/**
 * @author artur
 */
public class Category 
    extends
        Core<Category>
{
    private String name;
    private String info;
    
    public Category() {}

    public Category( String name, String info )
    {
        this.name = name;
        this.info = info;
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

    @Override
    public String toString() 
    {
        return name;
    }
}