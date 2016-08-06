package com.paa.requestnow.model.db.fetcher;

import com.paa.requestnow.model.data.Request;
import java.sql.ResultSet;

/**
 *
 * @author lucas
 */
public class RequestFetcher 
        implements 
            Fetcher<Request>
{
    
    @Override
    public Request fetch(ResultSet resultSet) throws Exception 
    {
        int i = 0;
        
        Request request = new Request();
        
        request.setId( resultSet.getInt(++i) );
        request.setType( resultSet.getInt(++i) );
        request.setUser(resultSet.getInt(++i) );
        request.setStart(resultSet.getTimestamp(++i) );
        request.setEnd(resultSet.getTimestamp(++i) );
        
        return request;
    }
    
}
