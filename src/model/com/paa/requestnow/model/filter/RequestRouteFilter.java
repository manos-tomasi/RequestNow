package com.paa.requestnow.model.filter;

import java.util.List;

/**
 *
 * @author lucas
 */
public class RequestRouteFilter 
        extends 
            DefaultFilter
{
    public static final int STATE      = 0; 
    public static final int REQUEST    = 1; 
    public static final int IN         = 2; 
    public static final int OUT        = 3; 
    public static final int USER       = 4; 
    
    @Override
    protected void createComponents( List<FilterItem> filters ) 
    {
        filters.add( new FilterItem( STATE,     "Situação",   "com.paa.requestnow.view.selectors.RequestRouteStateSelector" ) );
        filters.add( new FilterItem( REQUEST,   "Requisição", "com.paa.requestnow.view.selectors.RequestSelector" ) );
        filters.add( new FilterItem( IN,        "Entrada",    "com.paa.requestnow.view.util.DateBetweenField" ) );
        filters.add( new FilterItem( OUT,       "Saída",      "com.paa.requestnow.view.util.DateBetweenField" ) );
        filters.add( new FilterItem( USER,      "Usuário",    "com.paa.requestnow.view.selectors.UserSelector" ) );
    }
}
