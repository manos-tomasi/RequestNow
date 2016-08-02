package com.paa.requestnow.model.db.transactions;

import com.paa.requestnow.model.data.Sector;
import com.paa.requestnow.model.db.Database;
import com.paa.requestnow.model.db.Schema;
import java.util.List;

/**
 *
 * @author lucas
 */
public class SectorManagerTransactions
{
    public void addSector( Database db, Sector sector ) throws Exception
    {
        Schema.Sectors s = Schema.Sectors.table;
        
        String sql = " insert into "+ s.name + 
                     " values " +
                     "(" + 
                        "DEFAULT," +
                        db.quote(sector.getName()) + "," +
                        sector.getState() +
                     ")";
        
        db.executeCommand(sql);
    }
    
    public List getSectors( Database db, Sector sector ) throws Exception
    {
        Schema.Sectors S = Schema.Sectors.table;
        
        String sql = S.select;
        
        return db.fetchAll(sql, S.fetcher );
    }
}
