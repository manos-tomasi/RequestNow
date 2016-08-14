package com.paa.requestnow.view.util;

import com.paa.requestnow.panes.LegendPane;

/**
 *
 * @author lucas
 */
public class RequestLegendPane 
        extends 
            LegendPane
{    
    public RequestLegendPane() 
    {
        super();
        super.addItems( new LegendItem( "Aprovada"             , "finish.png" ),
                        new LegendItem( "Reprovada"            , "reproved.png" ),
                        new LegendItem( "Em Andamento"         , "play.png" ),
                        new LegendItem( "Cancelada"            , "delete.png" ));
    }
}
