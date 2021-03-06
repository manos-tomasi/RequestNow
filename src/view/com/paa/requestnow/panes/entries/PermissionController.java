package com.paa.requestnow.panes.entries;

import com.paa.requestnow.model.ApplicationUtilities;
import com.paa.requestnow.model.ModuleContext;
import com.paa.requestnow.model.data.Permission;
import com.paa.requestnow.model.data.Role;
import com.paa.requestnow.view.editor.RoleEditor;
import com.paa.requestnow.view.tables.PermissionList;
import com.paa.requestnow.view.tree.RoleTree;
import com.paa.requestnow.view.util.ActionButton;
import com.paa.requestnow.view.util.EditorCallback;
import com.paa.requestnow.view.util.Prompts;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;

/**
 * @author lucas
 */
public class PermissionController 
    implements 
        EntrieController<Role>
{
    private com.paa.requestnow.control.PermissionController controller = com.paa.requestnow.control.PermissionController.getInstance();
    
    public PermissionController() 
    {
        initComponents();
        composePermission();
    }
    
    private void composePermission()
    {
        addItem.setDisable( ! controller.hasPermissionAdd() );
        editItem.setDisable( ! controller.hasPermissionEdit() );
        permissionList.setDisable( ! controller.hasPermissionEdit() );
        saveItem.setDisable( ! controller.hasPermissionEdit() );
        deleteItem.setDisable( ! controller.hasPermissionDelete() );
    }
    
    List<Permission> permissions = new ArrayList();
    
    public void updatePermissions() throws Exception
    {
        List<Permission> permissionsScreen = new ArrayList();
        
        if( permissions.isEmpty() )
        {
            if( tree.getSelectedRole() != null && tree.getSelectedGroup() != null )
            {
                permissions = ModuleContext.getInstance().getPermissionManager().getPermissionsRole( tree.getSelectedRole().getId() );
            }
        }
        
        for (int i = 0; i < permissions.size(); i++) 
        {
            if( permissions.get(i).getGroup() == tree.getSelectedGroup().getId() )
            {
                permissionsScreen.add( permissions.get(i) );
            }  
        }

        permissionList.setItems(permissionsScreen);        
    }
    
    public void addItem() throws Exception 
    {
        new RoleEditor( new EditorCallback<Role>( new Role() )
        {
            @Override
            public void handle( Event t ) 
            {
                try
                {
                    com.paa.requestnow.model.ModuleContext.getInstance()
                                                        .getRoleManager()
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
        Role item = tree.getSelectedRole();

        String error = controller.onEdit( item );
        
        if( error == null )
        {
            new RoleEditor( new EditorCallback<Role>( item )
            {
                @Override
                public void handle( Event t ) 
                {
                    try
                    {
                        com.paa.requestnow.model.ModuleContext.getInstance()
                                                                .getRoleManager()
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

    public void saveItem() throws Exception 
    {
        for( Permission permission : permissions )
        {
            com.paa.requestnow.model.ModuleContext.getInstance().getPermissionManager().update( permission );
        }
        
        Prompts.info( "Permissões salvas com sucesso!" );
    }

    public void deleteItem() throws Exception 
    {
        Role item = tree.getSelectedRole();

       String error = controller.onDelete( item );
       
       if ( error == null )
       {
                if( Prompts.confirm( "Você tem certeza que deseja excluir a função:  " + item ) )
                {
                    com.paa.requestnow.model.ModuleContext.getInstance()
                                              .getRoleManager()
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
            tree.refreshContent();
        }
        
        catch( Exception e )
        {
            ApplicationUtilities.logException( e );
        }
    }

    @Override
    public Region getComponent() 
    {
        return  pane;
    }

    @Override
    public List<ActionButton> getActions() 
    {
        return Arrays.asList( addItem, editItem, saveItem, deleteItem );
    }
    
    private void initComponents()
    {
        pane.setLeft( tree );
        pane.setCenter( permissionList );
        
        tree.addEventHandler( RoleTree.Events.ON_SELECT_GROUP , new EventHandler<Event>() 
        {
            @Override
            public void handle(Event t) 
            {
                try
                {
                    updatePermissions();
                }
                catch( Exception e )
                {
                    ApplicationUtilities.logException(e);
                }
            }
        } );
        
        tree.addEventHandler(RoleTree.Events.ON_SELECT_ROLE , new EventHandler<Event>() 
        {
            @Override
            public void handle(Event t) 
            {
                try
                {
                    permissions.clear();
                    updatePermissions();
                }
                
                catch( Exception e )
                {
                    ApplicationUtilities.logException(e);
                }
            }
        } );
    }
    
    private BorderPane pane               = new BorderPane();
    private RoleTree tree                 = new RoleTree();
    private PermissionList permissionList = new PermissionList();
    
    
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
    
    private ActionButton saveItem = new ActionButton( "Salvar", "save.png", new ActionButton.ActionHandler() 
    {
        @Override
        public void onEvent( Event t ) throws Exception 
        {
            saveItem();
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
}