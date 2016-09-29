package com.paa.requestnow.model.db.service;

import com.paa.requestnow.model.data.Core;
import com.paa.requestnow.model.data.Type;
import com.paa.requestnow.model.db.Database;
import com.paa.requestnow.model.db.transactions.TypeManagerTransactions;
import java.util.List;

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
    
    public List<Type> getTypesCategory( int categoryId ) throws Exception
    {
        Database db = Database.getInstance();
    
        try
        {
            return transactions.getTypesCategory( db , categoryId );
        }

        finally
        {
            db.release();
        }
    }
    
    public List<Type> getTypesCategoryWithRoute( int categoryId ) throws Exception
    {
        Database db = Database.getInstance();
    
        try
        {
            return transactions.getTypesCategoryWithRoute( db , categoryId );
        }

        finally
        {
            db.release();
        }
    }
     
    public String getJson( Type type ) throws Exception
    {
        Database db = Database.getInstance();
        
        try
        {
            return transactions.getJson( db, type );
        }
        finally
        {
            db.release();
        }
    }
    
    public boolean hasDependences( Core type ) throws Exception
    {
        Database db = Database.getInstance();
        
        try
        {
            return transactions.hasDependences( db, type );
        }
        finally
        {
            db.release();
        }
    }
}
