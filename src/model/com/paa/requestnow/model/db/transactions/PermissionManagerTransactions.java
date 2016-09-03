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
        
        String sql = " insert into " + P.name        + " ( " +
                       P.columns.REF_ACTION_GROUP    + ",  " +
                       P.columns.REF_ROLE            + ",  " +
                       P.columns.ACTIVE              + " ) " + " values ( "  +
                       obj.getActionGroup()          + ","   +
                       obj.getRole()                 + ","   + 
                       ( obj.isActive() ? "'t'" : "'f'" )+ ")";
        
        db.executeCommand(sql);
    }

    @Override
    public void update(Database db, Permission obj) throws Exception 
    {   
        Schema.Permissions P = Schema.Permissions.table;
        
        String sql = " update " + P.name + " set " +
                       P.columns.REF_ACTION_GROUP  + " = " + obj.getActionGroup() + "," +
                       P.columns.REF_ROLE          + " = " + obj.getRole()        + "," +
                       P.columns.ACTIVE            + " = " + obj.isActive()       +
                     " where " + P.columns.ID      + " = " + obj.getId();
        
        db.executeCommand(sql);
    }

    @Override
    public Permission get(Database db, int id) throws Exception 
    {
        Schema.Permissions P = Schema.Permissions.table;
        
        String sql = " select " + P.columns + "," + " from " + P.name + " where " + P.columns.ID + " = " + id;
        
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
    
    public List getPermissionsRole( Database db, int role, int group ) throws Exception
    {
        Schema.Permissions   P = Schema.Permissions.table;
        Schema.ActionsGroups A = Schema.ActionsGroups.table;
        
        String sql =  P.select                   + " where " + 
                      P.columns.REF_ROLE         + " = "     + role                + " and "       +
                      P.columns.REF_ACTION_GROUP + " in ( "  +  " select "         + A.columns.ID  + " from " +
                      A.name                     + " where " + A.columns.REF_GROUP + " = " + group + "  ) ";
        
        return db.fetchAll(sql, P.fetcher);
    }
    
    public List getPermissionsRole( Database db, int role ) throws Exception
    {
        Schema.Permissions   P = Schema.Permissions.table;
        Schema.ActionsGroups A = Schema.ActionsGroups.table;
        
        String sql =  P.select            + " where "  + 
                      P.columns.REF_ROLE  + " = "      + role ;
        
        return db.fetchAll(sql, P.fetcher);
    }
}
