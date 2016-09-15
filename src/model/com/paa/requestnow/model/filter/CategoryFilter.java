package com.paa.requestnow.model.filter;

import java.util.List;

/**
 *
 * @author lucas
 */
public class CategoryFilter 
        extends 
            DefaultFilter
{
    public static final int NAME       = 0;
    public static final int STATE      = 1;
    
    @Override
    protected void createComponents(List<FilterItem> filters) 
    {
        filters.add( new FilterItem( NAME,       "Nome",              "com.paa.requestnow.view.util.MaskTextField" ) );
        filters.add( new FilterItem( STATE,      "Situação",          "com.paa.requestnow.view.selectors.StateSelector" )  );
    }
    
}
