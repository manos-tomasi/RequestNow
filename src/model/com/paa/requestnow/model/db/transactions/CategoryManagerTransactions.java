package com.paa.requestnow.model.db.transactions;

import com.paa.requestnow.model.data.Category;
import com.paa.requestnow.model.data.Option;
import com.paa.requestnow.model.filter.CategoryFilter;
import com.paa.requestnow.model.filter.DefaultFilter;
import com.paa.requestnow.model.db.Database;
import com.paa.requestnow.model.db.Schema;
import com.paa.requestnow.model.db.Schema.Types;
import java.sql.Array;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lucas
 */
public class CategoryManagerTransactions 
        extends ManagerTransaction<Category>
{    
    @Override
    public void add(Database db, Category obj) throws Exception 
    {    
        Schema.Categories S = Schema.Categories.table;
        
        String sql = " insert into "          + S.name + 
                     " values "               + "("    + 
                     " DEFAULT"               + ", "   +
                      db.quote( obj.getName() ) + ", "   +
                      obj.getState()            + ", "   +
                      db.quote( obj.getInfo() ) + ")";
        
        db.executeCommand(sql);
    }

    @Override
    public void update(Database db, Category obj) throws Exception 
    {
        if( obj == null )
        {
            throw new Exception( "Category cannot be null" );
        }
        
        Schema.Categories S = Schema.Categories.table;
        
        String sql = " update " + S.name       + " set " +
                        S.columns.NAME         + " = " + db.quote( obj.getName() )  + ", " +
                        S.columns.STATE        + " = " + obj.getState()             + ", " +
                        S.columns.INFO         + " = " + db.quote( obj.getInfo() )  +
                     " where " + 
                        S.columns.ID           + " = " + obj.getId();
        
        db.executeCommand( sql );
    }

    @Override
    public Category get(Database db, int id) throws Exception
    {
        Schema.Categories S = Schema.Categories.table;
        
        String sql = S.select + " where " + S.columns.ID + " = " + id;
        
        return  db.fetchOne( sql , S.fetcher );
    }    

    @Override
    public List get(Database db) throws Exception
    {
        Schema.Categories S = Schema.Categories.table;
        
        String sql = S.select  + 
                     " where " +
                     S.columns.STATE + " = " + Category.STATE_ACTIVE +
                     " order by " +
                     S.columns.NAME;
        
        return db.fetchAll(sql, S.fetcher );
    }
    

    @Override
    public List get(Database db, DefaultFilter filter) throws Exception
    {
        Schema.Categories S = Schema.Categories.table;
        
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
                    case CategoryFilter.NAME:
                    {
                        conditions += S.columns.NAME + " ilike " + db.quote( "%" + values.get(i) + "%" );
                    }
                    break;
                    
                    case CategoryFilter.STATE :
                    {
                        if( values.get(i) instanceof Option )
                        {
                            conditions += S.columns.STATE + " = " + ((Option)values.get(i)).getId();
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
    
    public String getJson( Database db, Category category ) throws Exception
    {
        if ( category == null )
        {
            throw new IllegalArgumentException( "Category cannot be null!" );
        }
         
        return db.queryString( "select getJson( " + category.getId() + ", 'category' )" );
    }
    
    public boolean hasDependences( Database db, Category category ) throws Exception
    {
        if ( category == null )
        {
            throw new IllegalArgumentException( "Category cannot be null!" );
        }
        
        Types T = Types.table;
        
        String sql = "select count(*) from " + T.name + 
                     " where " +
                     T.columns.STATE    + " = " + Category.STATE_ACTIVE +
                     " and " +
                     T.columns.CATEGORY + " = " + category.getId();
        
        return db.queryInteger( sql ) > 0;
    }
    
    
    public String getDrilldownCategories( Database db ) throws Exception
    {
        Schema.Types T = Schema.Types.alias( "T" );
        Schema.Categories C = Schema.Categories.alias( "C" );
        
        String sql = " select count( " + T.columns.ID + "), " + C.columns.NAME + ", " + C.columns.ID +
                     " from " +
                     C.name + 
                     " left join " +
                     T.name + 
                     " on " +
                     T.columns.CATEGORY +  " = " + C.columns.ID +
                     " group by " +
                     C.columns.NAME + ", " + C.columns.ID;
        
        ResultSet set = db.query( sql );
        
        List<String> data = new ArrayList();
        
        while ( set.next() )
        {
            Integer count = set.getInt( 1 );
            String name   = set.getString( 2 );
            Integer id    = set.getInt( 3 );
            
            data.add( "{ name: '" + name + "', y: " + count + ", drilldown: '" + id + "' }" );
        }
        
        set.close();
        
        return data.toString();
    }
}
