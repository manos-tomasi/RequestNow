package com.paa.requestnow.control;

import com.paa.requestnow.model.data.Core;
import com.paa.requestnow.model.data.Field;
import com.paa.requestnow.model.data.FieldValue;
import com.paa.requestnow.model.data.Type;
import java.util.Properties;
import javafx.collections.ObservableList;

/**
 * @author artur
 */
public class FieldController 
    extends 
        AbstractController<FieldController, Core>
{
    private static FieldController instance;
    
    private FieldController(){}
    
    public static FieldController getInstance()
    {
        if( instance == null )
        {
            instance = new FieldController();
        }
        
        return instance;
    }
    
    @Override
    public String onEdit( Core item) throws Exception 
    {
        if( item == null)
        {
            return "Selecione um campo para editar";
        }
        
        return null;
    }

    @Override
    public String onDelete( Core item ) throws Exception 
    {
        if( item  == null )
        {
            return "Selecione um campo para excluir!";
        }
        
        else if( item.getState() == Field.STATE_INACTIVE )
        {
            return "Campo já encontra-se excluida!";
        } 
            
        else if ( com.paa.requestnow.model.ModuleContext.getInstance().getFieldManager().hasDependences( item ) )
        {
             return "Existem campos ou rotas referênciados neste tipo!";
        }
        
        return null;
    }

    @Override
    public String onAdd( Core item ) throws Exception 
    {
        if( item == null)
        {
            return "Selecione um item iserir um campo";
        }
        
        if ( ! ( item instanceof Type ) &&
             ! ( item instanceof Field ) )
        {
             return "Selecione um tipo ou uma campo para iserir uma Campo";
        }
        
        return null;
    }
    
    public Field createField( Object item )
    {
        Field field = new Field();
        
        if ( item instanceof Type )
        {
            field.setTypeRequest( ( (Type) item ).getId() );
        }
        
        else if ( item instanceof Field ) 
        {
             field.setTypeRequest( ( (Field) item ).getTypeRequest() );
        }
        
        return field;
    }
    
    public void store( Field field, Properties properties ) throws Exception
    {
        if ( field == null )
        {
            throw new IllegalArgumentException( "Field cannot be null" );
        }
        
        if ( field.getId() == 0 )
        {
            com.paa.requestnow.model.ModuleContext
                                            .getInstance()
                                            .getFieldManager()
                                            .add( field );
        }
        
        else
        {
            com.paa.requestnow.model.ModuleContext
                                        .getInstance()
                                        .getFieldManager()
                                        .update( field );
        }
        
        ObservableList<FieldValue> values = (ObservableList<FieldValue>) properties.get( "values" );
        
        for( FieldValue value : values )
        {
            value.setField( field.getId() );
            
            if ( value.getId() > 0 )
            {
                com.paa.requestnow.model.ModuleContext
                                        .getInstance()
                                        .getFieldValueManager()
                                        .update( value );
            }
            
            else 
            {
                com.paa.requestnow.model.ModuleContext
                                        .getInstance()
                                        .getFieldValueManager()
                                        .add( value );
            }
        }
        
    }
    
    public void delete( Field field ) throws Exception
    {
        com.paa.requestnow.model.ModuleContext.getInstance()
                                          .getFieldManager()
                                          .delete( field );
        
    }
}
