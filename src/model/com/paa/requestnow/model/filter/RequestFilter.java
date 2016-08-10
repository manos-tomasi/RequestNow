package com.paa.requestnow.model.filter;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author lucas
 */
public class RequestFilter 
        extends 
            DefaultFilter
{
    public static final int USER  = 0; 
    public static final int STATE = 1; 
    public static final int TYPE  = 2; 
    public static final int START = 3; 
    public static final int END   = 4; 
    public static final int OPENED= 5; 
    
    @Override
    public List<FilterItem> getComponents() {
                return Arrays.asList(   new FilterItem( STATE,     "Situação", "com.paa.requestnow.view.selectors.StateSelector" ),
                                        new FilterItem( TYPE,      "Tipo",     "com.paa.requestnow.view.selectors.TypeSelector" ),
                                        new FilterItem( START,     "Inicio",   "com.paa.requestnow.view.util.DateBetweenField" ),
                                        new FilterItem( END,       "Final",    "com.paa.requestnow.view.util.DateBetweenField" ),
                                        new FilterItem( OPENED,    "Abertas",   "com.paa.requestnow.view.selectors.YesNoSelctor" ),
                                        new FilterItem( USER,      "Usuário",  "com.paa.requestnow.view.selectors.UserSelector" ));
    }
}
