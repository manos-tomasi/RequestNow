package com.paa.requestnow.model.data;

import java.sql.Timestamp;

/**
 *
 * @author lucas
 */
public class RequestRoute 
        extends 
            Core<Request>
{
    private int request;
    private int typeRoute;
    private Timestamp in;
    private Timestamp out;
    private int user;
    private int sequence;
    private String info;

    public static final int CANCELED = 0;
    public static final int REPROVED = 1;
    public static final int APROVED  = 2;
    public static final int WAINTING = 3;
    public static final int STOPED   = 4;
    
    public static final String[] STATES = { "Cancelada", "Reprovada", "Aprovada", "Aguardando despachos", "Aguardando aprovação" };
    
    public RequestRoute() {}

    public int getRequest()
    {
        return request;
    }

    public int getSequence() 
    {
        return sequence;
    }

    public void setSequence(int sequence) 
    {
        this.sequence = sequence;
    }
    
    public void setRequest(int request)
    {
        this.request = request;
    }

    public int getTypeRoute()
    {
        return typeRoute;
    }

    public void setTypeRoute(int typeRoute)
    {
        this.typeRoute = typeRoute;
    }

    public Timestamp getIn()
    {
        return in;
    }

    public void setIn(Timestamp in) 
    {
        this.in = in;
    }

    public Timestamp getOut() {
        return out;
    }

    public void setOut(Timestamp out) 
    {
        this.out = out;
    }

    public int getUser() 
    {
        return user;
    }

    public void setUser(int user)
    {
        this.user = user;
    }

    public String getInfo() 
    {
        return info;
    }

    public void setInfo(String info) 
    {
        this.info = info;
    }

    @Override
    public String toString() 
    {
     return this.info;
    }
}
