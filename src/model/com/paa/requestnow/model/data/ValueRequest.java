package com.paa.requestnow.model.data;

/**
 *
 * @author lucas
 */
public class ValueRequest 
        extends 
            Core<ValueRequest>
{
    private int field;
    private int request;
    private String value;

    public int getField() 
    {
        return field;
    }

    public void setField(int field) 
    {
        this.field = field;
    }

    public int getRequest() 
    {
        return request;
    }

    public void setRequest(int request) 
    {
        this.request = request;
    }

    public String getValue() 
    {
        return value;
    }

    public void setValue(String value) 
    {
        this.value = value;
    }

    @Override
    public String toString() 
    {
        return this.value;
    }    
}
