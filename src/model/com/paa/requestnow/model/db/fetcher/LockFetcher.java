package com.paa.requestnow.model.db.fetcher;

import com.paa.requestnow.model.data.Lock;
import java.sql.ResultSet;

/**
 *
 * @author lucas
 */
public class LockFetcher 
        implements 
            Fetcher<Lock>
{
    
    @Override
    public Lock fetch(ResultSet resultSet) throws Exception 
    {
        int i = 0;
        
        Lock lock = new Lock();
        
        lock.setId( resultSet.getInt(++i) );
        lock.setUser( resultSet.getInt(++i) );
        lock.setRoute( resultSet.getInt(++i) );
        lock.setDate( resultSet.getTimestamp(++i) );
        
        return  lock;
    }
    
}
