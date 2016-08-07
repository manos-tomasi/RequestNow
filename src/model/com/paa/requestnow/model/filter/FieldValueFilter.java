package com.paa.requestnow.model.filter;

import java.util.Arrays;
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
    public List<FilterItem> getComponents() {
                return Arrays.asList(   new FilterItem( STATE,      "Situação", "com.paa.requestnow.view.selectors.StateSelector" ),
                                        new FilterItem( FIELD,      "Campo"   , "com.paa.requestnow.view.selectors.FieldSelector" ));
    }    
}
