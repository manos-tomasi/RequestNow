package com.paa.requestnow.model.db.service;

import com.paa.requestnow.model.data.Field;
import com.paa.requestnow.model.db.transactions.FieldManagerTransactions;

/**
 *
 * @author lucas
 */
public class FieldManagerService 
        extends 
            Manager<Field, FieldManagerTransactions>
{
    private static FieldManagerService service;
   
    public static FieldManagerService getInstance()
    {
        if( service == null )
        {
            service = new FieldManagerService();
        }
        
        return  service;
    }
    
    private FieldManagerService()
    {
        transactions = new FieldManagerTransactions();
    }
}
