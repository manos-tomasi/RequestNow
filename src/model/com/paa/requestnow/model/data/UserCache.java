package com.paa.requestnow.model.data;

import com.paa.requestnow.model.ApplicationUtilities;
import java.util.HashMap;

/**
 * @author artur
 */
public class UserCache 
{
    private static HashMap< Integer, User> users = new HashMap();
    
    public static User getUser( int id )
    {
        User user = users.get( id );
       
        try
        {
            if( user == null )
            {
                user = (User) com.paa.requestnow.model.ModuleContext.getInstance()
                                                   .getUserManager()
                                                   .get( id ); 

                if( user != null )
                {
                    users.put( id, user );
                }

                return  user;
            }
        }

        catch( Exception e )
        {
            ApplicationUtilities.logException( e );
        }
        
        return user;
    }
}
