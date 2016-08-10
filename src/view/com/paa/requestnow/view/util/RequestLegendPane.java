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
                        new LegendItem( "Reprovado"            , "finish.png" ),
                        new LegendItem( "Atrasada"             , "finish.png" ),
                        new LegendItem( "Aguardando aprovação" , "finish.png" ),
                        new LegendItem( "Cancelado"            , "finish.png" ),
                        new LegendItem( "Concluida"            , "finish.png" ));
    }
}
