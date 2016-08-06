package com.paa.requestnow.model.data;

import java.util.Arrays;
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

    @Override
    public List<FilterItem> getComponents() 
    {
        return Arrays.asList( new FilterItem( NAME,       "Nome",              "com.paa.requestnow.view.util.MaskTextField" ),
                              new FilterItem( CPF,        "CPF",               "com.paa.requestnow.view.util.MaskTextField" ),
                              new FilterItem( ROLE,       "Categoria",         "com.paa.requestnow.view.selectors.RoleSelector" ),
                              new FilterItem( PHONE,      "Telefone",          "com.paa.requestnow.view.util.MaskTextField" ),
                              new FilterItem( MAIL,       "Email",             "com.paa.requestnow.view.util.MaskTextField" ),
                              new FilterItem( RG,         "RG",                "com.paa.requestnow.view.util.MaskTextField" ),
                              new FilterItem( GENDER,     "Sexo",              "com.paa.requestnow.view.selectors.GenderSelector" ),
                              new FilterItem( BIRTH_DATE, "Ano de Nascimento", "com.paa.requestnow.view.util.DateBetweenField" ),
                              new FilterItem( STATE,      "Situação",          "com.paa.requestnow.view.selectors.StateSelector" ) );
    }
}
