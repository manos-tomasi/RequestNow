package com.paa.requestnow.model.db.transactions;

import com.paa.requestnow.model.data.Field;
import com.paa.requestnow.model.data.FieldValue;
import com.paa.requestnow.model.data.Option;
import com.paa.requestnow.model.db.Database;
import com.paa.requestnow.model.db.Schema;
import com.paa.requestnow.model.filter.DefaultFilter;
import com.paa.requestnow.model.filter.FieldValueFilter;
import java.util.List;

/**
 *
 * @author lucas
 */
public class FieldValueManagerTransactions 
        extends 
            ManagerTransaction<FieldValue>
{
    @Override
    public void add(Database db, FieldValue obj) throws Exception 
    {    
        Schema.FieldsValues S = Schema.FieldsValues.table;
        
        String sql = " insert into "           + S.name + 
                     " values "                + "("    + 
                     " DEFAULT"                + ", "   +
                      db.quote(obj.getField()) + ", "   +
                      obj.getState()           + ", "   +
                      obj.getValue()           + ")";
        
        db.executeCommand(sql);
    }

    @Override
    public void update(Database db, FieldValue obj) throws Exception 
    {
        if( obj == null )
        {
            throw new Exception( "FieldValue cannot be null" );
        }
        
        Schema.FieldsValues S = Schema.FieldsValues.table;
        
        String sql = " update " + S.name       + " set " +
                        S.columns.STATE        + " = " + obj.getState() + ", " + 
                        S.columns.VALUE        + " = " + obj.getValue() + ", " +
                        S.columns.FIELD        + " = " + obj.getField() + 
                     " where " + 
                        S.columns.ID           + " = " + obj.getId();
        
        db.executeCommand( sql );
    }

    @Override
    public FieldValue get(Database db, int id) throws Exception 
    {
        Schema.FieldsValues S = Schema.FieldsValues.table;
        
        String sql = S.select + " where " + S.columns.ID + " = " + id;
        
        return  db.fetchOne( sql , S.fetcher );
    }
    
    @Override
    public List get(Database db) throws Exception 
    {
        Schema.FieldsValues S = Schema.FieldsValues.table;
        
        String sql = S.select + "where state = " + FieldValue.STATE_ACTIVE;
        
        return db.fetchAll(sql, S.fetcher );
    }

    @Override
    public List get(Database db, DefaultFilter filter) throws Exception 
    {
        Schema.FieldsValues S = Schema.FieldsValues.table;
        
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
                    case FieldValueFilter.STATE :
                    {
                        if( values.get(i) instanceof Option )
                        {
                            conditions += S.columns.STATE + " = " + ((Option)values.get(i)).getId();
                        }
                    }
                    break;
                    
                    case FieldValueFilter.FIELD :
                    {
                        if( values.get(i) instanceof Field )
                        {
                            conditions += S.columns.FIELD + " = " + ((Field)values.get(i)).getId();
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
