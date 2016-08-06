package com.paa.requestnow.model.db.fetcher;

import com.paa.requestnow.model.data.TypeRoute;
import java.sql.ResultSet;

/**
 *
 * @author lucas
 */
public class TypeRouteFetcher 
        implements 
            Fetcher<TypeRoute>
{
    
    @Override
    public TypeRoute fetch(ResultSet resultSet) throws Exception 
    {
        int i = 0;
        
        TypeRoute tr  = new TypeRoute();

        tr.setId( resultSet.getInt(++i) );
        tr.setSector( ( resultSet.getInt(++i) ) );
        tr.setType( resultSet.getInt(++i) );
        tr.setUser( resultSet.getInt(++i) );
        tr.setSequence( resultSet.getInt(++i) );
        tr.setDays( resultSet.getInt(++i) );
        tr.setState(resultSet.getInt(++i) );
        
        return tr;
    }
    
}
