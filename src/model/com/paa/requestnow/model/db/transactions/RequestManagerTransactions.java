package com.paa.requestnow.model.db.transactions;

import com.paa.requestnow.model.data.Option;
import com.paa.requestnow.model.data.Request;
import com.paa.requestnow.model.data.Type;
import com.paa.requestnow.model.data.User;
import com.paa.requestnow.model.db.Database;
import com.paa.requestnow.model.db.Schema;
import com.paa.requestnow.model.db.Schema.Requests;
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
    public void add(Database db, Request r) throws Exception 
    {    
        if ( r == null )
        {
            throw new IllegalArgumentException( "Request cannot be null!" );
        }
        
        if ( r.getId() != 0 )
        {
            throw new IllegalArgumentException( "Request id cannot be 0 !" );
        }
        
        Requests S = Requests.table;
        
        r.setId( db.nextId( S.name ) );
         
        String sql = " insert into "         + S.name + 
                     " values "              + "("    + 
                      r.getId()              + ", "   +
                      r.getType()            + ", "   +
                      r.getUser()            + ", "   +
                      db.quote(r.getStart()) + ", "   +
                      db.quote(r.getEnd())   + ", "   +
                      r.getState()           + ")";
        
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
                        S.columns.TYPE         + " = " + obj.getType()           + ", " + 
                        S.columns.START        + " = " + db.quote(obj.getStart())+ ", " +
                        S.columns.END          + " = " + db.quote(obj.getEnd() ) + ", " +
                        S.columns.USER         + " = " + obj.getUser()           + ", " +
                        S.columns.STATE        + " = " + obj.getState()          + 
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
                    
                    case RequestFilter.OPENED :
                    {
                        if( values.get(i) instanceof Option )
                        {
                            if( ((Option)values.get(i)) == Option.YES )
                            {
                                conditions += S.columns.END + " is null ";
                            }
                            else
                            {
                                conditions += S.columns.END + " is not null ";
                            }
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
    
    public Type getType( Database db, int request ) throws Exception
    {
        Schema.Types T = Schema.Types.alias( "t" );
        
        String sql = "select * from gettypeByRequest(" + request+ ")";
        
        return db.fetchOne( sql, T.fetcher );
    }
}
