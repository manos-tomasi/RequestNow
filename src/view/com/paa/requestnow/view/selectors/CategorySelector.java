package com.paa.requestnow.view.selectors;

import com.paa.requestnow.model.ApplicationUtilities;
import com.paa.requestnow.model.data.Category;

/**
 *
 * @author lucas
 */
public class CategorySelector 
        extends 
            ItemSelector<Category>
{
    public CategorySelector() 
    {
        super( "Categoria" );
        
        try
        {
            setItems(com.paa.requestnow.model.ModuleContext
                            .getInstance()
                            .getCategoryManager()
                            .get() );
        }
        
        catch( Exception e )
        {
            ApplicationUtilities.logException( e );
        }
    }
}
