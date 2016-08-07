package com.paa.requestnow.model.filter;

import java.util.Arrays;
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
    public static final int USER   = 2;
    public static final int STATE  = 3;

    @Override
    public List<FilterItem> getComponents() {
                return Arrays.asList(   new FilterItem( SECTOR, "Setor",   "com.paa.requestnow.view.selectors.SectorSelector" ),
                                        new FilterItem( TYPE,   "Tipo",    "com.paa.requestnow.view.selectors.TypeSelector" ),
                                        new FilterItem( STATE,  "Situação","com.paa.requestnow.view.selectors.StateSelector" ),
                                        new FilterItem( USER,   "Usuário", "com.paa.requestnow.view.selectors.UserSelector" ) );
    }
}
