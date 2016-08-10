package com.paa.requestnow.model.data;

/**
 * @author artur
 */
public class Option
{
    private int id;
    private String name;

    public static final Option YES = new Option(1, "Sim" );
    public static final Option NO  = new Option(2, "Não" );
    
    public Option( int id, String name )
    {
        this.id = id;
        this.name = name;
    }

    public int getId()
    {
        return id;
    }

    public void setId( int id )
    {
        this.id = id;
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

    @Override
    public boolean equals( Object  obj) 
    {
        if( obj instanceof Option )
        {
            return this.id == ((Option)obj).getId();
        }
        else
        {
            return false;
        }
    }
}