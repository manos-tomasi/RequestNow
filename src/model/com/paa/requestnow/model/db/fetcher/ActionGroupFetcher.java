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
        ActionGroup ag =  new ActionGroup();
        
        ag.setId( resultSet.getInt(1) );
        ag.setAction(resultSet.getInt(2) );
        ag.setGroup(resultSet.getInt(3) );
        
        return ag;
    }
    
}
