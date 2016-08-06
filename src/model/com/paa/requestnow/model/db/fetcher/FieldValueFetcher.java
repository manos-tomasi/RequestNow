package com.paa.requestnow.model.db.fetcher;

import com.paa.requestnow.model.data.FieldValue;
import java.sql.ResultSet;

/**
 *
 * @author lucas
 */
public class FieldValueFetcher 
        implements 
            Fetcher<FieldValue>
{
    
    @Override
    public FieldValue fetch(ResultSet resultSet) throws Exception 
    {
        int i = 0;
        
        FieldValue fv = new FieldValue();
        
        fv.setId( resultSet.getInt(++i) );
        fv.setState(resultSet.getInt(++i) );
        fv.setField(resultSet.getInt(++i) );
        fv.setValue(resultSet.getString(++i) );
        
        return fv;
    }
    
}
