package com.paa.requestnow.panes.entries;

import com.paa.requestnow.model.ApplicationUtilities;
import com.paa.requestnow.model.ResourceLocator;
import com.paa.requestnow.model.data.Field;
import com.paa.requestnow.model.data.Type;
import com.paa.requestnow.model.filter.FieldFilter;
import com.paa.requestnow.view.editor.FieldEditor;
import com.paa.requestnow.view.util.ActionButton;
import com.paa.requestnow.view.util.CategoryTree;
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
public class FieldController 
    implements 
        EntrieController<Field>
{
    public FieldController() 
    {
        initComponents();
    }
    
    
    private void addItem() throws Exception 
    {
        Type type = tree.getSelectedType();
        
        if ( type != null )
        {
            Field field = new Field();
            field.setTypeRequest( type.getId() );
            
            new FieldEditor( new EditorCallback<Field>( field )
            {
                @Override
                public void handle( Event t ) 
                {
                    try
                    {
                        //substituir por procedure()
                        source.setSequence( com.paa.requestnow.model.ModuleContext
                                                                        .getInstance()
                                                                        .getFieldManager()
                                                                        .getFieldsType( field.getTypeRequest() ).size() );
                        
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
        
        else
        {
            Prompts.alert( "Selecione um Tipo para inserir campos!" );
        }
    }

    
    private void editItem() throws Exception 
    {
        Field item = tree.getSelectedField();

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
    
    
    
    private void deleteItem() throws Exception 
    {
        Field item = tree.getSelectedField();

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

    
    private void moveUp() throws Exception
    {
        Field field = tree.getSelectedField();
        
        if ( field != null )
        {
            if ( field.getSequence() > 0 )
            {
                List<Field> fields = com.paa.requestnow.model.ModuleContext.getInstance().getFieldManager().getFieldsType( field.getTypeRequest() );
                
                field.setSequence( field.getSequence() - 1 );
                
                com.paa.requestnow.model.ModuleContext.getInstance().getFieldManager().update( field );
                
                for ( Field f : fields )
                {
                    if ( f.getSequence() == field.getSequence() )
                    {
                        f.setSequence( f.getSequence() + 1 );
                        
                        com.paa.requestnow.model.ModuleContext.getInstance().getFieldManager().update( f );
                        
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
        Field field = tree.getSelectedField();
        
        if ( field != null )
        {
            List<Field> fields = com.paa.requestnow.model.ModuleContext.getInstance().getFieldManager().getFieldsType( field.getTypeRequest() );
            
            if ( field.getSequence() < fields.size() - 1 )
            {
                field.setSequence( field.getSequence() + 1 );
                
                com.paa.requestnow.model.ModuleContext.getInstance().getFieldManager().update( field );
                
                for ( Field f : fields )
                {
                    if ( f.getSequence() == field.getSequence() )
                    {
                        f.setSequence( f.getSequence() - 1 );
                        
                        com.paa.requestnow.model.ModuleContext.getInstance().getFieldManager().update( f );
                        
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
    
    
    private void showContent()
    {
        try
        {
            Field field = tree.getSelectedField();
            
            if ( field != null )
            {
                engine.executeScript( "setField( " + field.toJson() +" );" ) ;
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
                
        engine.load( ResourceLocator.getInstance().getWebResource( "fields.html" ) );
        engine.setJavaScriptEnabled( true );
        
        engine.getLoadWorker().stateProperty().addListener( new ChangeListener<Worker.State>() 
        {  
            @Override 
            public void changed( ObservableValue<? extends Worker.State> ov, Worker.State oldState, Worker.State newState )
            {
              if ( newState == Worker.State.SUCCEEDED )
                ( (JSObject) engine.executeScript( "window" ) ).setMember( "application", FieldController.this );
            }
        } );
        
        tree.addEventHandler( CategoryTree.Events.ON_SELECT_FIELD, new EventHandler<Event>() 
        {
            @Override
            public void handle( Event t )
            {
                showContent();
            }
        } );
    }
    
    private BorderPane borderPane = new BorderPane();
    private CategoryTree tree     = new CategoryTree( CategoryTree.MODE_FIELD );
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
