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
        Permission p = new Permission();
        
        p.setId( resultSet.getInt(1) );
        p.setActionGroup(resultSet.getInt(2) );
        p.setRole(resultSet.getInt(3) );
        
        return p;
    }
    
}
