/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paa.requestnow.model.db.service;

import com.paa.requestnow.model.data.Group;
import com.paa.requestnow.model.db.transactions.GroupManagerTransactions;

/**
 *
 * @author lucas
 */
public class GroupManagerService 
        extends 
            Manager<Group, GroupManagerTransactions>
{
    private static GroupManagerService service;
   
    public static GroupManagerService getInstance()
    {
        if( service == null )
        {
            service = new GroupManagerService();
        }
        
        return  service;
    }
    
    private GroupManagerService()
    {
        transactions = new GroupManagerTransactions();
    }    
}
