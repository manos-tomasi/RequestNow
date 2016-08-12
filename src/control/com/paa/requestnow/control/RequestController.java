package com.paa.requestnow.control;

import com.paa.requestnow.model.data.Field;
import com.paa.requestnow.model.data.Request;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author lucas
 */
public class RequestController 
{
    
    private static RequestController controller;

    public static RequestController getInstance()
    {
        if( controller == null )
        {
            controller = new RequestController();
        }
        
        return controller;
    }
    
    public void save( Properties  p, Request r )
    {
        
    }
    
    public void cancel( Request r )
    {
        
    }
    
    public List<Field> getFields( int id ) throws Exception
    {
        return com.paa.requestnow.model.ModuleContext.getInstance().getFieldManager().getFieldsType( id );
    }
}
