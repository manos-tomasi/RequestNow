package com.paa.requestnow.panes.entries;

import com.paa.requestnow.control.util.JsonUtilities;
import com.paa.requestnow.model.ApplicationUtilities;
import com.paa.requestnow.model.ResourceLocator;
import com.paa.requestnow.model.data.Category;
import com.paa.requestnow.view.editor.CategoryEditor;
import com.paa.requestnow.view.util.ActionButton;
import com.paa.requestnow.view.util.ActionButton.ActionHandler;
import com.paa.requestnow.view.tree.CategoryTree;
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
public class CategoryController
    implements 
        EntrieController<Category>
{
    private com.paa.requestnow.control.CategoryController controller = com.paa.requestnow.control.CategoryController.getInstance();
    
    public CategoryController() 
    {
        initComponents();
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
        new CategoryEditor( new EditorCallback<Category>( new Category() )
        {
            @Override
            public void handle( Event t ) 
            {
                try
                {
                    com.paa.requestnow.model.ModuleContext.getInstance()
                                                        .getCategoryManager()
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
        Category item = tree.getSelectedCategory();

        String error = controller.onEdit( item );

        if( error == null )
        {
            new CategoryEditor( new EditorCallback<Category>( item )
            {
                @Override
                public void handle( Event t ) 
                {
                    try
                    {
                        com.paa.requestnow.model.ModuleContext.getInstance()
                                                            .getCategoryManager()
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

    public void deleteItem() throws Exception 
    {
        Category item = tree.getSelectedCategory();
        
        String  error = controller.onDelete( item );

        if ( error == null )
        {
            if( Prompts.confirm( "Você tem certeza que deseja excluir a categoria\n" + item ) )
            {
                com.paa.requestnow.model.ModuleContext.getInstance()
                                          .getCategoryManager()
                                          .delete( item );

                refresh();
            }
        }
        
        else
        {
            Prompts.alert( error );
        }
    }
    
        
    private void showContent()
    {
        try
        {
            Category category = tree.getSelectedCategory();
            
            if ( category != null )
            {
                engine.executeScript( "setCategory( " + JsonUtilities.getCategory( category )+" );" ) ;
            }
        }
        
        catch ( Exception e) {/*ignore*/}
    }

    
    @Override
    public void refresh() 
    {
        try
        {
            Object node = tree.getSelectedNode();
            
            tree.refreshContent();
            
            tree.setSelectedNode( node );
        }
        
        catch( Exception e )
        {
            ApplicationUtilities.logException( e );
        }
    }

    @Override
    public Region getComponent() 
    {
        return  borderPane;
    }

    
    @Override
    public List<ActionButton> getActions() 
    {
        return Arrays.asList( addItem, editItem, deleteItem );
    }
     
    
    private void initComponents()
    {
        borderPane.setLeft( tree );
        borderPane.setCenter( view );
                
        engine.load( ResourceLocator.getInstance().getWebResource( "category.html" ) );
        engine.setJavaScriptEnabled( true );
        
        engine.getLoadWorker().stateProperty().addListener( new ChangeListener<Worker.State>() 
        {  
            @Override 
            public void changed( ObservableValue<? extends Worker.State> ov, Worker.State oldState, Worker.State newState )
            {
              if ( newState == Worker.State.SUCCEEDED )
                ( (JSObject) engine.executeScript( "window" ) ).setMember( "application", CategoryController.this );
            }
        } );
        
        tree.addEventHandler( CategoryTree.Events.ON_SELECT_CATEGORY, new EventHandler<Event>() 
        {
            @Override
            public void handle( Event t )
            {
                showContent();
            }
        } );
        
    }
    
    private BorderPane borderPane = new BorderPane();
    private CategoryTree tree     = new CategoryTree( CategoryTree.MODE_CATEGORY );
    private WebView view          = new WebView();
    private WebEngine engine      = view.getEngine();
    
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
}
