package com.paa.requestnow.model.db.fetcher;

import com.paa.requestnow.model.data.Core;
import com.paa.requestnow.model.data.Field;
import java.sql.ResultSet;

/**
 *
 * @author lucas
 */
public class FieldFetcher 
        implements 
            Fetcher<Field>
{
    
    @Override
    public Field fetch(ResultSet resultSet) throws Exception 
    {
        int i = 0;
        
        Field field = new Field();
        
        field.setId( resultSet.getInt(++i) );
        field.setLabel( resultSet.getString(++i) );
        field.setRequired( Core.FLAG_TRUE == resultSet.getInt(++i) );
        field.setState( resultSet.getInt(++i) );
        field.setType( resultSet.getInt(++i) );
        field.setTypeRequest( resultSet.getInt(++i) );
        field.setSequence(resultSet.getInt(++i) );
        
        return field;
        
    }
    
}
