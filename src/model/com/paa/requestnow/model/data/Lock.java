package com.paa.requestnow.model.data;

import java.sql.Timestamp;

/**
 * @author lucas
 */
public class Lock 
        extends 
            Core<Lock>
{
    private int user;
    private Timestamp date;
    private int route;

    public Lock() {}

    public Lock(int user, int request) {
        this.user    = user;
        this.route = request;
    }
    
    
    
    public int getUser()
    {
        return user;
    }

    public void setUser(int user) 
    {
        this.user = user;
    }

    public Timestamp getDate() 
    {
        return date;
    }

    public void setDate(Timestamp date)
    {
        this.date = date;
    }

    public int getRoute() 
    {
        return route;
    }

    public void setRoute(int route) 
    {
        this.route = route;
    }
}
