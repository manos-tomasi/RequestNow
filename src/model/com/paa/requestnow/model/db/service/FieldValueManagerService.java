/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paa.requestnow.model.db.service;

import com.paa.requestnow.model.data.FieldValue;
import com.paa.requestnow.model.db.transactions.FieldValueManagerTransactions;

/**
 *
 * @author lucas
 */
public class FieldValueManagerService 
        extends 
            Manager<FieldValue, FieldValueManagerTransactions >
{
    private static FieldValueManagerService service;
   
    public static FieldValueManagerService getInstance()
    {
        if( service == null )
        {
            service = new FieldValueManagerService();
        }
        
        return  service;
    }
    
    private FieldValueManagerService()
    {
        transactions = new FieldValueManagerTransactions();
    }    
}
