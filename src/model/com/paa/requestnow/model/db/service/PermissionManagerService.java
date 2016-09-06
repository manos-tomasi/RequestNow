package com.paa.requestnow.model.db.service;

import com.paa.requestnow.model.data.Permission;
import com.paa.requestnow.model.db.Database;
import com.paa.requestnow.model.db.transactions.PermissionManagerTransactions;
import java.util.List;

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
    
    public List<Permission> getPermissionsRole( int role , int group ) throws Exception
    {
        Database db = Database.getInstance();
        
        try
        {
            return transactions.getPermissionsRole( db , role, group );
        }
        
        finally
        {
            db.release();
        }
    }
    
    public List<Permission> getPermissionsRole( int role ) throws Exception
    {
        Database db = Database.getInstance();
        
        try
        {
            return transactions.getPermissionsRole( db , role );
        }
        
        finally
        {
            db.release();
        }
    }
    
    public boolean canPermission( int role, int action, String origin ) throws Exception
    {
        Database db = Database.getInstance();
        
        try
        {
            return transactions.canPermission( db, role, action, origin );
        }
        
        finally
        {
            db.release();
        }
    }
}
