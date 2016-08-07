package com.paa.requestnow.model.db.service;

import com.paa.requestnow.model.data.ValueRequest;
import com.paa.requestnow.model.db.transactions.ValueRequestManagerTransactions;
/**
 *
 * @author lucas
 */
public class ValueRequestManagerService 
        extends
            Manager<ValueRequest,ValueRequestManagerTransactions>
{
    private static ValueRequestManagerService service;
   
    public static ValueRequestManagerService getInstance()
    {
        if( service == null )
        {
            service = new ValueRequestManagerService();
        }
        
        return  service;
    }
    
    private ValueRequestManagerService()
    {
        transactions = new ValueRequestManagerTransactions();
    }
}
