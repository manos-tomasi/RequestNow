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
    private boolean active;
    private String description;
    private int group;

    
    public Permission() {}

    public Permission (int actionGroup, int role )
    {
        this.actionGroup = actionGroup;
        this.role = role;
    }
    
    public boolean isActive() 
    {
        return active;
    }

    public void setActive(boolean active) 
    {
        this.active = active;
    }
    
    public int getActionGroup()
    {
        return actionGroup;
    }

    public void setActionGroup( int actionGroup )
    {
        this.actionGroup = actionGroup;
    }

    public int getGroup()
    {
        return group;
    }

    public void setGroup( int group )
    {
        this.group = group;
    }
    
    public int getRole() 
    {
        return role;
    }

    public void setRole( int role ) 
    {
        this.role = role;
    }

    public String getDescription() 
    {
        return description;
    }

    public void setDescription( String description )
    {
       this.description = description;
    }
    
    @Override
    public String toString() 
    {
        return description;
    }
    
    
}
