package com.paa.requestnow.model.db.fetcher;

import com.paa.requestnow.model.data.ActionGroup;
import java.sql.ResultSet;

/**
 *
 * @author lucas
 */
public class ActionGroupFetcher 
        implements 
            Fetcher<ActionGroup>
{
    
    @Override
    public ActionGroup fetch(ResultSet resultSet) throws Exception 
    {
        int i = 0;

        ActionGroup ag =  new ActionGroup();        
        
        ag.setId( resultSet.getInt( ++i ) );
        ag.setAction(resultSet.getInt( ++i ) );
        ag.setGroup(resultSet.getInt( ++i ) );
        
        return ag;
    }
    
}
