package com.paa.requestnow.model.db.transactions;

import com.paa.requestnow.model.data.Option;
import com.paa.requestnow.model.data.Sector;
import com.paa.requestnow.model.data.Type;
import com.paa.requestnow.model.data.TypeRoute;
import com.paa.requestnow.model.data.User;
import com.paa.requestnow.model.db.Database;
import com.paa.requestnow.model.db.Schema;
import com.paa.requestnow.model.filter.DefaultFilter;
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
        
        String sql = " insert into "                   + S.name + 
                     " values "                        + "("    + 
                     " DEFAULT"                        + ", "   +
                      db.foreingKey( obj.getSector() ) + ", "   +
                      db.foreingKey( obj.getType() )   + ", "   +
                      db.foreingKey( obj.getUser() )   + ", "   +
                      obj.getSequence()                + ", "   +
                      obj.getState()                   + ", "   +
                      obj.getDays()                    + ")";
        
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
                        S.columns.DAYS         + " = " + obj.getDays()                    + ", " +
                        S.columns.SECTOR       + " = " + db.foreingKey( obj.getSector() ) + ", " +
                        S.columns.SEQUENCE     + " = " + obj.getSequence()                + ", " +
                        S.columns.TYPE         + " = " + obj.getType()                    + ", " +
                        S.columns.STATE        + " = " + obj.getState()                   + ", " +
                        S.columns.USER         + " = " + db.foreingKey( obj.getUser() )   +
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

    public List<TypeRoute> getByType( Database db, int typeId ) throws Exception
    {
        Schema.TypesRoutes S = Schema.TypesRoutes.table;
        
        String sql = S.select + " where " + S.columns.TYPE + " = " + typeId + " order by sequence asc";
        
        return  db.fetchAll(sql , S.fetcher );
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
                        if( values.get(i) instanceof Option )
                        {
                            conditions += S.columns.STATE + " = " + ((Option)values.get(i)).getId();
                        }
                    }
                    break;
                    
                    case TypeRouteFilter.SECTOR :
                    {
                        if( values.get(i) instanceof Sector )
                        {
                            conditions += S.columns.SECTOR + " = " + ((Sector)values.get(i)).getId();
                        }
                    }
                    break;
                    
                    case TypeRouteFilter.TYPE :
                    {
                        if( values.get(i) instanceof Type )
                        {
                            conditions += S.columns.TYPE + " = " + ((Type)values.get(i)).getId();
                        }
                    }
                    break;
                    
                    case TypeRouteFilter.USER :
                    {
                        if( values.get(i) instanceof User )
                        {
                            conditions += S.columns.USER + " = " + ((User)values.get(i)).getId();
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
