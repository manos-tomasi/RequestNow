package com.paa.requestnow.model.db.service;

import com.paa.requestnow.model.data.Category;
import com.paa.requestnow.model.db.transactions.CategoryManagerTransactions;

/**
 *
 * @author lucas
 */
public class CategoryManagerService 
        extends 
            Manager<Category, CategoryManagerTransactions >
{
        private static CategoryManagerService service;
   
    public static CategoryManagerService getInstance()
    {
        if( service == null )
        {
            service = new CategoryManagerService();
        }
        
        return  service;
    }
    
    private CategoryManagerService()
    {
        transactions = new CategoryManagerTransactions();
    }
}
