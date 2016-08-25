/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paa.requestnow.model.db.transactions;

import com.paa.requestnow.model.data.Role;
import com.paa.requestnow.model.db.Database;
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
    public void add(Database db, Role obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Database db, Role obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Role get(Database db, int id) throws Exception {
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
