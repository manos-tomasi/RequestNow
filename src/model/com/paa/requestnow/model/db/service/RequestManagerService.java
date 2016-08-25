package com.paa.requestnow.model.db.service;

import com.paa.requestnow.model.data.Request;
import com.paa.requestnow.model.data.Type;
import com.paa.requestnow.model.db.Database;
import com.paa.requestnow.model.db.transactions.RequestManagerTransactions;

/**
 *
 * @author lucas
 */
public class RequestManagerService 
        extends 
            Manager<Request, RequestManagerTransactions >
{
    private static RequestManagerService service;
   
    public static RequestManagerService getInstance()
    {
        if( service == null )
        {
            service = new RequestManagerService();
        }
        
        return  service;
    }
    
    private RequestManagerService()
    {
        transactions = new RequestManagerTransactions();
    }
    
    public Type getType( int request ) throws Exception
    {
        Database db = Database.getInstance();
        
        try 
        {
            return transactions.getType( db, request );
        }
        
        finally
        {
            db.release();
        }
    }    
}