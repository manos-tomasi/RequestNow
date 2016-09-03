package com.paa.requestnow.model.db.fetcher;

import com.paa.requestnow.model.data.Permission;
import java.sql.ResultSet;

/**
 *
 * @author lucas
 */
public class PermissionFetcher 
        implements 
            Fetcher<Permission>
{
    
    @Override
    public Permission fetch(ResultSet resultSet) throws Exception 
    {
        int i = 0;
        
        Permission p = new Permission();
        
        p.setId( resultSet.getInt( ++i ) );
        p.setActionGroup(resultSet.getInt( ++i ) );
        p.setRole(resultSet.getInt( ++i ) );
        p.setActive(resultSet.getBoolean( ++i ) );
        p.setDescription(resultSet.getString( ++i ) );
        p.setGroup(resultSet.getInt( ++i ) );
        
        return p;
    }
    
}
