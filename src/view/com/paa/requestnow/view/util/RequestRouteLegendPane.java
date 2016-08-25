package com.paa.requestnow.view.util;

import com.paa.requestnow.panes.LegendPane;

/**
 *
 * @author lucas
 */
public class RequestRouteLegendPane 
        extends 
            LegendPane
{

    public RequestRouteLegendPane() 
    {
        super();
        super.addItems( new LegendItem( "Aprovada"             , "finish.png" ),
                        new LegendItem( "Reprovada"            , "reproved.png" ),
                        new LegendItem( "Aguardando"           , "play.png" ),
                        new LegendItem( "Cancelada"            , "delete.png" ));
    }
}
