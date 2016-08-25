/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paa.requestnow.model.db.service;

import com.paa.requestnow.model.data.ActionGroup;
import com.paa.requestnow.model.db.transactions.ActionGroupManagerTransactions;

/**
 *
 * @author lucas
 */
public class ActionGroupManagerService 
        extends 
            Manager<ActionGroup, ActionGroupManagerTransactions>
{
    private static ActionGroupManagerService service;
   
    public static ActionGroupManagerService getInstance()
    {
        if( service == null )
        {
            service = new ActionGroupManagerService();
        }
        
        return  service;
    }
    
    private ActionGroupManagerService()
    {
        transactions = new ActionGroupManagerTransactions();
    }    
}
