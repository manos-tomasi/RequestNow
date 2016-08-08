package com.paa.requestnow.view.selectors;

import com.paa.requestnow.model.data.Field;
import com.paa.requestnow.model.data.Option;

/**
 * @author artur
 */
public class HandlerSelector 
     extends 
        ItemSelector<Option>
{
    public HandlerSelector() 
    {
        setItems( new Option( Field.TYPE_TEXT,        Field.TYPES[ Field.TYPE_TEXT ] ),
                  new Option( Field.TYPE_CHOICE,      Field.TYPES[ Field.TYPE_CHOICE ] ),
                  new Option( Field.TYPE_NUMBER,      Field.TYPES[ Field.TYPE_NUMBER ] ),
                  new Option( Field.TYPE_DATE,        Field.TYPES[ Field.TYPE_DATE ] ),
                  new Option( Field.TYPE_FILE,        Field.TYPES[ Field.TYPE_FILE ] ),
                  new Option( Field.TYPE_SIMPLE_TEXT, Field.TYPES[ Field.TYPE_SIMPLE_TEXT ] ) );
   }
}
