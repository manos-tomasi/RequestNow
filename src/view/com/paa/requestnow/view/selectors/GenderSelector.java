package com.paa.requestnow.view.selectors;

import com.paa.requestnow.model.data.Option;
import com.paa.requestnow.model.data.User;

/**
 * @author artur
 */
public class GenderSelector
    extends 
        ItemSelector<Option>
{
    public GenderSelector() 
    {
        super( "Selecione um Sexo",
                new Option( User.MALE,   User.GENDER[ User.MALE ] ),
                new Option( User.FEMALE, User.GENDER[ User.FEMALE ] ) );
    }
}
