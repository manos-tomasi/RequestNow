package com.paa.requestnow.view.selectors;

import com.paa.requestnow.model.data.Option;
import com.paa.requestnow.model.data.User;

/**
 * @author artur
 */
public class RoleSelector 
    extends 
        ItemSelector<Option>
{
    public RoleSelector() 
    {
        super( "Selecione uma Categoria",
                new Option( User.ROLE_ADMIN,    User.ROLE[ User.ROLE_ADMIN ] ),
                new Option( User.ROLE_OPERATOR, User.ROLE[ User.ROLE_OPERATOR ] ) );
    }
}
