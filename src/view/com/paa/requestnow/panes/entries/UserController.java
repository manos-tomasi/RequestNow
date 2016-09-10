package com.paa.requestnow.panes.entries;

import com.paa.requestnow.model.ApplicationUtilities;
import com.paa.requestnow.model.filter.DefaultFilter;
import com.paa.requestnow.model.data.User;
import com.paa.requestnow.model.filter.UserFilter;
import com.paa.requestnow.view.editor.FilterEditor;
import com.paa.requestnow.view.editor.UserEditor;
import com.paa.requestnow.view.tables.UserTable;
import com.paa.requestnow.view.util.ActionButton;
import com.paa.requestnow.view.util.ActionButton.ActionHandler;
import com.paa.requestnow.view.util.EditorCallback;
import com.paa.requestnow.view.util.Prompts;
import java.util.Arrays;
import java.util.List;
import javafx.event.Event;
import javafx.scene.layout.Region;

/**
 * @author artur
 */
public class UserController 
    implements 
        EntrieController<User>
{
    private UserFilter filter =  new UserFilter();
    private com.paa.requestnow.control.UserController controller = com.paa.requestnow.control.UserController.getInstance();

    public UserController() 
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
        new UserEditor(new EditorCallback<User>( new User() )
        {
            @Override
            public void handle( Event t ) 
            {
                try
                {
                    com.paa.requestnow.model.ModuleContext.getInstance()
                                                        .getUserManager()
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
        User item = table.getSelectedItem();

        String error = controller.onEdit( item );
        
        if( error == null )
        {
            new UserEditor(new EditorCallback<User>( item )
            {
                @Override
                public void handle( Event t ) 
                {
                    try
                    {
                        com.paa.requestnow.model.ModuleContext.getInstance()
                                                            .getUserManager()
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
                filter = (UserFilter) source;
                
                refresh();
            }
        } ).open();
    }

    public void deleteItem() throws Exception 
    {
        User item = table.getSelectedItem();

        String error = controller.onDelete( item );
        
        if ( error == null )
        {
            if( Prompts.confirm( "Você tem certeza que deseja excluir o usuário\n" + item ) )
            {
                com.paa.requestnow.model.ModuleContext.getInstance()
                                          .getUserManager()
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
                                                    .getUserManager()
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
    
    private UserTable table = new UserTable();
    
    private ActionButton addItem = new ActionButton( "Novo", "new.png", new ActionHandler()
    {
        @Override
        public void onEvent( Event t ) throws Exception 
        {
            addItem();
        }
    } );
    
    private ActionButton editItem = new ActionButton( "Editar", "edit.png", new ActionHandler() 
    {
        @Override
        public void onEvent( Event t ) throws Exception 
        {
            editItem();
        }
    } );
    
    private ActionButton deleteItem = new ActionButton( "Excluir", "delete.png", new ActionHandler() 
    {
        @Override
        public void onEvent( Event t ) throws Exception
        {
            deleteItem();
        }
    } );
    
    private ActionButton filterItem = new ActionButton( "Filtro", "search.png", new ActionHandler() 
    {
        @Override
        public void onEvent( Event t ) throws Exception
        {
            filterItem();
        }
    } );
}
