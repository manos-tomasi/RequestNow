package com.paa.requestnow.model.filter;

import static com.paa.requestnow.model.filter.SectorFilter.NAME;
import static com.paa.requestnow.model.filter.SectorFilter.STATE;
import java.util.List;

/**
 *
 * @author lucas
 */
public class SectorFilter 
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
