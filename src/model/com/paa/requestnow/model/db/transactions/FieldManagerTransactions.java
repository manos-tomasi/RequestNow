package com.paa.requestnow.model.db.transactions;

import com.paa.requestnow.model.data.Core;
import com.paa.requestnow.model.filter.DefaultFilter;
import com.paa.requestnow.model.data.Field;
import com.paa.requestnow.model.data.Option;
import com.paa.requestnow.model.data.Type;
import com.paa.requestnow.model.filter.FieldFilter;
import com.paa.requestnow.model.db.Database;
import com.paa.requestnow.model.db.Schema;
import java.util.List;

/**
 *
 * @author lucas
 */
public class FieldManagerTransactions 
        extends 
            ManagerTransaction<Field>
{    
    @Override
    public void add(Database db, Field obj) throws Exception 
    {    
        Schema.Fields S = Schema.Fields.table;
        
        if ( obj == null )
        {
            throw new IllegalArgumentException( "Field cannot be null!" );
        }
        
        if ( obj.getId() != 0 )
        {
            throw new IllegalArgumentException( "Field id cannot be 0 !" );
        }
        
        
        obj.setId( db.nextId( S.name ) );
        
        String sql = " insert into "           + S.name + 
                     " values "                + "("    + 
                      obj.getId()              + ", "   +
                      db.quote(obj.getLabel()) + ", "   +
                      obj.getType()            + ", "   +
                      db.flag(obj.isRequired())+ ", "   +
                      obj.getState()           + ", "   +
                      obj.getTypeRequest()     + ", "   +
                      obj.getSequence()        + ")";
        
        db.executeCommand(sql);
    }

    @Override
    public void update(Database db, Field obj) throws Exception 
    {
        if( obj == null )
        {
            throw new Exception( "Field cannot be null" );
        }
        
        Schema.Fields S = Schema.Fields.table;
        
        String sql = " update " + S.name       + " set " +
                        S.columns.LABEL        + " = " + db.quote( obj.getLabel() )  + ", " +
                        S.columns.STATE        + " = " + obj.getState()              + ", " + 
                        S.columns.REQUIRED     + " = " + db.flag( obj.isRequired() ) + ", " + 
                        S.columns.TYPE_REQUEST + " = " + obj.getTypeRequest()        + ", " +
                        S.columns.SEQUENCE     + " = " + obj.getSequence()           + ", " +
                        S.columns.TYPE         + " = " + obj.getType()               + 
                     " where " + 
                        S.columns.ID           + " = " + obj.getId();
        
        db.executeCommand( sql );
    }

    @Override
    public Field get(Database db, int id) throws Exception 
    {
        Schema.Fields S = Schema.Fields.table;
        
        String sql = S.select + " where " + S.columns.ID + " = " + id;
        
        return  db.fetchOne( sql , S.fetcher );
    }

    public List<Field> getFieldsType( Database db, int id ) throws Exception 
    {
        Schema.Fields S = Schema.Fields.table;
        
        String sql = S.select + 
                     " where " + 
                     S.columns.TYPE_REQUEST + " = " + id +
                     " and " +
                     S.columns.STATE        + " = " +  Field.STATE_ACTIVE +
                     "order by " +
                     S.columns.SEQUENCE;
        
        return  db.fetchAll( sql , S.fetcher );
    }
    
    @Override
    public List get(Database db) throws Exception 
    {
        Schema.Fields S = Schema.Fields.table;
        
        String sql = S.select + 
                     " where " +   
                     S.columns.STATE + " = " + Field.STATE_ACTIVE;
        
        return db.fetchAll(sql, S.fetcher );
    }

    @Override
    public List get(Database db, DefaultFilter filter) throws Exception 
    {
        Schema.Fields S = Schema.Fields.table;
        
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
                    case FieldFilter.LABEL:
                    {
                        conditions += S.columns.LABEL + " ilike " + db.quote( "%" + values.get(i) + "%" );
                    }
                    break;
                    
                    case FieldFilter.STATE :
                    {
                        if( values.get(i) instanceof Option )
                        {
                            conditions += S.columns.STATE + " = " + ((Option)values.get(i)).getId();
                        }
                    }
                    break;
                    
                    case FieldFilter.TYPE :
                    {
                        if ( values.get( i ) instanceof Option )
                        {
                            conditions += S.columns.TYPE + " = " + ( (Option) values.get(i) ).getId();
                        }
                    }
                    break;
                    
                    case FieldFilter.TYPE_REQUEST :
                    {
                        if( values.get(i) instanceof Type )
                        {
                            conditions += S.columns.TYPE_REQUEST + " = " + ( (Type)values.get(i) ).getId();
                        }
                    }
                    break;
                }
                
                conditions += i == values.size() - 1 ? " ) " : " or ";    
            }
            
            sql.append( conditions );
        } );
        
        return db.fetchAll( sql.toString() , S.fetcher );
    }  
    
    
    public String getJson( Database db, Field field ) throws Exception
    {
        if ( field == null )
        {
            throw new IllegalArgumentException( "Field cannot be null!" );
        }
         
        return db.queryString( "select getJson( " + field.getId() + ", 'field' )" );
    }
    
    public boolean hasDependences( Database db, Core field ) throws Exception
    {
        if ( field == null )
        {
            throw new IllegalArgumentException( "Field cannot be null!" );
        }
        
        Schema.ValuesRequests V = Schema.ValuesRequests.alias( "V" );
        
        String sql = " select count( * ) from " +
                     V.name +
                     " where " +
                     V.columns.FIELD + " = " + field.getId();
        
        return db.queryInteger( sql ) > 0;
    }
}