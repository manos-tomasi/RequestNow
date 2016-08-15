package com.paa.requestnow.panes.entries;

import com.paa.requestnow.model.ApplicationUtilities;
import com.paa.requestnow.model.data.Field;
import com.paa.requestnow.model.filter.DefaultFilter;
import com.paa.requestnow.model.filter.FieldFilter;
import com.paa.requestnow.view.editor.FieldEditor;
import com.paa.requestnow.view.editor.FilterEditor;
import com.paa.requestnow.view.tables.FieldTable;
import com.paa.requestnow.view.util.ActionButton;
import com.paa.requestnow.view.util.EditorCallback;
import com.paa.requestnow.view.util.Prompts;
import java.util.Arrays;
import java.util.List;
import javafx.event.Event;
import javafx.scene.control.Control;

/**
 * @author artur
 */
public class FieldController 
    implements 
        EntrieController<Field>
{
    private FieldFilter filter =  new FieldFilter();
    
    public void addItem() throws Exception 
    {
        new FieldEditor( new EditorCallback<Field>( new Field() )
        {
            @Override
            public void handle( Event t ) 
            {
                try
                {
                    com.paa.requestnow.model.ModuleContext.getInstance()
                                                        .getFieldManager()
                                                        .add( source );
                    refresh();
                }
                
                catch( Exception e )
                {
                    ApplicationUtilities.logException( e );
                }
            }
        } ).open();
    }

    public void editItem() throws Exception 
    {
        Field item = table.getSelectedItem();

        if( item != null )
        {
            new FieldEditor( new EditorCallback<Field>( item )
            {
                @Override
                public void handle( Event t ) 
                {
                    try
                    {
                        com.paa.requestnow.model.ModuleContext.getInstance()
                                                            .getFieldManager()
                                                            .update( source );
                        refresh();
                    }

                    catch( Exception e )
                    {
                        ApplicationUtilities.logException( e );
                    }
                }
            } ).open();
        }
        
        else
        {
            Prompts.alert( "Selecione uma campo para editar" );
        }
    }

    public void filterItem() throws Exception 
    {
        new FilterEditor( new EditorCallback<DefaultFilter>( filter )
        {
            @Override
            public void handle( Event t ) 
            {
                filter = (FieldFilter) source;
                
                refresh();
            }
        } ).open();
    }

    public void deleteItem() throws Exception 
    {
        Field item = table.getSelectedItem();

        if( item != null && item.getState() == Field.STATE_INACTIVE )
        {
            Prompts.info( "Setor já encontra-se excluido!" );
        }

        else if( item == null )
        {
            Prompts.alert( "Selecione uma campo para excluir!" );
        }

        else
        {
            if( Prompts.confirm( "Você tem certeza que deseja excluir o campo\n" + item ) )
            {
                com.paa.requestnow.model.ModuleContext.getInstance()
                                          .getFieldManager()
                                          .delete( item );

                refresh();
            }
        }
    }

    @Override
    public void refresh() 
    {
        try
        {
            table.setItems(com.paa.requestnow.model.ModuleContext
                                                    .getInstance()
                                                    .getFieldManager()
                                                    .get( filter ) );
        }
        
        catch( Exception e )
        {
            ApplicationUtilities.logException( e );
        }
    }

    @Override
    public Control getComponent() 
    {
        return  table;
    }

    
    @Override
    public List<ActionButton> getActions() 
    {
        return Arrays.asList( addItem, editItem, deleteItem, filterItem );
    }
    
    private FieldTable table = new FieldTable();
    
    private ActionButton addItem = new ActionButton( "Novo", "new.png", new ActionButton.ActionHandler()
    {
        @Override
        public void onEvent( Event t ) throws Exception 
        {
            addItem();
        }
    } );
    
    private ActionButton editItem = new ActionButton( "Editar", "edit.png", new ActionButton.ActionHandler() 
    {
        @Override
        public void onEvent( Event t ) throws Exception 
        {
            editItem();
        }
    } );
    
    private ActionButton deleteItem = new ActionButton( "Excluir", "delete.png", new ActionButton.ActionHandler() 
    {
        @Override
        public void onEvent( Event t ) throws Exception
        {
            deleteItem();
        }
    } );
    
    private ActionButton filterItem = new ActionButton( "Filtro", "search.png", new ActionButton.ActionHandler() 
    {
        @Override
        public void onEvent( Event t ) throws Exception
        {
            filterItem();
        }
    } );
}
