/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paa.requestnow.model.db.transactions;

import com.paa.requestnow.model.data.ActionGroup;
import com.paa.requestnow.model.db.Database;
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
    public void add(Database db, ActionGroup obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Database db, ActionGroup obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ActionGroup get(Database db, int id) throws Exception {
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
