package com.paa.requestnow.view.selectors;

import com.paa.requestnow.model.ApplicationUtilities;
import com.paa.requestnow.model.data.Sector;

/**
 *
 * @author lucas
 */
public class SectorSelector
        extends 
            ItemSelector<Sector>
{
    public SectorSelector() 
    {
         super( "Setor" );
        
        try
        {
            setItems(com.paa.requestnow.model.ModuleContext
                            .getInstance()
                            .getSectorManager()
                            .get() );
        }
        
        catch( Exception e )
        {
            ApplicationUtilities.logException( e );
        }
    }
}
