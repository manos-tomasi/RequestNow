package com.paa.requestnow.model.filter;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author lucas
 */
public class FieldFilter 
        extends 
            DefaultFilter
{
    public static final int LABEL        = 0;
    public static final int STATE        = 1;
    public static final int REQUIRED     = 2;
    public static final int TYPE         = 3;
    public static final int TYPE_REQUEST = 4;
    
    @Override
    public List<FilterItem> getComponents() 
    {
                return Arrays.asList(   new FilterItem( LABEL,        "Label",            "com.paa.requestnow.view.util.MaskTextField" ),
                                        new FilterItem( REQUIRED,     "Requerido",        "com.paa.requestnow.view.selectors.YesNoSelector"),
                                        new FilterItem( STATE,        "Situação",         "com.paa.requestnow.view.selectors.StateSelector" ),
                                        new FilterItem( TYPE,         "Tipo",             "com.paa.requestnow.view.selectors.handlerSelector" ),
                                        new FilterItem( TYPE_REQUEST, "Tipo Requisição",  "com.paa.requestnow.view.selectors.TypeSelector" ) );
    }
    
}
