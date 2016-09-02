/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paa.requestnow.model.db.transactions;

import com.paa.requestnow.model.data.Group;
import com.paa.requestnow.model.db.Database;
import com.paa.requestnow.model.db.Schema.Groups;
import com.paa.requestnow.model.filter.DefaultFilter;
import java.util.List;

/**
 *
 * @author lucas
 */
public class GroupManagerTransactions
        extends 
            ManagerTransaction<Group>
{
    
    @Override
    public void add( Database db, Group obj ) throws Exception 
    {
        Groups G = Groups.table;
        
        String sql = " insert into " + G.name + 
                     " ( " + G.columns + " ) " + 
                     " values ( " +
                     " default ," + 
                     G.columns.NAME   + " = " + db.quote( obj.getName() )   + ", " +
                     G.columns.ORIGIN + " = " + db.quote( obj.getOrigin() ) + " ) ";

        db.executeCommand( sql );
    }

    @Override
    public void update(Database db, Group obj) throws Exception 
    {
        Groups G = Groups.table;
        
        String sql = " update "       + G.name + " set " +
                     G.columns.NAME   + " = "  + db.quote( obj.getName() )  + ", " +
                     G.columns.ORIGIN + " = "  +db.quote( obj.getOrigin() ) + " where " +
                     G.columns.ID     + " = " + obj.getId();

        db.executeCommand( sql );
    }

    @Override
    public Group get(Database db, int id) throws Exception 
    {
        Groups G = Groups.table;
        
        String sql = " select " + G.columns    + " from " + G.name 
                   + " where "  + G.columns.ID + " = "    + id;

        return db.fetchOne(sql, G.fetcher);
    }

    @Override
    public List get(Database db) throws Exception 
    {
    
        Groups G = Groups.table;
        
        String sql = " select " + G.columns + " from " + G.name;

        return db.fetchAll(sql, G.fetcher);
    }

    @Override
    public List get(Database db, DefaultFilter filter) throws Exception 
    {
        throw new InternalError("Method not implemented!");
    }   
}
