package com.paa.requestnow.view.selectors;

import com.paa.requestnow.model.data.Option;
import com.paa.requestnow.model.data.RequestRoute;

/**
 * @author lucas
 */
public class RequestRouteStateSelector
    extends 
        ItemSelector<Option>
{
    public static final Option APPROVED    = new Option( RequestRoute.APPROVED , RequestRoute.STATES[ RequestRoute.APPROVED  ] );
    public static final Option CANCELED    = new Option( RequestRoute.CANCELED, RequestRoute.STATES[ RequestRoute.CANCELED ] );
    public static final Option REPROVED    = new Option( RequestRoute.DISAPPROVED, RequestRoute.STATES[ RequestRoute.DISAPPROVED ] );
    public static final Option STOPED      = new Option( RequestRoute.STOPED  , RequestRoute.STATES[ RequestRoute.STOPED   ] );
    
    public RequestRouteStateSelector() 
    {
        super( "Seletor de condição" );
        
        setItems( APPROVED, CANCELED, REPROVED, STOPED );
    }
}
