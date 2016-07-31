package com.paa.requestnow.view.selectors;

import com.paa.requestnow.model.data.Core;
import com.paa.requestnow.model.data.Option;

/**
 * @author artur
 */
public class StateSelector 
    extends 
        ItemSelector<Option>
{
    public StateSelector() 
    {
        super( "Selecione uma situação", 
                new Option( Core.STATE_ACTIVE,   Core.STATES[ Core.STATE_ACTIVE ] ), 
                new Option( Core.STATE_INACTIVE, Core.STATES[ Core.STATE_INACTIVE ] ) );
    }
}
