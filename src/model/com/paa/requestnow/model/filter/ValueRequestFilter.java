package com.paa.requestnow.model.filter;

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
    protected void createComponents(List<FilterItem> filters) 
    {
        filters.add( new FilterItem( REQUEST,   "Requisição",  "com.paa.requestnow.view.selectors.RequestSelector" ) );
        filters.add( new FilterItem( FIELD,     "Campo",       "com.paa.requestnow.view.selectors.FieldSelector" )  );
    }
}
