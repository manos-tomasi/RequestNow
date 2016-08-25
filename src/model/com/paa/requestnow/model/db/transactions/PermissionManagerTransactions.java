package com.paa.requestnow.model.db.transactions;

import com.paa.requestnow.model.data.Permission;
import com.paa.requestnow.model.db.Database;
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
    public void add(Database db, Permission obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Database db, Permission obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Permission get(Database db, int id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List get(Database db) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List get(Database db, DefaultFilter filter) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
