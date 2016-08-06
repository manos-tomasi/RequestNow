package com.paa.requestnow.model.db.fetcher;

import com.paa.requestnow.model.data.Type;
import java.sql.ResultSet;

/**
 *
 * @author lucas
 */
public class TypeFetcher 
        implements 
            Fetcher<Type>
{
    
    @Override
    public Type fetch(ResultSet resultSet) throws Exception 
    {
        int i = 0;
        
        Type type = new Type();
        type.setId( resultSet.getInt(++i));
        type.setName(resultSet.getString(++i));
        type.setState(resultSet.getInt(++i));
        type.setInfo(resultSet.getString(++i));
        
        return type;
    }
    
}
