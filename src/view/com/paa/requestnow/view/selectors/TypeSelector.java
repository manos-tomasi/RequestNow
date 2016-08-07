package com.paa.requestnow.view.selectors;

import com.paa.requestnow.model.ApplicationUtilities;
import com.paa.requestnow.model.data.Type;

/**
 *
 * @author lucas
 */
public class TypeSelector 
        extends 
            ItemSelector<Type>
{

    public TypeSelector() 
    {
        super( "Tipo de Requisição" );
        
        try
        {
            setItems(com.paa.requestnow.model.ModuleContext
                            .getInstance()
                            .getTypeManager()
                            .get() );
        }
        
        catch( Exception e )
        {
            ApplicationUtilities.logException( e );
        }
    }
}
