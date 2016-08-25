package com.paa.requestnow.model.db.service;

import com.paa.requestnow.model.data.Role;
import com.paa.requestnow.model.db.transactions.RoleManagerTransactions;

/**
 *
 * @author lucas
 */
public class RoleManagerService 
        extends 
            Manager<Role, RoleManagerTransactions>
{
    private static RoleManagerService service;
   
    public static RoleManagerService getInstance()
    {
        if( service == null )
        {
            service = new RoleManagerService();
        }
        
        return  service;
    }
    
    private RoleManagerService()
    {
        transactions = new RoleManagerTransactions();
    }
}