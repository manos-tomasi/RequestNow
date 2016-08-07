package com.paa.requestnow.model.db.transactions;

import com.paa.requestnow.model.data.TypeRoute;
import com.paa.requestnow.model.db.Database;
import com.paa.requestnow.model.db.Schema;
import com.paa.requestnow.model.filter.DefaultFilter;
import com.paa.requestnow.model.filter.TypeFilter;
import com.paa.requestnow.model.filter.TypeRouteFilter;
import java.util.List;

/**
 *
 * @author lucas
 */
public class TypeRouteManagerTransactions 
        extends 
            ManagerTransaction<TypeRoute>
{
     @Override
    public void add(Database db, TypeRoute obj) throws Exception 
    {    
        Schema.TypesRoutes S = Schema.TypesRoutes.table;
        
        String sql = " insert into "            + S.name + 
                     " values "                 + "("    + 
                     " DEFAULT"                 + ", "   +
                      db.quote(obj.getSector()) + ", "   +
                      db.quote(obj.getType())   + ", "   +
                      db.quote(obj.getUser())   + ", "   +
                      obj.getSequence()         + ", "   +
                      obj.getState()            + ", "   +
                      obj.getDays()             + ")";
        
        db.executeCommand(sql);
    }

    @Override
    public void update(Database db, TypeRoute obj) throws Exception 
    {
        if( obj == null )
        {
            throw new Exception( "Type cannot be null" );
        }
        
        Schema.TypesRoutes S = Schema.TypesRoutes.table;
        
        String sql = " update " + S.name       + " set " +
                        S.columns.DAYS         + " = " + obj.getDays()     + ", " +
                        S.columns.SECTOR       + " = " + obj.getSector()   + ", " +
                        S.columns.SEQUENCE     + " = " + obj.getSequence() + ", " +
                        S.columns.TYPE         + " = " + obj.getType()     + ", " +
                        S.columns.STATE        + " = " + obj.getState()    + ", " +
                        S.columns.USER         + " = " + obj.getUser()     + ", " +
                     " where " + 
                        S.columns.ID           + " = " + obj.getId();
        
        db.executeCommand( sql );
    }

    @Override
    public TypeRoute get(Database db, int id) throws Exception
    {
        Schema.TypesRoutes S = Schema.TypesRoutes.table;
        
        String sql = S.select + " where " + S.columns.ID + " = " + id;
        
        return  db.fetchOne( sql , S.fetcher );
    }    

    @Override
    public List get(Database db) throws Exception
    {
        Schema.TypesRoutes S = Schema.TypesRoutes.table;
        
        String sql = S.select;
        
        return db.fetchAll(sql, S.fetcher );
    }
    

    @Override
    public List get(Database db, DefaultFilter filter) throws Exception
    {
        Schema.TypesRoutes S = Schema.TypesRoutes.table;
        
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
                    case TypeRouteFilter.STATE :
                    {
                        conditions += S.columns.STATE + " = " + values.get(i);
                    }
                    break;
                    
                    case TypeRouteFilter.SECTOR :
                    {
                        conditions += S.columns.SECTOR + " = " + values.get(i);
                    }
                    break;
                    
                    case TypeRouteFilter.TYPE :
                    {
                        conditions += S.columns.TYPE + " = " + values.get(i);
                    }
                    break;
                    
                    case TypeRouteFilter.USER :
                    {
                        conditions += S.columns.USER + " = " + values.get(i);
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
