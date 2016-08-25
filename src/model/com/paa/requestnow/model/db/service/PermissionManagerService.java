package com.paa.requestnow.model.db.service;

import com.paa.requestnow.model.data.Permission;
import com.paa.requestnow.model.db.transactions.PermissionManagerTransactions;

/**
 *
 * @author lucas
 */
public class PermissionManagerService 
        extends 
            Manager<Permission, PermissionManagerTransactions>
{
    private static PermissionManagerService service;
   
    public static PermissionManagerService getInstance()
    {
        if( service == null )
        {
            service = new PermissionManagerService();
        }
        
        return  service;
    }
    
    private PermissionManagerService()
    {
        transactions = new PermissionManagerTransactions();
    }    
}
