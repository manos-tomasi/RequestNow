package com.paa.requestnow.control.util;

import com.paa.requestnow.model.ApplicationUtilities;
import com.paa.requestnow.model.data.Category;
import com.paa.requestnow.model.data.Type;

/**
 * @author artur
 */
public class JsonUtilities 
{
    public static String getCategory( Category category )
    {
        try
        {
            return com.paa.requestnow.model.ModuleContext
                                .getInstance()
                                .getCategoryManager()
                                .getJson( category );
        }
        catch ( Exception e )
        {
            ApplicationUtilities.logException( e );
        }
        
        return  null;
    }
    
    public static String getType( Type type )
    {
        try
        {
            return com.paa.requestnow.model.ModuleContext
                                .getInstance()
                                .getTypeManager()
                                .getJson( type );
        }
        catch ( Exception e )
        {
            ApplicationUtilities.logException( e );
        }
        
        return  null;
    }
    
    public static String getRoute( int id )
    {
        //to do
        
        return null;
    }
    
    public static String getRequest( int id )
    {
        //to do
        
        return null;
    }
    
    public static String getField( int id )
    {
        //to do
        
        return null;
    }
}
