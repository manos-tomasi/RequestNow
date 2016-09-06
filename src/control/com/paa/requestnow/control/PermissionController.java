package com.paa.requestnow.control;

import com.paa.requestnow.model.ApplicationUtilities;
import com.paa.requestnow.model.data.Action;
import com.paa.requestnow.model.data.User;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 *
 * @author artur
 * @param <T>
 */
public abstract class PermissionController <T>
{
    private String  className ;
    private User user;
    
    public PermissionController() 
    {
        Type superClass = this.getClass().getGenericSuperclass();
        Type type = ( ( ParameterizedType ) superClass ).getActualTypeArguments()[0];
        
        className = type.toString().split(" ")[1];
        
        user = ApplicationUtilities.getInstance().getActiveUser();
    }
     
    public boolean canEdit()
    {
        try
        {
            return com.paa.requestnow.model.ModuleContext.getInstance().getPermissionManager().canPermission( user.getRole() , Action.EDIT, className );
        }
        
        catch ( Exception e )
        {
            logException( e );
        }
        
        return false;
    }
    
    public boolean canAdd()
    {
        try
        {
            return com.paa.requestnow.model.ModuleContext.getInstance().getPermissionManager().canPermission( user.getRole() , Action.ADD, className );
        }
        
        catch ( Exception e )
        {
            logException( e );
        }
        
        return false;
    }
    
    public boolean canDelete()
    {
        try
        {
           return com.paa.requestnow.model.ModuleContext.getInstance().getPermissionManager().canPermission( user.getRole() , Action.DELETE, className );
        }
        
        catch ( Exception e )
        {
            logException( e );
        }
        
        return false;
    }
    
    public boolean canView()
    {
        try
        {
           return com.paa.requestnow.model.ModuleContext.getInstance().getPermissionManager().canPermission( user.getRole() , Action.VIEW, className );
        }
        
        catch ( Exception e )
        {
            logException( e );
        }
        
        return false;
    }
    
    protected void logException( Exception e )
    {
        ApplicationUtilities.logException( e );
    }
}
