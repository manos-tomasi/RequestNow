package com.paa.requestnow.model.db.fetcher;

import com.paa.requestnow.model.data.Group;
import java.sql.ResultSet;

/**
 *
 * @author lucas
 */
public class GroupFetcher 
        implements 
            Fetcher<Group>
{

    @Override
    public Group fetch(ResultSet resultSet) throws Exception 
    {
        Group g = new Group();
        
        g.setId( resultSet.getInt(1) );
        g.setName(resultSet.getString(2) );
        g.setOrigin(resultSet.getString(3) );
        
        return g;
    }
    
}
