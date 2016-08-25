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
        Role r = new Role();
        
        r.setId( resultSet.getInt(10) );
        r.setName(resultSet.getString(2) );
        r.setState(resultSet.getInt(3) );
        
        return r;
    }
    
}
