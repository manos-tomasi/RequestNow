package com.paa.requestnow.control;

import com.paa.requestnow.model.ApplicationUtilities;
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
    private String className ;
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
            return user.getRole() == User.ROLE_ADMIN;
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
            return user.getRole() == User.ROLE_ADMIN;
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
           return user.getRole() == User.ROLE_ADMIN;
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
           return user.getRole() == User.ROLE_ADMIN;
        }
        
        catch ( Exception e )
        {
            logException( e );
        }
        
        return false;
    }
    
    /**
     * use when select node of the tree into other controller
     * 
     * @param className
     * @return 
     */
    public boolean canView( String className )
    {
        try
        {
            //pensar se pode ou não e qual vai ser a regra
           return user.getRole() == User.ROLE_ADMIN;
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
