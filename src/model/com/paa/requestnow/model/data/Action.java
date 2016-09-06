package com.paa.requestnow.model.data;

/**
 * @author artur
 */
public class Action 
    extends 
        Core<Action>
{
    public static final int VIEW     = 1;
    public static final int EDIT     = 2;
    public static final int ADD      = 3;
    public static final int DELETE   = 4;
    public static final int DISPATCH = 5;
    public static final int CANCEL   = 6;
    public static final int PRINT    = 7;

    private String name;

    public Action(){}

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
