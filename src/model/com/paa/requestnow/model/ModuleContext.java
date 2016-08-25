package com.paa.requestnow.model;

import com.paa.requestnow.model.db.service.UserManagerService;
import com.paa.requestnow.model.db.service.*;

/**
 * @author artur
 */
public class ModuleContext 
{
    private static ModuleContext moduleContext;
    
    public static ModuleContext getInstance()
    {
        if( moduleContext == null )
        {
            moduleContext = new ModuleContext();
        }
        
        return  moduleContext;
    }
    
    private ModuleContext()
    {
    }
    
    public UserManagerService getUserManager()
    {
        return UserManagerService.getInstance();
    }
    
    public CategoryManagerService getCategoryManager()
    {
        return CategoryManagerService.getInstance();
    }
    
    public SectorManagerService getSectorManager()
    {
        return SectorManagerService.getInstance();
    }
    
    public FieldManagerService getFieldManager()
    {
        return FieldManagerService.getInstance();
    }

    public ValueRequestManagerService getValueRequestManager()
    {
        return ValueRequestManagerService.getInstance();
    }    

    public TypeManagerService getTypeManager()
    {
        return TypeManagerService.getInstance();
    }
    
    public TypeRouteManagerService getTypeRouteManager()
    {
        return TypeRouteManagerService.getInstance();
    }
    
    public FieldValueManagerService getFieldValueManager()
    {
        return FieldValueManagerService.getInstance();
    }
    
    public RequestManagerService getRequestManager()
    {
        return RequestManagerService.getInstance();
    }
    
    public RequestRouteManagerService getRequestRouteManager()
    {
        return RequestRouteManagerService.getInstance();
    }
    
    public RoleManagerService getRoleManager()
    {
        return RoleManagerService.getInstance();
    }
    
    public GroupManagerService getGroupManager()
    {
        return GroupManagerService.getInstance();
    }
    
    public ActionManagerService getActionManager()
    {
        return ActionManagerService.getInstance();
    }
    
    public ActionGroupManagerService getActionGroupManager()
    {
        return ActionGroupManagerService.getInstance();
    }
    
    public PermissionManagerService getPermissionManager()
    {
        return PermissionManagerService.getInstance();
    }
}
