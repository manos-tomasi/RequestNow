package com.paa.requestnow.panes.entries;

import com.paa.requestnow.control.util.JsonUtilities;
import com.paa.requestnow.model.ApplicationUtilities;
import com.paa.requestnow.model.ResourceLocator;
import com.paa.requestnow.model.data.Category;
import com.paa.requestnow.model.data.Core;
import com.paa.requestnow.model.data.Type;
import com.paa.requestnow.model.data.TypeRoute;
import com.paa.requestnow.view.editor.TypeRouteEditor;
import com.paa.requestnow.view.tree.CategoryTree;
import com.paa.requestnow.view.util.ActionButton;
import com.paa.requestnow.view.util.EditorCallback;
import com.paa.requestnow.view.util.Prompts;
import java.util.Arrays;
import java.util.List;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;

/**
 * @author artur
 */
public class TypeRouteController 
    implements 
        EntrieController<TypeRoute>
{
    private com.paa.requestnow.control.TypeRouteController controller = com.paa.requestnow.control.TypeRouteController.getInstance();
    
    public TypeRouteController() 
    {
        initComponents();
        composePermission();
    }
    
    private void composePermission()
    {
        addItem.setDisable( ! controller.hasPermissionAdd() );
        editItem.setDisable( ! controller.hasPermissionEdit() );
        moveDownItem.setDisable( ! controller.hasPermissionEdit() );
        moveUpItem.setDisable( ! controller.hasPermissionEdit() );
        deleteItem.setDisable( ! controller.hasPermissionDelete() );
    }
    
    
    private void addItem() throws Exception 
    {
        Core item = tree.getSelectedCore();
        
        String error = controller.onAdd( item );
        
        if ( error == null )
        {
            TypeRoute route = controller.createRoute( item );
            
            new TypeRouteEditor( new EditorCallback<TypeRoute>( route )
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
        
        else
        {
            Prompts.alert( error );
        }
    }

    
    private void editItem() throws Exception 
    {
        TypeRoute item = tree.getSelectedRoute();
        
        String error = controller.onEdit( item );

        if( error == null )
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
            Prompts.alert( error );
        }
    }
    
    
    private void deleteItem() throws Exception 
    {
        TypeRoute item = tree.getSelectedRoute();

        String error = controller.onDelete( item );
        
        if ( error == null )
        {
            if( Prompts.confirm( "Você tem certeza que deseja excluir a rota\n" + item ) )
            {
                com.paa.requestnow.model.ModuleContext.getInstance()
                                          .getTypeRouteManager()
                                          .delete( item );

                refresh();
            }
        }
        
        else
        {
            Prompts.alert( error );
        }
    }
    
    private void moveUp() throws Exception
    {
        TypeRoute route = tree.getSelectedRoute();
        
        if ( route != null )
        {
            if ( route.getSequence() > 0 )
            {
                List<TypeRoute> routes = com.paa.requestnow.model.ModuleContext.getInstance().getTypeRouteManager().getByType( route.getType() );
                
                route.setSequence( route.getSequence() - 1 );
                
                com.paa.requestnow.model.ModuleContext.getInstance().getTypeRouteManager().update( route );
                
                for ( TypeRoute r : routes )
                {
                    if ( r.getSequence() == route.getSequence() )
                    {
                        r.setSequence( r.getSequence() + 1 );
                        
                        com.paa.requestnow.model.ModuleContext.getInstance().getTypeRouteManager().update( r );
                        
                        break;
                    }
                }
                
                int index = tree.getSelectionModel().getSelectedIndex();
                
                refresh();
                
                if ( index != -1 )
                {
                    tree.getSelectionModel().select( index - 1 );
                }
            }
        }
    }
    
    
    private void moveDown() throws Exception
    {
        TypeRoute route = tree.getSelectedRoute();
        
        if ( route != null )
        {
            List<TypeRoute> routes = com.paa.requestnow.model.ModuleContext.getInstance().getTypeRouteManager().getByType( route.getType() );
            
            if ( route.getSequence() < routes.size() - 1 )
            {
                route.setSequence( route.getSequence() + 1 );
                
                com.paa.requestnow.model.ModuleContext.getInstance().getTypeRouteManager().update( route );
                
                for ( TypeRoute r : routes )
                {
                    if ( r.getSequence() == route.getSequence() )
                    {
                        r.setSequence( r.getSequence() - 1 );
                        
                        com.paa.requestnow.model.ModuleContext.getInstance().getTypeRouteManager().update( r );
                        
                        break;
                    }
                }
                
                int index = tree.getSelectionModel().getSelectedIndex();
                
                refresh();
                
                if ( index != -1 )
                {
                    tree.getSelectionModel().select( index + 1 );
                }
            }
        }
    }

    private void showType()
    {
        try
        {
            Type type = tree.getSelectedType();
            
            if ( type != null )
            {
                engine.load( ResourceLocator.getInstance().getWebResource( "type.html" ) );
             
                engine.documentProperty().addListener( ( prop, oldDoc, newDoc ) ->  engine.executeScript( "setType( " + JsonUtilities.getType( type ) +" );" ) );
            }
        }
        
        catch ( Exception e) {/*ignore*/}
    }
    
    
    private void showCategory() 
    {
        try
        {
            Category category = tree.getSelectedCategory();
            
            if ( category != null )
            {
                engine.load( ResourceLocator.getInstance().getWebResource( "category.html" ) );
                
                engine.documentProperty().addListener( (s) -> engine.executeScript( "setCategory( " + JsonUtilities.getCategory( category ) +" );" ) );
            }
        }
        
        catch ( Exception e) {/*ignore*/}
    }
    
    
    private void showRoute()
    {
        try
        {
            TypeRoute route = tree.getSelectedRoute();
            
            if ( route != null )
            {
                engine.load( ResourceLocator.getInstance().getWebResource( "route.html" ) );
                
                engine.documentProperty().addListener( (s) -> engine.executeScript( "setRoute( " + JsonUtilities.getRoute( route ) +" );" ) );
            }
        }
        
        catch ( Exception e) {/*ignore*/}
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
    public List<ActionButton> getActions() 
    {
        return Arrays.asList( addItem, editItem, deleteItem, moveUpItem, moveDownItem );
    }
    
    
    
    @Override
    public Region getComponent() 
    {
        return  borderPane;
    }

    
    
    private void initComponents()
    {
        borderPane.setLeft( tree );
        borderPane.setCenter( view );
                
        engine.setJavaScriptEnabled( true );
        
        engine.getLoadWorker().stateProperty().addListener( new ChangeListener<Worker.State>() 
        {  
            @Override 
            public void changed( ObservableValue<? extends Worker.State> ov, Worker.State oldState, Worker.State newState )
            {
              if ( newState == Worker.State.SUCCEEDED )
                ( (JSObject) engine.executeScript( "window" ) ).setMember( "application", TypeRouteController.this );
            }
        } );
        
        tree.addEventHandler( CategoryTree.Events.ON_SELECT_TYPE, new EventHandler<Event>() 
        {
            @Override
            public void handle( Event t )
            {
                showType();
            }
        } );
        
        tree.addEventHandler( CategoryTree.Events.ON_SELECT_ROUTE, new EventHandler<Event>() 
        {
            @Override
            public void handle( Event t )
            {
                showRoute();
            }
        } );
        
        tree.addEventHandler( CategoryTree.Events.ON_SELECT_CATEGORY, new EventHandler<Event>() 
        {
            @Override
            public void handle( Event t )
            {
                showCategory();
            }
        } );
    }
    
    private BorderPane borderPane = new BorderPane();
    private CategoryTree tree     = new CategoryTree( CategoryTree.MODE_ROUTE );
    private WebView view          = new WebView();
    private WebEngine engine      = view.getEngine();
    
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
    
    private ActionButton moveUpItem = new ActionButton( "Acima", "up.png", new ActionButton.ActionHandler() 
    {
        @Override
        public void onEvent( Event t ) throws Exception
        {
            moveUp();
        }
    } );
    
    private ActionButton moveDownItem = new ActionButton( "Abaixo", "down.png", new ActionButton.ActionHandler() 
    {
        @Override
        public void onEvent( Event t ) throws Exception
        {
            moveDown();
        }
    } );
}
