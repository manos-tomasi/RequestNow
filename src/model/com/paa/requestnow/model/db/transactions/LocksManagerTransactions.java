package com.paa.requestnow.model.db.transactions;

import com.paa.requestnow.model.data.Lock;
import com.paa.requestnow.model.db.Database;
import com.paa.requestnow.model.db.Schema;
import com.paa.requestnow.model.filter.DefaultFilter;
import java.util.List;

/**
 *
 * @author lucas
 */
public class LocksManagerTransactions 
        extends 
            ManagerTransaction<Lock>
{
    @Override
    public void add(Database db, Lock obj) throws Exception 
    {    
        Schema.Locks S = Schema.Locks.table;
        
        String sql = " insert into "          + S.name + 
                     " values "               + "("    + 
                     " DEFAULT"               + ", "   +
                      " now() "               + ", "   +
                      obj.getRoute()          + ", "   +
                      obj.getUser()           + ")";
        
        db.executeCommand(sql);
    }
    
    @Override
    public void delete( Database db, Lock obj ) throws Exception
    {
        Schema.Locks S = Schema.Locks.table;
        
        String sql = " delete from " +
                     S.name + 
                    " where "  + 
                     S.columns.ROUTE  + " = "  + obj.getRoute() +
                    " and  " +
                    S.columns.USER    + " = " + obj.getUser() ;
        
        db.executeCommand( sql);
    }

    @Override
    public void update(Database db, Lock obj) throws Exception 
    {
        throw new Exception("Not implemented");
    }

    @Override
    public Lock get(Database db, int id) throws Exception
    {
        Schema.Locks S = Schema.Locks.table;
        
        String sql = S.select + " where " + S.columns.ROUTE + " = " + id;
        
        return  db.fetchOne( sql , S.fetcher );
    }    

    @Override
    public List get(Database db) throws Exception
    {
        Schema.Locks S = Schema.Locks.table;
        
        String sql = S.select;
        
        return db.fetchAll(sql, S.fetcher );
    }
    

    @Override
    public List get(Database db, DefaultFilter filter) throws Exception
    {
        throw new Exception("Not implemented");
    }
}
