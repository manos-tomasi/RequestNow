package com.paa.requestnow.model.data;

/**
 * @author lucas
 */
public class ActionGroup
        extends Core<ActionGroup>
{   
    private int action;
    private int group;
 
    public ActionGroup() {}

    public int getAction() 
    {
        return action;
    }

    public void setAction(int action) 
    {
        this.action = action;
    }

    public int getGroup() 
    {
        return group;
    }

    public void setGroup(int group) 
    {
        this.group = group;
    }
}
