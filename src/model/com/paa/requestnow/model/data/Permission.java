package com.paa.requestnow.model.data;

/**
 * @author artur
 */
public class Permission 
    extends 
        Core<Permission>
{
    private int actionGroup;
    private int role;

    public Permission() {}

    public Permission (int actionGroup, int role )
    {
        this.actionGroup = actionGroup;
        this.role = role;
    }
    
    public int getActionGroup()
    {
        return actionGroup;
    }

    public void setActionGroup( int actionGroup )
    {
        this.actionGroup = actionGroup;
    }

    public int getRole() 
    {
        return role;
    }

    public void setRole( int role ) 
    {
        this.role = role;
    }
}
