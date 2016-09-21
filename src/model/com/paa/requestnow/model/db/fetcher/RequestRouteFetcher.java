package com.paa.requestnow.model.db.fetcher;

import com.paa.requestnow.model.data.RequestRoute;
import java.sql.ResultSet;

/**
 *
 * @author lucas
 */
public class RequestRouteFetcher 
        implements 
            Fetcher<RequestRoute>
{
    
    @Override
    public RequestRoute fetch(ResultSet resultSet) throws Exception 
    {
        int i = 0;
        
        RequestRoute rr = new RequestRoute();
        
        rr.setId( resultSet.getInt(++i) );
        rr.setUser(resultSet.getInt(++i) );
        rr.setTypeRoute(resultSet.getInt(++i) );
        rr.setRequest(resultSet.getInt(++i) );
        rr.setState(resultSet.getInt(++i) );
        rr.setIn(resultSet.getTimestamp(++i) );
        rr.setOut(resultSet.getTimestamp(++i) );
        rr.setInfo(resultSet.getString(++i) );
        rr.setSequence( resultSet.getInt(++i) );
        rr.setSetor( resultSet.getInt(++i) );
        
        return rr;
    }
    
}
