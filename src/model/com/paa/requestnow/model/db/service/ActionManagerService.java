package com.paa.requestnow.model.db.service;

import com.paa.requestnow.model.data.Action;
import com.paa.requestnow.model.db.transactions.ActionManagerTransactions;

/**
 *
 * @author lucas
 */
public class ActionManagerService 
        extends 
            Manager<Action, ActionManagerTransactions>
{
    private static ActionManagerService service;
   
    public static ActionManagerService getInstance()
    {
        if( service == null )
        {
            service = new ActionManagerService();
        }
        
        return  service;
    }
    
    private ActionManagerService()
    {
        transactions = new ActionManagerTransactions();
    }
}
