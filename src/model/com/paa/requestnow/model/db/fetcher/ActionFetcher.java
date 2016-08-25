package com.paa.requestnow.model.db.fetcher;

import com.paa.requestnow.model.data.Action;
import java.sql.ResultSet;

/**
 *
 * @author lucas
 */
public class ActionFetcher 
        implements 
            Fetcher<Action>
{
    
    @Override
    public Action fetch(ResultSet resultSet) throws Exception 
    {
        Action a = new Action();
        
        a.setId( resultSet.getInt(1) );
        a.setName(resultSet.getString(2) );
        
        return a;
    }
    
}
