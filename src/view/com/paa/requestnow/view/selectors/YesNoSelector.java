package com.paa.requestnow.view.selectors;

import com.paa.requestnow.model.data.Option;

/**
 * @author artur
 */
public class YesNoSelector
    extends 
        ItemSelector<Option>
{
    public YesNoSelector() 
    {
        super( "Seletor de condição" );
        
        setItems( Option.YES, Option.NO );
    }
    
    public void setOption( boolean yes )
    {
        setSelectedIndex( yes ? 0 : 1 );
    }
    
    public boolean isYesOption()
    {
        return getSelectedIndex() == 0;
    }
}
