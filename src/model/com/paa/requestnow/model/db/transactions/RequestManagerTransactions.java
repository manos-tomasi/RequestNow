package com.paa.requestnow.model.db.transactions;

import com.paa.requestnow.model.data.Option;
import com.paa.requestnow.model.data.Request;
import com.paa.requestnow.model.data.Type;
import com.paa.requestnow.model.data.User;
import com.paa.requestnow.model.db.Database;
import com.paa.requestnow.model.db.Schema;
import com.paa.requestnow.model.filter.DefaultFilter;
import com.paa.requestnow.model.filter.RequestFilter;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author lucas
 */
public class RequestManagerTransactions 
        extends 
            ManagerTransaction<Request>
{
    @Override
    public void add(Database db, Request obj) throws Exception 
    {    
        Schema.Requests S = Schema.Requests.table;
        
        String sql = " insert into "           + S.name + 
                     " values "                + "("    + 
                     " DEFAULT"                + ", "   +
                      obj.getType()            + ", "   +
                      obj.getUser()            + ", "   +
                      obj.getStart()           + ", "   +
                      obj.getEnd()             + ", "   +
                      obj.getState()           + ")";
        
        db.executeCommand(sql);
    }

    @Override
    public void update(Database db, Request obj) throws Exception 
    {
        if( obj == null )
        {
            throw new Exception( "Request cannot be null" );
        }
        
        Schema.Requests S = Schema.Requests.table;
        
        String sql = " update " + S.name       + " set " +
                        S.columns.TYPE         + " = " + obj.getType() + ", " + 
                        S.columns.START        + " = " + obj.getStart()+ ", " +
                        S.columns.END          + " = " + obj.getEnd()  + ", " +
                        S.columns.USER         + " = " + obj.getUser() + ", " +
                        S.columns.STATE        + " = " + obj.getState()+ 
                     " where " + 
                        S.columns.ID           + " = " + obj.getId();
        
        db.executeCommand( sql );
    }

    @Override
    public Request get(Database db, int id) throws Exception 
    {
        Schema.Requests S = Schema.Requests.table;
        
        String sql = S.select + " where " + S.columns.ID + " = " + id;
        
        return  db.fetchOne( sql , S.fetcher );
    }
    
    @Override
    public List get(Database db) throws Exception 
    {
        Schema.Requests S = Schema.Requests.table;
        
        String sql = S.select;
        
        return db.fetchAll(sql, S.fetcher );
    }

    @Override
    public List get(Database db, DefaultFilter filter) throws Exception 
    {
        Schema.Requests S = Schema.Requests.table;
        
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
                    case RequestFilter.STATE :
                    {
                        if(  values.get(i) instanceof Option )
                        {
                            conditions += S.columns.STATE + " = " + ((Option)values.get(i)).getId();
                        }
                    }
                    break;
                    
                    case RequestFilter.TYPE :
                    {
                        if( values.get(i) instanceof Type )
                        {
                            conditions += S.columns.TYPE + " = " + ((Type)values.get(i)).getId();
                        }
                    }
                    break;
                    
                    case RequestFilter.USER :
                    {
                        if( values.get(i) instanceof User )
                        {
                            conditions += S.columns.USER + " = " + ((User)values.get(i)).getId();
                        }
                    }
                    break;
                    
                    case RequestFilter.START :
                    {
                        if( values.get( i ) instanceof Date[] )
                        {
                            Date[] dates = (Date[]) values.get( i );
                            
                            conditions += S.columns.START + 
                                         " between " + 
                                         db.quote( dates[0] ) +
                                         " and " +
                                         db.quote( dates[1] );
                        }
                    }
                    break;
                    
                    case RequestFilter.END :
                    {
                        if( values.get( i ) instanceof Date[] )
                        {
                            Date[] dates = (Date[]) values.get( i );
                            
                            conditions += S.columns.END + 
                                         " between " + 
                                         db.quote( dates[0] ) +
                                         " and " +
                                         db.quote( dates[1] );
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
