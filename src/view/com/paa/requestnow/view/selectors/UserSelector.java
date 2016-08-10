package com.paa.requestnow.view.selectors;

import com.paa.requestnow.model.ApplicationUtilities;
import com.paa.requestnow.model.data.User;

/**
 * @author artur
 */
public class UserSelector
   extends 
        ItemSelector<User>
{
    public UserSelector() 
    {
        super( "Usuário" );
        
        try
        {
            setItems(com.paa.requestnow.model.ModuleContext
                            .getInstance()
                            .getUserManager()
                            .get() );
        }
        
        catch( Exception e )
        {
            ApplicationUtilities.logException( e );
        }
    }
}
