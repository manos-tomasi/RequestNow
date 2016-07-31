package com.paa.requestnow.model;

import com.paa.requestnow.model.db.service.UserManagerService;
import com.paa.requestnow.model.db.service.AttachmentManagerService;

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
    
    public AttachmentManagerService getAttachmentManager()
    {
        return AttachmentManagerService.getInstance();
    }
}
