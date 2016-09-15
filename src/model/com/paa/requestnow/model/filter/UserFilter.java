package com.paa.requestnow.model.filter;

import java.util.List;

/**
 * @author artur
 */
public class UserFilter 
    extends 
        DefaultFilter
{
    public static final int NAME       = 0;
    public static final int CPF        = 1;
    public static final int ROLE       = 2;
    public static final int PHONE      = 3;
    public static final int MAIL       = 4;
    public static final int RG         = 5;
    public static final int GENDER     = 6;
    public static final int BIRTH_DATE = 7;
    public static final int STATE      = 8;
    public static final int SECTOR     = 9;

    @Override
    protected void createComponents( List<FilterItem> filters )
    {
        filters.add( new FilterItem( NAME,       "Nome",              "com.paa.requestnow.view.util.MaskTextField" ) );
        filters.add( new FilterItem( CPF,        "CPF",               "com.paa.requestnow.view.util.MaskTextField" ) );
        filters.add( new FilterItem( ROLE,       "Categoria",         "com.paa.requestnow.view.selectors.RoleSelector" ) );
        filters.add( new FilterItem( PHONE,      "Telefone",          "com.paa.requestnow.view.util.MaskTextField" ) );
        filters.add( new FilterItem( MAIL,       "Email",             "com.paa.requestnow.view.util.MaskTextField" ) );
        filters.add( new FilterItem( RG,         "RG",                "com.paa.requestnow.view.util.MaskTextField" ) );
        filters.add( new FilterItem( GENDER,     "Sexo",              "com.paa.requestnow.view.selectors.GenderSelector" ) );
        filters.add( new FilterItem( BIRTH_DATE, "Ano de Nascimento", "com.paa.requestnow.view.util.DateBetweenField" ) );
        filters.add( new FilterItem( STATE,      "Situação",          "com.paa.requestnow.view.selectors.StateSelector" ) );
        filters.add( new FilterItem( SECTOR,     "Setor",             "com.paa.requestnow.view.selectors.SectorSelector" ) );
    }
}
