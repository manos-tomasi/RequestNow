package com.paa.requestnow.control.util;

import com.paa.requestnow.model.ApplicationUtilities;
import com.paa.requestnow.model.data.Category;
import com.paa.requestnow.model.data.Field;
import com.paa.requestnow.model.data.Request;
import com.paa.requestnow.model.data.Type;
import com.paa.requestnow.model.data.TypeRoute;

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
    
    public static String getRoute( TypeRoute route )
    {
        try
        {
             return com.paa.requestnow.model.ModuleContext
                                .getInstance()
                                .getTypeRouteManager()
                                .getJson( route );
        }
        
        catch ( Exception e )
        {
            ApplicationUtilities.logException( e );
        }
        
        return null;
    }
    
    public static String getRequest( Request request  )
    {
        try
        {
             return com.paa.requestnow.model.ModuleContext
                                .getInstance()
                                .getRequestManager()
                                .getJsonRequest( request );
        }
        
        catch ( Exception e )
        {
            ApplicationUtilities.logException( e );
        }
        
        return null;
    }
    
    public static String getRequestField( Request request  )
    {
        try
        {
             return com.paa.requestnow.model.ModuleContext
                                .getInstance()
                                .getRequestManager()
                                .getJsonField( request );
        }
        
        catch ( Exception e )
        {
            ApplicationUtilities.logException( e );
        }
        
        return null;
    }
    
    public static String getField( Field field )
    {
        try
        {
             return com.paa.requestnow.model.ModuleContext
                                .getInstance()
                                .getFieldManager()
                                .getJson( field );
        }
        
        catch ( Exception e )
        {
            ApplicationUtilities.logException( e );
        }
        
        return null;
    }
}
