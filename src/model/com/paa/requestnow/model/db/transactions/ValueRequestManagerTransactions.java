package com.paa.requestnow.model.db.transactions;

import com.paa.requestnow.model.data.Field;
import com.paa.requestnow.model.data.Request;
import com.paa.requestnow.model.data.ValueRequest;
import com.paa.requestnow.model.db.Database;
import com.paa.requestnow.model.db.Schema;
import com.paa.requestnow.model.filter.DefaultFilter;
import com.paa.requestnow.model.filter.ValueRequestFilter;
import java.util.List;

/**
 *
 * @author lucas
 */
public class ValueRequestManagerTransactions 
        extends 
            ManagerTransaction<ValueRequest>
{
     @Override
    public void add(Database db, ValueRequest obj) throws Exception 
    {    
        Schema.ValuesRequests S = Schema.ValuesRequests.table;
        
        String sql = " insert into "          + S.name + 
                     " values "               + "("    + 
                     " DEFAULT"               + ", "   +
                      obj.getField()          + ", "   +
                      obj.getRequest()        + ", "   +
                      obj.getValue()          + ")";
        
        db.executeCommand(sql);
    }

    @Override
    public void update(Database db, ValueRequest obj) throws Exception 
    {
        if( obj == null )
        {
            throw new Exception( "Value Request cannot be null" );
        }
        
        Schema.ValuesRequests S = Schema.ValuesRequests.table;
        
        String sql = " update " + S.name       + " set " +
                        S.columns.FIELD        + " = " + obj.getField()   + ", " +
                        S.columns.REQUEST      + " = " + obj.getRequest() + ", " +
                        S.columns.VALUE        + " = " + obj.getValue()   +
                     " where " + 
                        S.columns.ID           + " = " + obj.getId();
        
        db.executeCommand( sql );
    }

    @Override
    public ValueRequest get(Database db, int id) throws Exception
    {
        Schema.ValuesRequests S = Schema.ValuesRequests.table;
        
        String sql = S.select + " where " + S.columns.ID + " = " + id;
        
        return  db.fetchOne( sql , S.fetcher );
    }    

    @Override
    public List get(Database db) throws Exception
    {
        Schema.ValuesRequests S = Schema.ValuesRequests.table;
        
        String sql = S.select;
        
        return db.fetchAll(sql, S.fetcher );
    }
    
    @Override
    public List get(Database db, DefaultFilter filter) throws Exception
    {
        Schema.ValuesRequests S = Schema.ValuesRequests.table;
        
        StringBuilder sql = new StringBuilder();
        
        sql.append( S.select );
        sql.append( " where true " );
        
        filter.getConditions().forEach( ( key , values ) ->
        {
            String conditions = " and ( "; 
            
            for (int i = 0; i < values.size(); i++) 
            {
                switch( key )
                {                    
                    case ValueRequestFilter.FIELD :
                    {
                        if( values.get(i) instanceof Field )
                        {
                            conditions += S.columns.FIELD + " = " + ((Field)values.get(i)).getId();
                        }
                    }
                    break;
                    
                    case ValueRequestFilter.REQUEST :
                    {
                        if( values.get(i) instanceof Request )
                        {
                            conditions += S.columns.REQUEST + " = " + ((Request)values.get(i)).getId();
                        }
                    }
                    break;
                }
                conditions += i == values.size() - 1 ? " ) " : " or ";    
            }
            
            sql.append(conditions);
        });
        
        return db.fetchAll(sql.toString() , S.fetcher );
    }    
}
