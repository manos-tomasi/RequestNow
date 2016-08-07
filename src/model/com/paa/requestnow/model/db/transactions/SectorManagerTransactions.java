package com.paa.requestnow.model.db.transactions;

import com.paa.requestnow.model.data.Option;
import com.paa.requestnow.model.filter.DefaultFilter;
import com.paa.requestnow.model.data.Sector;
import com.paa.requestnow.model.filter.SectorFilter;
import com.paa.requestnow.model.db.Database;
import com.paa.requestnow.model.db.Schema;
import java.util.List;

/**
 *
 * @author lucas
 */
public class SectorManagerTransactions
        extends 
            ManagerTransaction<Sector>
{
    @Override
    public void add( Database db, Sector sector ) throws Exception
    {    
        Schema.Sectors S = Schema.Sectors.table;
        
        String sql = " insert into "+ S.name + 
                     " values " +
                     "(" + 
                        "DEFAULT," +
                        db.quote(sector.getName()) + "," +
                        sector.getState() +
                     ")";
        
        db.executeCommand(sql);
    }
    
    @Override
    public List get( Database db ) throws Exception
    {
        Schema.Sectors S = Schema.Sectors.table;
        
        String sql = S.select;
        
        return db.fetchAll(sql, S.fetcher );
    }
    
    @Override
    public List get( Database db, DefaultFilter filter ) throws Exception
    {
        Schema.Sectors S = Schema.Sectors.table;
        
        StringBuilder sql = new StringBuilder();
        
        sql.append( S.select );
        sql.append( " where true " );
        
        filter.getConditions().forEach( ( key , values ) ->
        {
            String conditions = " and ( "; 
            
            for (int i = 0; i < values.size(); i++) 
            {
                switch( key )
                {
                    case SectorFilter.NAME:
                    {
                        conditions += S.columns.NAME + " ilike " + db.quote( "%" + values.get(i) + "%" );
                    }
                    break;
                    
                    case SectorFilter.STATE :
                    {
                        if( values.get( i ) instanceof Option )
                        {
                            Option option = (Option) values.get( i );
                            
                            conditions += S.columns.STATE + " = " + option.getId();
                        }
                    }
                    break;

                }
                conditions += i == values.size() - 1 ? " ) " : " or ";    
            }
            
            sql.append(conditions);
        });
        
        return db.fetchAll(sql.toString() , S.fetcher );
    }
    
    @Override
    public Sector get( Database db, int id ) throws Exception
    {
        Schema.Sectors S = Schema.Sectors.table;
        
        String sql = S.select + " where " + S.columns.ID + " = " + id;
        
        return  db.fetchOne( sql , S.fetcher );
    }    
    
    @Override
    public void update( Database db, Sector sector ) throws Exception
    {
        if( sector == null )
        {
            throw new Exception( "Sector cannot be null" );
        }
        
        Schema.Sectors S = Schema.Sectors.table;
        
        String sql = " update " + S.name       + " set " +
                        S.columns.NAME         + " = " + db.quote( sector.getName() )      + ", " +
                        S.columns.STATE        + " = " + sector.getState()                 + 
                     " where " + 
                        S.columns.ID           + " = " + sector.getId();
        
        db.executeCommand( sql );
    }
}
