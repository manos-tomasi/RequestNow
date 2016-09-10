package com.paa.requestnow.model.db.service;

import com.paa.requestnow.model.data.Core;
import com.paa.requestnow.model.data.TypeRoute;
import com.paa.requestnow.model.db.Database;
import com.paa.requestnow.model.db.transactions.TypeRouteManagerTransactions;
import java.util.List;

/**
 *
 * @author lucas
 */
public class TypeRouteManagerService 
        extends 
            Manager<TypeRoute, TypeRouteManagerTransactions>
{
    private static TypeRouteManagerService service;
   
    public static TypeRouteManagerService getInstance()
    {
        if( service == null )
        {
            service = new TypeRouteManagerService();
        }
        
        return  service;
    }
    
    private TypeRouteManagerService()
    {
        transactions = new TypeRouteManagerTransactions();
    }
    
    public List<TypeRoute> getByType( int id ) throws Exception
    {
        Database db = Database.getInstance();
        
        try
        {
            return  transactions.getByType( db, id );
        }
        finally
        {
            db.release();
        }
    }
         
    public String getJson( TypeRoute route ) throws Exception
    {
        Database db = Database.getInstance();
        
        try
        {
            return transactions.getJson( db, route );
        }
        finally
        {
            db.release();
        }
    }
    
             
    public boolean hasDependences( Core field ) throws Exception
    {
        Database db = Database.getInstance();
        
        try
        {
            return transactions.hasDependences( db, field );
        }
        finally
        {
            db.release();
        }
    }
}