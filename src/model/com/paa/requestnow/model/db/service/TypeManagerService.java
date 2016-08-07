package com.paa.requestnow.model.db.service;

import com.paa.requestnow.model.data.Type;
import com.paa.requestnow.model.db.transactions.TypeManagerTransactions;

/**
 *
 * @author lucas
 */
public class TypeManagerService 
        extends 
            Manager<Type, TypeManagerTransactions>
{
    private static TypeManagerService service;
   
    public static TypeManagerService getInstance()
    {
        if( service == null )
        {
            service = new TypeManagerService();
        }
        
        return  service;
    }
    
    private TypeManagerService()
    {
        transactions = new TypeManagerTransactions();
    }
}
