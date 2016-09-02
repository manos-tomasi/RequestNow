package com.paa.requestnow.model.db.transactions;

import com.paa.requestnow.model.data.ActionGroup;
import com.paa.requestnow.model.db.Database;
import com.paa.requestnow.model.db.Schema;
import com.paa.requestnow.model.filter.DefaultFilter;
import java.util.List;

/**
 *
 * @author lucas
 */
public class ActionGroupManagerTransactions
        extends 
            ManagerTransaction<ActionGroup>
{
    
    @Override
    public void add(Database db, ActionGroup obj) throws Exception 
    {
        Schema.ActionsGroups A = Schema.ActionsGroups.table;
        
        String sql = " insert into "          + A.name + 
                     " values "               + "("    + 
                     " DEFAULT"               + ", "   +
                      obj.getAction()         + ", "   +
                      obj.getGroup()          + ")";
        
        db.executeCommand(sql);
    }

    @Override
    public void update(Database db, ActionGroup obj) throws Exception 
    {
        Schema.ActionsGroups A = Schema.ActionsGroups.table;
        
        String sql = " update  "              + A.name + " set "         +
                     A.columns.REF_ACTION     + " = "  + obj.getAction() + ", "   +
                     A.columns.REF_GROUP      + " = "  + obj.getGroup()  + " where " +
                     A.columns.ID             + " = "  + obj.getId();
        
        db.executeCommand(sql);
    }

    @Override
    public ActionGroup get(Database db, int id) throws Exception 
    {
        Schema.ActionsGroups A = Schema.ActionsGroups.table;
        
        String sql = " select  " + A.columns + " from " + A.name + " where " + A.columns.ID + " = " + id;
        
        return db.fetchOne(sql, A.fetcher);
    }

    @Override
    public List get(Database db) throws Exception 
    {
        Schema.ActionsGroups A = Schema.ActionsGroups.table;
        
        String sql = " select  " + A.columns + " from " + A.name;
        
        return db.fetchAll(sql, A.fetcher);
    }

    @Override
    public List get(Database db, DefaultFilter filter) throws Exception 
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
