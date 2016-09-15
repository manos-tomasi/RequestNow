package com.paa.requestnow.model.filter;

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
    public static final int REQUIRED     = 1;
    public static final int STATE        = 2;
    public static final int TYPE         = 3;
    public static final int TYPE_REQUEST = 4;
    
    @Override
    protected void createComponents(List<FilterItem> filters) 
    {
        filters.add( new FilterItem( LABEL,        "Label",            "com.paa.requestnow.view.util.MaskTextField" ) );
        filters.add( new FilterItem( REQUIRED,     "Requerido",        "com.paa.requestnow.view.selectors.YesNoSelector")  );
        filters.add( new FilterItem( STATE,        "Situação",         "com.paa.requestnow.view.selectors.StateSelector" ) );
        filters.add( new FilterItem( TYPE,         "Tipo",             "com.paa.requestnow.view.selectors.handlerSelector" ) );
        filters.add( new FilterItem( TYPE_REQUEST, "Tipo Requisição",  "com.paa.requestnow.view.selectors.TypeSelector" ) );
    } 
    
}
