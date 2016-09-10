package com.paa.requestnow.model.db.transactions;

import com.paa.requestnow.model.data.Category;
import com.paa.requestnow.model.data.Core;
import com.paa.requestnow.model.data.Option;
import com.paa.requestnow.model.data.Type;
import com.paa.requestnow.model.db.Database;
import com.paa.requestnow.model.db.Schema;
import com.paa.requestnow.model.db.Schema.Fields;
import com.paa.requestnow.model.db.Schema.TypesRoutes;
import com.paa.requestnow.model.filter.DefaultFilter;
import com.paa.requestnow.model.filter.TypeFilter;
import java.util.List;

/**
 *
 * @author lucas
 */ 
public class TypeManagerTransactions 
        extends 
            ManagerTransaction<Type>
{
     @Override
    public void add(Database db, Type obj) throws Exception 
    {    
        Schema.Types S = Schema.Types.table;
        
        String sql = " insert into "          + S.name + 
                     " values "               + "("    + 
                     " DEFAULT"               + ", "   +
                      db.quote(obj.getName()) + ", "   +
                      obj.getState()          + ", "   +
                      db.quote(obj.getInfo()) + ", "   +
                      obj.getCategory()       + ")";
        
        db.executeCommand(sql);
    }

    @Override
    public void update(Database db, Type obj) throws Exception 
    {
        if( obj == null )
        {
            throw new Exception( "Type cannot be null" );
        }
        
        Schema.Types S = Schema.Types.table;
        
        String sql = " update " + S.name       + " set " +
                        S.columns.NAME         + " = " + db.quote( obj.getName() )  + ", " +
                        S.columns.STATE        + " = " + obj.getState()             + ", " +
                        S.columns.INFO         + " = " + db.quote( obj.getInfo() )  + "" +
                     " where " + 
                        S.columns.ID           + " = " + obj.getId();
        
        db.executeCommand( sql );
    }

    @Override
    public Type get(Database db, int id) throws Exception
    {
        Schema.Types S = Schema.Types.table;
        
        String sql = S.select + " where " + S.columns.ID + " = " + id;
        
        return  db.fetchOne( sql , S.fetcher );
    }    

    public List<Type> getTypesCategory( Database db, int id ) throws Exception
    {
        Schema.Types S = Schema.Types.table;
        
        String sql = S.select + 
                     " where " + 
                     S.columns.CATEGORY + " = " + id +
                     " and " +
                    S.columns.STATE +  " = " + Type.STATE_ACTIVE;
       
        return  db.fetchAll( sql , S.fetcher );
    }    

    @Override
    public List get(Database db) throws Exception
    {
        Schema.Types T = Schema.Types.table;
        
        String sql = T.select + " where " + T.columns.STATE + " = " +  Type.STATE_ACTIVE;
        
        return db.fetchAll(sql, T.fetcher );
    }
    
    @Override
    public List get(Database db, DefaultFilter filter) throws Exception
    {
        Schema.Types S = Schema.Types.table;
        
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
                    case TypeFilter.NAME:
                    {
                        conditions += S.columns.NAME + " ilike " + db.quote( "%" + values.get(i) + "%" );
                    }
                    break;
                    
                    case TypeFilter.STATE :
                    {
                        if( values.get(i) instanceof Option )
                        {
                            conditions += S.columns.STATE + " = " + ((Option)values.get(i)).getId();
                        }
                    }
                    break;
                    
                    case TypeFilter.CATEGORY :
                    {
                        if( values.get(i) instanceof Category )
                        {
                            conditions += S.columns.CATEGORY + " = " + ((Category)values.get(i)).getId();
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
    
    public String getJson( Database db, Type type ) throws Exception
    {
        if ( type == null )
        {
            throw new IllegalArgumentException( "Type cannot be null!" );
        }
         
        return db.queryString( "select getJson( " + type.getId() + ", 'type' )" );
    }
    
    public boolean hasDependences( Database db, Core type ) throws Exception
    {
        if ( type == null )
        {
            throw new IllegalArgumentException( "Type cannot be null!" );
        }
        
        Fields      F = Fields.table;
        TypesRoutes R = TypesRoutes.table;

        String sql;
        boolean hasDependence;
        
        sql = "select count(*) from " + F.name + 
                     " where " +
                     F.columns.STATE    + " = " + Type.STATE_ACTIVE +
                     " and " +
                     F.columns.TYPE_REQUEST + " = " + type.getId();
        
        hasDependence = db.queryInteger( sql ) > 0;
        
        sql = "select count(*) from " + R.name + 
                     " where " +
                     R.columns.STATE + " = " + Type.STATE_ACTIVE +
                     " and " +
                     R.columns.TYPE  + " = " + type.getId();
        
        hasDependence |= db.queryInteger( sql ) > 0;
        
        return hasDependence;
    }
}
