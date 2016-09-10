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
 * @param <E>
 */
public abstract class AbstractController <T, E>
{
    private String  className ;
    private User user;
    
    public AbstractController() 
    {
        Type superClass = this.getClass().getGenericSuperclass();
        Type type = ( ( ParameterizedType ) superClass ).getActualTypeArguments()[0];
        
        className = type.toString().split(" ")[1];
        
        user = ApplicationUtilities.getInstance().getActiveUser();
    }
     
    public boolean hasPermissionEdit()
    {
        try
        {
            return com.paa.requestnow.model.ModuleContext.getInstance()
                                                .getPermissionManager()
                                                .canPermission( user.getRole() , Action.EDIT, className );
        }
        
        catch ( Exception e )
        {
            logException( e );
        }
        
        return false;
    }
    
    public boolean hasPermissionAdd()
    {
        try
        {
            return com.paa.requestnow.model.ModuleContext.getInstance()
                                            .getPermissionManager()
                                            .canPermission( user.getRole() , Action.ADD, className );
        }
        
        catch ( Exception e )
        {
            logException( e );
        }
        
        return false;
    }
    
    public boolean hasPermissionDelete()
    {
        try
        {
           return com.paa.requestnow.model.ModuleContext.getInstance()
                                            .getPermissionManager()
                                            .canPermission( user.getRole() , Action.DELETE, className );
        }
        
        catch ( Exception e )
        {
            logException( e );
        }
        
        return false;
    }
    
    public boolean hasPermissionView()
    {
        try
        {
           return com.paa.requestnow.model.ModuleContext.getInstance()
                                        .getPermissionManager()
                                        .canPermission( user.getRole() , Action.VIEW, className );
        }
        
        catch ( Exception e )
        {
            logException( e );
        }
        
        return false;
    }
    
    public abstract String onEdit( E item ) throws Exception;
    public abstract String onDelete( E item ) throws Exception;
    public abstract String onAdd( E item ) throws Exception;
    
    protected void logException( Exception e )
    {
        ApplicationUtilities.logException( e );
    }
}
