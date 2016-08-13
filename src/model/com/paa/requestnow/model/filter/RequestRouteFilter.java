package com.paa.requestnow.model.filter;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author lucas
 */
public class RequestRouteFilter 
        extends 
            DefaultFilter
{
    public static final int USER       = 0; 
    public static final int STATE      = 1; 
    public static final int REQUEST    = 3; 
    public static final int IN         = 4; 
    public static final int OUT        = 5; 
    public static final int SECTOR     = 6; 
    
    @Override
    public List<FilterItem> getComponents() {
                return Arrays.asList(   new FilterItem( STATE,     "Situação",   "com.paa.requestnow.view.selectors.StateSelector" ),
                                        new FilterItem( REQUEST,   "Requisição", "com.paa.requestnow.view.selectors.RequestSelecotr" ),
                                        new FilterItem( IN,        "Entrada",    "com.paa.requestnow.view.util.DateBetweenField" ),
                                        new FilterItem( OUT,       "Saída",      "com.paa.requestnow.view.util.DateBetweenField" ),
                                        new FilterItem( USER,      "Usuário",    "com.paa.requestnow.view.selectors.UserSelector" ),
                                        new FilterItem( SECTOR,    "Setor",      "com.paa.requestnow.view.selectors.SectorSelector" ));
    }    
}
