package com.paa.requestnow.model.filter;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author lucas
 */
public class ValueRequestFilter 
        extends 
            DefaultFilter
{
    public static final int REQUEST   = 0;
    public static final int FIELD     = 1;

    @Override
    public List<FilterItem> getComponents() {
                return Arrays.asList(   new FilterItem( REQUEST,   "Requisição",  "com.paa.requestnow.view.selectors.RequestSelector" ),
                                        new FilterItem( FIELD,     "Campo",       "com.paa.requestnow.view.selectors.FieldSelector" ) );
    }
}
