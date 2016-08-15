package com.paa.requestnow.view.selectors;

import com.paa.requestnow.model.data.Option;

/**
 * @author lucas
 */
public class RequestStateSelector
    extends 
        ItemSelector<Option>
{
    public static final Option APPROVED    = new Option( 1, "Aprovada"     );
    public static final Option CANCELED    = new Option( 2, "Cancelada"    );
    public static final Option REPROVED    = new Option( 3, "Reprovada"    );
    public static final Option IN_PROGRESS = new Option( 4, "Em andamento" );
    
    public RequestStateSelector() 
    {
        super( "Seletor de condição" );
        
        setItems( APPROVED, CANCELED, REPROVED, IN_PROGRESS );
    }
}
