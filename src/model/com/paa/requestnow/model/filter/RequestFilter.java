package com.paa.requestnow.model.filter;

import java.util.List;

/**
 *
 * @author lucas
 */
public class RequestFilter 
        extends 
            DefaultFilter
{
    public static final int STATE      = 0; 
    public static final int TYPE       = 1; 
    public static final int START      = 2; 
    public static final int END        = 3; 
    public static final int OPENED     = 4; 
    public static final int USER       = 5;

    @Override
    protected void createComponents( List<FilterItem> items ) 
    {
        items.add( new FilterItem( STATE,     "Situação",    "com.paa.requestnow.view.selectors.RequestStateSelector" ) );
        items.add( new FilterItem( TYPE,      "Tipo",        "com.paa.requestnow.view.selectors.TypeSelector" ) );
        items.add( new FilterItem( START,     "Inicio",      "com.paa.requestnow.view.util.DateBetweenField" ) );
        items.add( new FilterItem( END,       "Final",       "com.paa.requestnow.view.util.DateBetweenField" ) );
        items.add( new FilterItem( OPENED,    "Abertas",     "com.paa.requestnow.view.selectors.YesNoSelector" ) );
        items.add( new FilterItem( USER,      "Usuário",     "com.paa.requestnow.view.selectors.UserSelector" ) ) ;
    }
    
    
}
