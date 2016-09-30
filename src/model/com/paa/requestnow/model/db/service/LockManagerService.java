package com.paa.requestnow.model.db.service;

import com.paa.requestnow.model.data.Lock;
import com.paa.requestnow.model.db.transactions.LocksManagerTransactions;

/**
 * @author artur
 */
public class LockManagerService 
        extends 
            Manager<Lock, LocksManagerTransactions>
{
    private static LockManagerService service;
   
    public static LockManagerService getInstance()
    {
        if( service == null )
        {
            service = new LockManagerService();
        }
        
        return  service;
    }
    
    private LockManagerService()
    {
        transactions = new LocksManagerTransactions();
    }    
}
