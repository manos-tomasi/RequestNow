package com.paa.requestnow.panes.entries;

import com.paa.requestnow.model.ApplicationUtilities;
import com.paa.requestnow.model.filter.DefaultFilter;
import com.paa.requestnow.model.data.Sector;
import com.paa.requestnow.model.filter.SectorFilter;
import com.paa.requestnow.view.editor.FilterEditor;
import com.paa.requestnow.view.editor.SectorEditor;
import com.paa.requestnow.view.tables.SectorTable;
import com.paa.requestnow.view.util.ActionButton;
import com.paa.requestnow.view.util.EditorCallback;
import com.paa.requestnow.view.util.Prompts;
import java.util.Arrays;
import java.util.List;
import javafx.event.Event;
import javafx.scene.layout.Region;

/**
 *
 * @author lucas
 */
public class SectorController 
    implements 
        EntrieController<Sector>
{
    private SectorFilter filter =  new SectorFilter();
     
    private com.paa.requestnow.control.SectorController controller = com.paa.requestnow.control.SectorController.getInstance();

    public SectorController() 
    {
        composePermission();
    }
    
    private void composePermission()
    {
        addItem.setDisable( ! controller.hasPermissionAdd() );
        editItem.setDisable( ! controller.hasPermissionEdit() );
        deleteItem.setDisable( ! controller.hasPermissionDelete() );
    }
    
    public void addItem() throws Exception 
    {
        new SectorEditor( new EditorCallback<Sector>( new Sector() )
        {
            @Override
            public void handle( Event t ) 
            {
                try
                {
                    com.paa.requestnow.model.ModuleContext.getInstance()
                                                        .getSectorManager()
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
        Sector item = table.getSelectedItem();

        String error = controller.onEdit( item );
        
        if( error == null )
        {
            new SectorEditor( new EditorCallback<Sector>( item )
            {
                @Override
                public void handle( Event t ) 
                {
                    try
                    {
                        com.paa.requestnow.model.ModuleContext.getInstance()
                                                            .getSectorManager()
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
            Prompts.alert( error );
        }
    }

    public void filterItem() throws Exception 
    {
        new FilterEditor( new EditorCallback<DefaultFilter>( filter )
        {
            @Override
            public void handle( Event t ) 
            {
                filter = (SectorFilter) source;
                
                refresh();
            }
        } ).open();
    }

    public void deleteItem() throws Exception 
    {
        Sector item = table.getSelectedItem();
        
        String error = controller.onDelete( item );
        
        if ( error == null )
        {
            if( Prompts.confirm( "Você tem certeza que deseja excluir o setor\n" + item ) )
            {
                com.paa.requestnow.model.ModuleContext.getInstance()
                                          .getSectorManager()
                                          .delete( item );

                refresh();
            }
        }
        
        else
        {
            Prompts.alert( error );
        }
    }

    @Override
    public void refresh() 
    {
        try
        {
            table.setItems(com.paa.requestnow.model.ModuleContext
                                                    .getInstance()
                                                    .getSectorManager()
                                                    .get( filter ) );
        }
        
        catch( Exception e )
        {
            ApplicationUtilities.logException( e );
        }
    }

    @Override
    public Region getComponent() 
    {
        return  table;
    }

    
    @Override
    public List<ActionButton> getActions() 
    {
        return Arrays.asList( addItem, editItem, deleteItem, filterItem );
    }
    
    private SectorTable table = new SectorTable();
    
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
