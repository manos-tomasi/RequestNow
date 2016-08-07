package com.paa.requestnow.model.db.transactions;

import com.paa.requestnow.model.data.Type;
import com.paa.requestnow.model.db.Database;
import com.paa.requestnow.model.db.Schema;
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
                        S.columns.NAME         + " = " + db.quote( obj.getName() )      + ", " +
                        S.columns.STATE        + " = " + obj.getState()                 + ", " +
                        S.columns.INFO         + " = " + obj.getInfo()                  + ", " +
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
        Schema.Types S = Schema.Types.table;
        
        String sql = S.select;
        
        return db.fetchAll(sql, S.fetcher );
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
                        conditions += S.columns.STATE + " = " + values.get(i);
                    }
                    break;
                    
                    case TypeFilter.CATEGORY :
                    {
                        conditions += S.columns.CATEGORY + " = " + values.get(i);
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
