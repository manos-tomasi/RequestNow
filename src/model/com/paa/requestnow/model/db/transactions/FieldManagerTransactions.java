package com.paa.requestnow.model.db.transactions;

import com.paa.requestnow.model.filter.DefaultFilter;
import com.paa.requestnow.model.data.Field;
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
        
        String sql = " insert into "           + S.name + 
                     " values "                + "("    + 
                     " DEFAULT"                + ", "   +
                      db.quote(obj.getLabel()) + ", "   +
                      obj.getType()            + ", "   +
                      obj.isRequired()         + ", "   +
                      obj.getState()           + ", "   +
                      obj.getTypeRequest()     + ")";
        
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
                        S.columns.LABEL        + " = " + db.quote( obj.getLabel())      + ", " +
                        S.columns.STATE        + " = " + obj.getState()                 + ", " + 
                        S.columns.REQUIRED     + " = " + obj.isRequired()               + ", " + 
                        S.columns.TYPE_REQUEST + " = " + obj.getTypeRequest()           + ", " +
                        S.columns.TYPE         + " = " + obj.getType()                  + 
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
                     S.columns.STATE        + " = " +  Field.STATE_ACTIVE;
        
        return  db.fetchAll( sql , S.fetcher );
    }
    
    @Override
    public List get(Database db) throws Exception 
    {
        Schema.Fields S = Schema.Fields.table;
        
        String sql = S.select;
        
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
                        conditions += S.columns.STATE + " = " + values.get(i);
                    }
                    break;
                    
                    case FieldFilter.TYPE :
                    {
                        conditions += S.columns.TYPE + " = " + values.get(i);
                    }
                    break;
                    
                    case FieldFilter.TYPE_REQUEST :
                    {
                        conditions += S.columns.STATE + " = " + values.get(i);
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
