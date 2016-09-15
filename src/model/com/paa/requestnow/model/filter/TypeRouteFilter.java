package com.paa.requestnow.model.filter;

import java.util.List;

/**
 *
 * @author lucas
 */
public class TypeRouteFilter 
        extends 
            DefaultFilter
{
    public static final int SECTOR = 0;
    public static final int TYPE   = 1;
    public static final int STATE  = 2;
    public static final int USER   = 3;

    @Override
    protected void createComponents( List<FilterItem> filters )
    {
        filters.add( new FilterItem( SECTOR, "Setor",   "com.paa.requestnow.view.selectors.SectorSelector" ) );
        filters.add( new FilterItem( TYPE,   "Tipo",    "com.paa.requestnow.view.selectors.TypeSelector" ) );
        filters.add( new FilterItem( STATE,  "Situação","com.paa.requestnow.view.selectors.StateSelector" ) );
        filters.add( new FilterItem( USER,   "Usuário", "com.paa.requestnow.view.selectors.UserSelector" ) );
    }
}
