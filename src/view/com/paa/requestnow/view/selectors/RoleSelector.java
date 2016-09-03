package com.paa.requestnow.view.selectors;

import com.paa.requestnow.model.ApplicationUtilities;
import com.paa.requestnow.model.data.Role;

/**
 * @author artur
 */
public class RoleSelector 
    extends 
        ItemSelector<Role>
{
    public RoleSelector() 
    {
        super( "Funções" );
        
        try
        {
            setItems(com.paa.requestnow.model.ModuleContext
                            .getInstance()
                            .getRoleManager()
                            .get() );
        }
        
        catch( Exception e )
        {
            ApplicationUtilities.logException( e );
        }
    }
}
