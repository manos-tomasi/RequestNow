package com.paa.requestnow.model.db.transactions;

import com.paa.requestnow.model.ApplicationUtilities;
import com.paa.requestnow.model.data.Option;
import com.paa.requestnow.model.data.Request;
import com.paa.requestnow.model.data.RequestRoute;
import com.paa.requestnow.model.data.Sector;
import com.paa.requestnow.model.data.User;
import com.paa.requestnow.model.db.Database;
import com.paa.requestnow.model.db.Schema;
import com.paa.requestnow.model.filter.DefaultFilter;
import com.paa.requestnow.model.filter.RequestRouteFilter;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author lucas
 */
public class RequestRouteManagerTransaction 
        extends 
            ManagerTransaction<RequestRoute>
{    
    @Override
    public void add(Database db, RequestRoute obj) throws Exception 
    {    
        Schema.RequestsRoutes S = Schema.RequestsRoutes.table;
        
        String sql = " insert into "               + S.name + 
                     " values "                    + "("    + 
                     " DEFAULT"                    + ", "   +
                      obj.getRequest()             + ", "   +
                      obj.getTypeRoute()           + ", "   +
                      db.quote(obj.getIn())        + ", "   +
                      db.quote(obj.getOut())       + ", "   +
                      obj.getState()               + ", "   +
                      db.foreingKey(obj.getUser()) + ", "   +
                      db.quote(obj.getInfo())      + ", "   +
                      obj.getSequence()            + ", "   +
                      db.foreingKey(obj.getSector())+ ") ";
        
        db.executeCommand(sql);
    }

    @Override
    public void update(Database db, RequestRoute obj) throws Exception 
    {
        if( obj == null )
        {
            throw new Exception( "Request Route cannot be null" );
        }
        
        Schema.RequestsRoutes S = Schema.RequestsRoutes.table;
        
        String sql = " update " + S.name       + " set " +
                        S.columns.USER         + " = " + db.foreingKey( obj.getUser() )   + ", " +
                        S.columns.INFO         + " = " + db.quote( obj.getInfo() )        + ", " +
                        S.columns.SECTOR       + " = " + db.foreingKey( obj.getSector() )  + ", " +
                        S.columns.OUT          + " = " + db.quote(obj.getOut())           + ", " +
                        S.columns.STATE        + " = " + obj.getState()                   + ", " +
                        S.columns.SEQUENCE     + " = " + obj.getSequence()                + ", " +
                        S.columns.TYPE_ROUTE   + " = " + obj.getTypeRoute()               + ", " +
                        S.columns.REQUEST      + " = " + obj.getRequest()                 + ", " +
                        S.columns.IN           + " = " + db.quote(obj.getIn())            +  
                     " where " + 
                        S.columns.ID           + " = " + obj.getId();
        
        db.executeCommand( sql );
    }

    @Override
    public RequestRoute get(Database db, int id) throws Exception 
    {
        Schema.RequestsRoutes S = Schema.RequestsRoutes.table;
        
        String sql = S.select + " where " + S.columns.ID + " = " + id;
        
        return  db.fetchOne( sql , S.fetcher );
    }
    
    public List<RequestRoute> getByRequest(Database db, int id) throws Exception 
    {
        Schema.RequestsRoutes S = Schema.RequestsRoutes.table;
        
        String sql = S.select + " where " + S.columns.REQUEST + " = " + id + " ORDER BY sequence ASC";
        
        return  db.fetchAll( sql , S.fetcher );
    }
    
    @Override
    public List get(Database db) throws Exception 
    {
        Schema.RequestsRoutes S = Schema.RequestsRoutes.table;
        
        String sql = S.select;
        
        return db.fetchAll(sql, S.fetcher );
    }

    @Override
    public List get(Database db, DefaultFilter filter) throws Exception 
    {
        Schema.RequestsRoutes S = Schema.RequestsRoutes.table;
        
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
                    case RequestRouteFilter.STATE :
                    {
                        if( values.get(i) instanceof Option )
                        {
                            conditions += S.columns.STATE + " = " + ((Option)values.get(i)).getId();
                        }
                    }
                    break;
                    
                    case RequestRouteFilter.REQUEST :
                    {
                        if( values.get(i) instanceof Request )
                        {
                            conditions += S.columns.REQUEST + " = " + ((Request)values.get(i)).getId();
                        }
                    }
                    break;
                    
                    case RequestRouteFilter.USER :
                    {
                        if( values.get(i) instanceof User )
                        {
                            conditions += S.columns.USER + " = " + ((User)values.get(i)).getId();
                        }
                    }
                    break;
                    
                    case RequestRouteFilter.SECTOR :
                    {
                        if( values.get(i) instanceof Sector )
                        {
                            conditions += S.columns.SECTOR + " = " + ((Sector)values.get(i)).getId();
                        }
                    }
                    break;
                    
                    case RequestRouteFilter.IN :
                    {
                        if( values.get( i ) instanceof Date[] )
                        {
                            Date[] dates = (Date[]) values.get( i );
                            
                            conditions += S.columns.IN + 
                                         " between " + 
                                         db.quote( dates[0] ) +
                                         " and " +
                                         db.quote( dates[1] );
                        }
                    }
                    break;
                    
                    case RequestRouteFilter.OUT :
                    {
                        if( values.get( i ) instanceof Date[] )
                        {
                            Date[] dates = (Date[]) values.get( i );
                            
                            conditions += S.columns.OUT + 
                                         " between " + 
                                         db.quote( dates[0] ) +
                                         " and " +
                                         db.quote( dates[1] );
                        }
                    }
                    break;
                    
                    case RequestRouteFilter.SEQUENCE :
                    {
                        conditions += S.columns.SEQUENCE + " = " + values.get( i );
                    }
                    break;
                    
                    case RequestRouteFilter.MYRESPONSE :
                    {
                        if( values.get(i) instanceof Option )
                        {
                            if( ((Option)values.get(i)) == Option.YES )
                            {
                                User user = ApplicationUtilities.getInstance().getActiveUser();
                                conditions += S.columns.USER   + " = " + user.getId()     + 
                                   " OR ( " + S.columns.SECTOR + " = " + user.getSector() + 
                                   " AND "  + S.columns.USER   + " IS NULL "              + " ) ";
                            }
                        }
                    }
                    break;
                }
                conditions += i == values.size() - 1 ? " ) " : " or ";    
            }
            
            sql.append(conditions);
        });
        
        return db.fetchAll( sql.toString() , S.fetcher );
    }
    
    public RequestRoute getOne(Database db, DefaultFilter filter) throws Exception 
    {
        Schema.RequestsRoutes S = Schema.RequestsRoutes.table;
        
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
                    case RequestRouteFilter.STATE :
                    {
                        if( values.get(i) instanceof Option )
                        {
                            conditions += S.columns.STATE + " = " + ((Option)values.get(i)).getId();
                        }
                    }
                    break;
                    
                    case RequestRouteFilter.REQUEST :
                    {
                        if( values.get(i) instanceof Request )
                        {
                            conditions += S.columns.REQUEST + " = " + ((Request)values.get(i)).getId();
                        }
                    }
                    break;
                    
                    case RequestRouteFilter.USER :
                    {
                        if( values.get(i) instanceof User )
                        {
                            conditions += S.columns.USER + " = " + ((User)values.get(i)).getId();
                        }
                    }
                    break;
                    
                    case RequestRouteFilter.SECTOR :
                    {
                        if( values.get(i) instanceof Sector )
                        {
                            conditions += S.columns.SECTOR + " = " + ((Sector)values.get(i)).getId();
                        }
                    }
                    break;
                    
                    case RequestRouteFilter.IN :
                    {
                        if( values.get( i ) instanceof Date[] )
                        {
                            Date[] dates = (Date[]) values.get( i );
                            
                            conditions += S.columns.IN + 
                                         " between " + 
                                         db.quote( dates[0] ) +
                                         " and " +
                                         db.quote( dates[1] );
                        }
                    }
                    break;
                    
                    case RequestRouteFilter.OUT :
                    {
                        if( values.get( i ) instanceof Date[] )
                        {
                            Date[] dates = (Date[]) values.get( i );
                            
                            conditions += S.columns.OUT + 
                                         " between " + 
                                         db.quote( dates[0] ) +
                                         " and " +
                                         db.quote( dates[1] );
                        }
                    }
                    break;
                    
                    case RequestRouteFilter.SEQUENCE :
                    {
                        conditions += S.columns.SEQUENCE + " = " + values.get( i );
                    }
                    break;
                    
                    case RequestRouteFilter.MYRESPONSE :
                    {
                        if( values.get(i) instanceof User )
                        {
                            if( ((Option)values.get(i)) == Option.YES )
                            {
                                User user = ApplicationUtilities.getInstance().getActiveUser();
                                conditions += S.columns.USER   + " = " + user.getId()     + 
                                   " OR ( " + S.columns.SECTOR + " = " + user.getSector() + 
                                   " AND "  + S.columns.USER   + " IS NULL "              + ") ";
                            }
                        }
                    }
                }
                conditions += i == values.size() - 1 ? " ) " : " or ";    
            }
            
            sql.append(conditions);
        });
        
        return db.fetchOne( sql.toString() , S.fetcher );
    }
    
    /**
     * 
     * @param db
     * @param dispatch
     * @return retorna true se é o ultimo despacho
     * @throws Exception 
     */
    public void dispatch( Database db, RequestRoute dispatch ) throws Exception
    {
        //Atualiza o despacho
        update( db, dispatch );
        
        // buscar o proximo despacho
        RequestRouteFilter filter = new RequestRouteFilter();
        
        filter.addCondition( RequestRouteFilter.REQUEST  , com.paa.requestnow.model.ModuleContext.getInstance().getRequestManager().get( dispatch.getRequest() ) );
        filter.addCondition( RequestRouteFilter.SEQUENCE , dispatch.getSequence() + 1 );
    }   
    
    public boolean prepareNext( Database db, RequestRoute dispatch ) throws Exception
    {
        // buscar o proximo despacho
        RequestRouteFilter filter = new RequestRouteFilter();
        
        filter.addCondition( RequestRouteFilter.REQUEST  , com.paa.requestnow.model.ModuleContext.getInstance().getRequestManager().get( dispatch.getRequest() ) );
        filter.addCondition( RequestRouteFilter.SEQUENCE , dispatch.getSequence() + 1 );
        
        RequestRoute nextDispatch = getOne( db , filter );
        
        if( nextDispatch != null )
        {
            //atualiza o despacho
            nextDispatch.setIn( new Timestamp( System.currentTimeMillis() ) );
            nextDispatch.setState( RequestRoute.STOPED );
            
            update(db, nextDispatch );
            
            return false;
        }
        else
        {
            return true;
        }
    }
    
    public void cancelUpcoming( Database db, RequestRoute dispatch ) throws Exception
    {
        // buscar o proximos despachos
        RequestRouteFilter filter = new RequestRouteFilter();
        
        filter.addCondition( RequestRouteFilter.REQUEST  , com.paa.requestnow.model.ModuleContext.getInstance().getRequestManager().get( dispatch.getRequest() ) );
        
        List<RequestRoute> dispatchs = get( db , filter );
        
        String info = "Cancelado por reprovação no despacho: " + dispatch.getSequence() + ", pelo usuário " + dispatch.getUser();
        
        for (RequestRoute route : dispatchs ) 
        {
            if( route.getSequence() > dispatch.getSequence() )
            {
                route.setOut( new Timestamp( System.currentTimeMillis() ) );
                route.setInfo( info );
                route.setState( RequestRoute.CANCELED );
                update( db, route );
            }
        }
    }

    public int getDays( Database db, RequestRoute requestRoute ) throws Exception
    {
        if( requestRoute == null )
        {
            throw new IllegalArgumentException( "RequestRoute cannot be null!" );
        }
        
        String sql = "SELECT ( SELECT SUM(b.days) "       +
                                "FROM requests_routes a " +
                               "INNER JOIN types_routes b ON ( a.ref_type_route = b.id ) " +
                              " WHERE a.ref_request = r.ref_request " + 
                                " AND a.sequence   <= t.sequence ) AS days " +
                      "FROM requests_routes r " +
                     "INNER JOIN types_routes t ON ( r.ref_type_route = t.id ) " +
                     "INNER JOIN requests x     ON ( x.id = r.ref_request ) " +
                     "WHERE r.id = " + requestRoute.getId();
        
        ResultSet rs = db.query(sql);
        
        if( rs.next() )
        {
            return rs.getInt(1);
        }
        
        return 0;
    }
    
    public String getJson( Database db, RequestRoute dispatch ) throws Exception
    {
        if ( dispatch == null )
        {
            throw new IllegalArgumentException( "Request cannot be null!" );
        }
         
        return db.queryString( "select getJson( " + dispatch.getRequest() + ", 'dispatch' )" );
    }
}