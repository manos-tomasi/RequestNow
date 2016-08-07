package com.paa.requestnow.model.filter;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author lucas
 */
public class TypeFilter 
        extends 
            DefaultFilter
{
    public static final int NAME       = 0;
    public static final int CATEGORY   = 1;
    public static final int STATE      = 2;

    @Override
    public List<FilterItem> getComponents() {
                return Arrays.asList(   new FilterItem( NAME,       "Nome",              "com.paa.requestnow.view.util.MaskTextField" ),
                                        new FilterItem( CATEGORY,   "Categoria",         "com.paa.requestnow.view.selectors.CategorySelector" ),
                                        new FilterItem( STATE,      "Situação",          "com.paa.requestnow.view.selectors.StateSelector" ) );
    }
}
