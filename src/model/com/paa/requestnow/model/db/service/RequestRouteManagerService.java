package com.paa.requestnow.model.db.service;

import com.paa.requestnow.model.data.RequestRoute;
import com.paa.requestnow.model.db.Database;
import com.paa.requestnow.model.db.transactions.RequestRouteManagerTransaction;
import java.util.List;

/**
 *
 * @author lucas
 */
public class RequestRouteManagerService 
        extends 
            Manager<RequestRoute, RequestRouteManagerTransaction>
{
    private static RequestRouteManagerService service;

    public static RequestRouteManagerService getInstance()
    {
        if( service == null )
        {
            service = new RequestRouteManagerService();
        }

        return  service;
    }

    private RequestRouteManagerService()
    {
        transactions = new RequestRouteManagerTransaction();
    }
    
    public List<RequestRoute> getByRequest( int id ) throws Exception
    {
        Database db = Database.getInstance();
        
        try
        {
            return transactions.getByRequest( db, id );
        }
        
        finally
        {
            db.release();
        }
    }
    
    public boolean approve( RequestRoute dispatch ) throws Exception
    {
        Database db = Database.getInstance();
        
        try 
        {
            transactions.dispatch( db, dispatch );
            return transactions.prepareNext( db, dispatch );
        } 
        
        finally
        {
            db.release();
        }
    }
    
    public boolean disapprove( RequestRoute dispatch ) throws Exception
    {
        Database db = Database.getInstance();
        
        try 
        {
            transactions.dispatch( db, dispatch );
            transactions.cancelUpcoming( db, dispatch );
            return true;
        } 
        
        finally
        {
            db.release();
        }
    }

    public int getDays( RequestRoute requestRoute ) throws Exception
    {
        Database db = Database.getInstance();
        
        try
        {
            return transactions.getDays( db, requestRoute );
        }
        
        finally
        {
            db.release();
        }
    }
    
    public String getJson( RequestRoute dispatch ) throws Exception
    {
        Database db = Database.getInstance();
        try 
        {
            return transactions.getJson( db, dispatch );
        } 
        finally 
        {
            db.release();
        }
    }
}