package com.paa.requestnow.view.selectors;

import com.paa.requestnow.model.ApplicationUtilities;
import com.paa.requestnow.model.data.Field;

/**
 *
 * @author lucas
 */
public class FieldSelector 
        extends 
            ItemSelector<Field>
{

    public FieldSelector() 
    {
        super( "Campo" );
        
        try
        {
            setItems(com.paa.requestnow.model.ModuleContext
                            .getInstance()
                            .getFieldManager()
                            .get() );
        }
        
        catch( Exception e )
        {
            ApplicationUtilities.logException( e );
        }
    }    
}
