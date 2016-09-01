package com.paa.requestnow.model.data;

/**
 *
 * @author lucas
 */
public class TypeRoute 
        extends 
            Core<TypeRoute>
{
    private int sector;
    private int type;
    private int user;
    private int sequence;
    private int days;

    public TypeRoute() {}

    public int getSector() 
    {
        return sector;
    }

    public void setSector(int sector) 
    {
        this.sector = sector;
    }

    public int getType() 
    {
        return type;
    }

    public void setType(int type) 
    {
        this.type = type;
    }

    public int getUser() 
    {
        return user;
    }

    public void setUser(int user) 
    {
        this.user = user;
    }

    public int getSequence() 
    {
        return sequence;
    }

    public void setSequence(int sequence) 
    {
        this.sequence = sequence;
    }

    public int getDays() 
    {
        return days;
    }

    public void setDays(int days) 
    {
        this.days = days;
    }

    @Override
    public String toString() 
    {
        return String.valueOf( this.sequence );
    } 
    
    public String toJson()
    {
        try
        {
        }
        
        catch ( Exception e )
        {
            System.err.println( e );
        }
        
        return null;
    }
}
