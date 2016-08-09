package com.paa.requestnow.model.db.service;

import com.paa.requestnow.model.data.Sector;
import com.paa.requestnow.model.data.User;
import com.paa.requestnow.model.db.Database;
import com.paa.requestnow.model.db.transactions.UserManagerTransactions;
import java.util.List;

public class UserManagerService 
        extends 
            Manager<User,UserManagerTransactions>
{
    private static UserManagerService service;
   
    public static UserManagerService getInstance()
    {
        if( service == null )
        {
            service = new UserManagerService();
        }
        
        return  service;
    }
    public UserManagerService()
    {
        transactions = new UserManagerTransactions();
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

    public List<User> getUsersBySector( Sector sector ) throws Exception 
    {
        Database db = Database.getInstance();
        
        try
        {
            return transactions.getUsersBySector( db, sector );
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
}
