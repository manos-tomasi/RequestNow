package com.paa.requestnow.model.db.fetcher;

import com.paa.requestnow.model.data.Role;
import java.sql.ResultSet;

/**
 *
 * @author lucas
 */
public class RoleFetcher 
        implements 
            Fetcher<Role>
{
    
    @Override
    public Role fetch(ResultSet resultSet) throws Exception 
    {
        int i = 0;
 
        Role r = new Role();
        
        r.setId( resultSet.getInt( ++i ) );
        r.setName( resultSet.getString( ++i ) );
        r.setState(resultSet.getInt( ++i ) );
        
        return r;
    }
    
}
