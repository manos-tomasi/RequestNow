package com.paa.requestnow.model.db.service;

import com.paa.requestnow.model.data.Field;
import com.paa.requestnow.model.db.Database;
import com.paa.requestnow.model.db.transactions.FieldManagerTransactions;
import java.util.List;

/**
 *
 * @author lucas
 */
public class FieldManagerService 
        extends 
            Manager<Field, FieldManagerTransactions>
{
    private static FieldManagerService service;
   
    public static FieldManagerService getInstance()
    {
        if( service == null )
        {
            service = new FieldManagerService();
        }
        
        return  service;
    }
    
    private FieldManagerService()
    {
        transactions = new FieldManagerTransactions();
    }
    
    public List<Field> getFieldsType( int fieldId ) throws Exception
    {
        Database db = Database.getInstance();
    
        try
        {
            return transactions.getFieldsType( db , fieldId );
        }

        finally
        {
            db.release();
        }
    }
}
