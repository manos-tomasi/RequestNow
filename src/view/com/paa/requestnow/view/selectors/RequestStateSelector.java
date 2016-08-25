package com.paa.requestnow.view.selectors;

import com.paa.requestnow.model.data.Option;
import com.paa.requestnow.model.data.Request;

/**
 * @author lucas
 */
public class RequestStateSelector
    extends 
        ItemSelector<Option>
{
    public static final Option APPROVED    = new Option( Request.APPROVED   , Request.STATES[ Request.APPROVED    ] );
    public static final Option CANCELED    = new Option( Request.CANCELED   , Request.STATES[ Request.CANCELED    ] );
    public static final Option REPROVED    = new Option( Request.REPROVED   , Request.STATES[ Request.REPROVED    ] );
    public static final Option IN_PROGRESS = new Option( Request.IN_PROGRESS, Request.STATES[ Request.IN_PROGRESS ] );
    
    public RequestStateSelector() 
    {
        super( "Seletor de condição" );
        
        setItems( APPROVED, CANCELED, REPROVED, IN_PROGRESS );
    }
}
