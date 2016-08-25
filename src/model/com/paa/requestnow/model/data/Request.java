package com.paa.requestnow.model.data;

import java.sql.Timestamp;

/**
 * @author artur
 */
public class Request
        extends 
            Core<Request>
{
    private int type;
    private int user;
    private Timestamp start;
    private Timestamp end;

    public static final int IN_PROGRESS = 0;
    public static final int APPROVED    = 1;
    public static final int CANCELED    = 2;
    public static final int REPROVED    = 3;

    public static final String[] STATES = { "Em andamento", "Aprovada", "Cancelada", "Reprovada" };
    
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

    public Timestamp getStart() 
    {
        return start;
    }

    public void setStart(Timestamp start) 
    {
        this.start = start;
    }

    public Timestamp getEnd() 
    {
        return end;
    }

    public void setEnd(Timestamp end) 
    {
        this.end = end;
    }

    @Override
    public String toString() 
    {
        return start.toString();
    }    
}
