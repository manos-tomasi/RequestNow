package com.paa.requestnow.model.db.service;

import com.paa.requestnow.model.data.DefaultFilter;
import com.paa.requestnow.model.data.User;
import com.paa.requestnow.model.data.UserFilter;
import com.paa.requestnow.model.db.Database;
import com.paa.requestnow.model.db.transactions.UserManagerTransactions;
import java.util.List;

/**
 * @author artur
 */
public class UserManagerService 
    implements 
            Manager<User>
{
    private static UserManagerTransactions transactions;
    private static UserManagerService service;
    
    public static UserManagerService getInstance()
    {
        if( service == null )
        {
            service = new UserManagerService();
        }
        
        return  service;
    }
    
    private UserManagerService()
    {
        transactions = new UserManagerTransactions();
    }
    
    
    @Override
    public void addValue( User user) throws Exception 
    {
        Database db = Database.getInstance();
        
        try
        {
            transactions.addUser( db, user );
        }
        
        finally
        {
            db.release();
        }
    }

    @Override
    public void deleteValue( User user ) throws Exception 
    {
        Database db = Database.getInstance();
        
        try
        {
            transactions.deleteUser( db, user );
        }
        
        finally
        {
            db.release();
        }
    }

    @Override
    public void updateValue( User user ) throws Exception 
    {
        Database db = Database.getInstance();
        
        try
        {
            transactions.updateUser( db, user );
        }
        
        finally
        {
            db.release();
        }
    }

    @Override
    public User getValue( int userId ) throws Exception 
    {
        Database db = Database.getInstance();
        
        try
        {
            return transactions.getUser( db, userId );
        }
        
        finally
        {
            db.release();
        }
    }

    @Override
    public List<User> getValues() throws Exception 
    {
        Database db = Database.getInstance();
        
        try
        {
            return transactions.getUsers( db, false );
        }
        
        finally
        {
            db.release();
        }
    }

    public List<User> getValues( boolean showInactives ) throws Exception 
    {
        Database db = Database.getInstance();
        
        try
        {
            return transactions.getUsers( db, showInactives );
        }
        
        finally
        {
            db.release();
        }
    }

    public List<User> getValues( UserFilter filter ) throws Exception 
    {
        Database db = Database.getInstance();
        
        try
        {
            return transactions.getUsers( db, filter );
        }
        
        finally
        {
            db.release();
        }
    }

    public User getValue( String name ) throws Exception 
    {
        Database db = Database.getInstance();
        
        try
        {
            return transactions.getUserByName( db, name );
        }
        
        finally
        {
            db.release();
        }
    }
 
    public User getUserByLogin( String login, String password ) throws Exception 
    {
        Database db = Database.getInstance();
        
        try
        {
            return transactions.getUserByLogin( db, login, password );
        }
        
        finally
        {
            db.release();
        }
    }

    @Override
    public List<String> isUnique( User user ) throws Exception
    {
        Database db = Database.getInstance();
        
        try
        {
           return transactions.isUnique( db, user );
        }
        
        finally
        {
            db.release();
        }
    }

    @Override
    public List<User> getValues(DefaultFilter filter) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
