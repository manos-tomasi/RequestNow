package com.paa.requestnow.model.db.fetcher;

import com.paa.requestnow.model.data.Sector;
import java.sql.ResultSet;

/**
 *
 * @author lucas
 */
public class SectorFetcher
    implements 
        Fetcher<Sector>
{

    @Override
    public Sector fetch( ResultSet resultSet ) throws Exception 
    {
        int i = 0;
        
        Sector sector = new Sector();
        
        sector.setId( resultSet.getInt(++i) );
        sector.setName( resultSet.getString(++i) );
        sector.setState( resultSet.getInt(++i) );
        
        return sector;
    }

}
