/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paa.requestnow.model.db.transactions;

import com.paa.requestnow.model.data.Role;
import com.paa.requestnow.model.db.Database;
import com.paa.requestnow.model.db.Schema.Roles;
import com.paa.requestnow.model.filter.DefaultFilter;
import java.util.List;

/**
 *
 * @author lucas
 */
public class RoleManagerTransactions
        extends 
            ManagerTransaction<Role>
{
    
    @Override
    public void add( Database db, Role obj ) throws Exception 
    {
        Roles R = Roles.table;
        
        String sql = " insert into " + R.name + 
                     " ( " + R.columns + " ) " + 
                     " values ( " +
                     " default ," + 
                     R.columns.NAME  + " = " + db.quote( obj.getName() ) + ", " +
                     R.columns.STATE + " = " + obj.getState()            + " ) ";
        
        db.executeCommand( sql );
    }

    @Override
    public void update( Database db, Role obj ) throws Exception 
    {
        Roles R = Roles.table;
        
        String sql = " update " + R.name + " set "  +
                     R.columns.NAME  + " = " + db.quote( obj.getName() ) + ", " +
                     R.columns.STATE + " = " + obj.getState() +
                     " where " +
                     R.columns.ID    + " = " + obj.getId();
        
        db.executeCommand( sql );
    }

    @Override
    public Role get( Database db, int id ) throws Exception
    {
        Roles R = Roles.table;
        
        String sql = R.select + 
                     " where " +
                     R.columns.ID + " = " + id;
        
        return db.fetchOne( sql, R.fetcher );
    }

    @Override
    public List get( Database db ) throws Exception 
    {
        Roles R = Roles.table;
        
        String sql = R.select + 
                     " where " +
                     R.columns.STATE + " = " + Role.STATE_ACTIVE;
        
        return db.fetchAll( sql, R.fetcher );
    }

    @Override
    public List get( Database db, DefaultFilter filter ) throws Exception 
    {
        return get( db );
    }
    
}
