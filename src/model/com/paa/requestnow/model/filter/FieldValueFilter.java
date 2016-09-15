package com.paa.requestnow.model.filter;

import java.util.List;

/**
 *
 * @author lucas
 */
public class FieldValueFilter 
        extends 
            DefaultFilter
{
    public static final int FIELD = 0;
    public static final int STATE = 1;
    
    @Override
    protected void createComponents(List<FilterItem> filters) 
    {
        filters.add( new FilterItem( FIELD,      "Nome",              "com.paa.requestnow.view.selectors.FieldSelector" ) );
        filters.add( new FilterItem( STATE,      "Situação",          "com.paa.requestnow.view.selectors.StateSelector" )  );
    }  
}
