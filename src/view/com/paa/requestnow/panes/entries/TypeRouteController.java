package com.paa.requestnow.panes.entries;

import com.paa.requestnow.model.ApplicationUtilities;
import com.paa.requestnow.model.data.TypeRoute;
import com.paa.requestnow.model.filter.DefaultFilter;
import com.paa.requestnow.model.filter.TypeRouteFilter;
import com.paa.requestnow.view.editor.FilterEditor;
import com.paa.requestnow.view.editor.TypeRouteEditor;
import com.paa.requestnow.view.tables.TypeRouteTable;
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
public class TypeRouteController 
    implements 
        EntrieController<TypeRoute>
{
    private TypeRouteFilter filter =  new TypeRouteFilter();
    
    public void addItem() throws Exception 
    {
        new TypeRouteEditor( new EditorCallback<TypeRoute>( new TypeRoute() )
        {
            @Override
            public void handle( Event t ) 
            {
                try
                {
                    com.paa.requestnow.model.ModuleContext.getInstance()
                                                        .getTypeRouteManager()
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
        TypeRoute item = table.getSelectedItem();

        if( item != null )
        {
            new TypeRouteEditor( new EditorCallback<TypeRoute>( item )
            {
                @Override
                public void handle( Event t ) 
                {
                    try
                    {
                        com.paa.requestnow.model.ModuleContext.getInstance()
                                                            .getTypeRouteManager()
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
            Prompts.alert( "Selecione uma rota para editar" );
        }
    }

    public void filterItem() throws Exception 
    {
        new FilterEditor( new EditorCallback<DefaultFilter>( filter )
        {
            @Override
            public void handle( Event t ) 
            {
                filter = (TypeRouteFilter) source;
                
                refresh();
            }
        } ).open();
    }

    public void deleteItem() throws Exception 
    {
        TypeRoute item = table.getSelectedItem();

        if( item != null && item.getState() == TypeRoute.STATE_INACTIVE )
        {
            Prompts.info( "Rota já encontra-se excluido!" );
        }

        else if( item == null )
        {
            Prompts.alert( "Selecione uma rota para excluir!" );
        }

        else
        {
            if( Prompts.confirm( "Você tem certeza que deseja excluir o rota\n" + item ) )
            {
                com.paa.requestnow.model.ModuleContext.getInstance()
                                          .getTypeRouteManager()
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
                                                    .getTypeRouteManager()
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
    
    private TypeRouteTable table = new TypeRouteTable();
    
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
