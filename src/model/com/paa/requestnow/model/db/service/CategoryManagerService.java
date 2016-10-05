package com.paa.requestnow.model.db.service;

import com.paa.requestnow.model.data.Category;
import com.paa.requestnow.model.db.Database;
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
    
    
    public String getJson( Category category ) throws Exception
    {
        Database db = Database.getInstance();
        
        try
        {
            return transactions.getJson( db, category );
        }
        finally
        {
            db.release();
        }
    }
    
    public boolean hasDependences( Category category ) throws Exception
    {
        Database db = Database.getInstance();
        
        try
        {
            return transactions.hasDependences( db, category );
        }
        finally
        {
            db.release();
        }
    }
    
    public String getDrilldownCategories() throws Exception
    {
        Database db = Database.getInstance();
        
        try
        {
            return transactions.getDrilldownCategories( db );
        }
        
        finally
        {
            db.release();
        }
    }
}
