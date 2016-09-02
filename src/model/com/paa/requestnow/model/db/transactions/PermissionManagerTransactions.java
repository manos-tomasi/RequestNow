package com.paa.requestnow.model.db.transactions;

import com.paa.requestnow.model.data.Permission;
import com.paa.requestnow.model.db.Database;
import com.paa.requestnow.model.db.Schema;
import com.paa.requestnow.model.filter.DefaultFilter;
import java.util.List;

/**
 *
 * @author lucas
 */
public class PermissionManagerTransactions
        extends 
            ManagerTransaction<Permission>
{
    
    @Override
    public void add(Database db, Permission obj) throws Exception 
    {
        Schema.Permissions P = Schema.Permissions.table;
        
        String sql = " insert into " + P.name + " ( "         + 
                       P.columns     + " )"   + " values ( "  +
                       obj.getActionGroup() + "," +
                       obj.getRole() + " )";
        
        db.executeCommand(sql);
    }

    @Override
    public void update(Database db, Permission obj) throws Exception 
    {   
        Schema.Permissions P = Schema.Permissions.table;
        
        String sql = " update " + P.name + " set " +
                       P.columns.REF_ACTION_GROUP  + " = " + obj.getActionGroup() + "," +
                       P.columns.REF_ROLE          + " = " + obj.getRole()        +
                     " where " + P.columns.ID      + " = " + obj.getId();
        
        db.executeCommand(sql);
    }

    @Override
    public Permission get(Database db, int id) throws Exception 
    {
        Schema.Permissions P = Schema.Permissions.table;
        
        String sql = " select " + P.columns + " from " + P.name + " where " + P.columns.ID + " = " + id;
        
        return db.fetchOne(sql, P.fetcher);
    }

    @Override
    public List get(Database db) throws Exception 
    {
        Schema.Permissions P = Schema.Permissions.table;
        
        String sql = " select " + P.columns + " from " + P.name;
        
        return db.fetchAll(sql, P.fetcher);
    }

    @Override
    public List get(Database db, DefaultFilter filter) throws Exception 
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
