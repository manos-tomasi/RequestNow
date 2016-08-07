package com.paa.requestnow.view.selectors;

import com.paa.requestnow.model.ApplicationUtilities;
import com.paa.requestnow.model.data.Request;

/**
 *
 * @author lucas
 */
public class RequestSelector 
        extends 
            ItemSelector<Request>
{
    public RequestSelector() 
    {
         super( "Requisição" );
        
        try
        {
            setItems(com.paa.requestnow.model.ModuleContext
                            .getInstance()
                            .getRequestManager()
                            .get() );
        }
        
        catch( Exception e )
        {
            ApplicationUtilities.logException( e );
        }
    }
}
