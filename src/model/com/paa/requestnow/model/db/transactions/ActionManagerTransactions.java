package com.paa.requestnow.model.db.transactions;

import com.paa.requestnow.model.data.Action;
import com.paa.requestnow.model.db.Database;
import com.paa.requestnow.model.db.Schema;
import com.paa.requestnow.model.filter.DefaultFilter;
import java.util.List;

/**
 *
 * @author lucas
 */
public class ActionManagerTransactions
        extends 
            ManagerTransaction<Action>
{
    
    @Override
    public void add(Database db, Action obj) throws Exception 
    {
        Schema.Actions A = Schema.Actions.table;
        
        String sql = " insert into "          + A.name + 
                     " values "               + "("    + 
                     " DEFAULT"               + ", "   +
                      obj.getName()           + ")";
        
        db.executeCommand(sql);
    }

    @Override
    public void update(Database db, Action obj) throws Exception 
    {
        Schema.Actions A = Schema.Actions.table;
        
        String sql = " update  "              + A.name + " set "         +
                     A.columns.NAME           + " = "  + obj.getName()   + " where " +
                     A.columns.ID             + " = "  + obj.getId();
        
        db.executeCommand(sql);
    }

    @Override
    public Action get(Database db, int id) throws Exception 
    {
        Schema.Actions A = Schema.Actions.table;
        
        String sql = " select  " + A.columns + " from " + A.name + " where " + A.columns.ID + " = " + id;
        
        return db.fetchOne(sql, A.fetcher);
    }

    @Override
    public List get(Database db) throws Exception 
    {
        Schema.Actions A = Schema.Actions.table;
        
        String sql = " select  " + A.columns + " from " + A.name;
        
        return db.fetchAll(sql, A.fetcher);
    }

    @Override
    public List get(Database db, DefaultFilter filter) throws Exception 
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
