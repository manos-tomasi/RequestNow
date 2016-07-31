package com.paa.requestnow.control;

import com.paa.requestnow.control.tasks.MainTasks;
import com.paa.requestnow.model.ApplicationUtilities;
import com.paa.requestnow.model.data.Encryption;
import com.paa.requestnow.model.data.User;

/**
 * @author artur
 */
public class LoginController 
{
    private static LoginController instance;
    
    private LoginController(){}
    
    public static LoginController getInstance()
    {
        if( instance == null )
        {
            instance = new LoginController();
        }
        
        return instance;
    }
    
    public User login( String login, String password )
    {
        User user = null;
        
        try
        {
            user = com.paa.requestnow.model.ModuleContext
                                    .getInstance()
                                    .getUserManager()
                                    .getUserByLogin( login, Encryption.hash( password ) );
                      
            if( user != null )
            {
                ApplicationUtilities.getInstance().setActiveUser( user );
                
                new MainTasks().run();
            }
        }
        
        catch( Exception e )
        {
            ApplicationUtilities.logException( e );
        }
        
        return user;
    }
}
