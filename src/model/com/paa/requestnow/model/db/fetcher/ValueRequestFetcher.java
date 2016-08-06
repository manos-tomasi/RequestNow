package com.paa.requestnow.model.db.fetcher;

import com.paa.requestnow.model.data.ValueRequest;
import java.sql.ResultSet;

/**
 *
 * @author lucas
 */
public class ValueRequestFetcher 
        implements 
            Fetcher<ValueRequest>
{

    @Override
    public ValueRequest fetch(ResultSet resultSet) throws Exception
    {
        int i = 0;
        
        ValueRequest vr = new ValueRequest();
        
        vr.setId( resultSet.getInt(++i) );
        vr.setField(resultSet.getInt(++i) );
        vr.setValue(resultSet.getString(++i) );
        vr.setRequest(resultSet.getInt(++i) );
        
        return vr;
    }
    
}
