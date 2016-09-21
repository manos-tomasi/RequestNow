package com.paa.requestnow.view.editor;

import com.paa.requestnow.model.ApplicationUtilities;
import com.paa.requestnow.model.data.Request;
import com.paa.requestnow.view.editor.panes.FieldsPane;
import com.paa.requestnow.view.tree.CategoryTree;
import com.paa.requestnow.view.util.EditorCallback;
import java.util.List;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author lucas
 */
public class RequestEditor 
        extends 
            AbstractEditor<Request>
{
    public RequestEditor(EditorCallback<Request> callback) 
    {    
        super( callback );
        
        setTitle( "Editor de Requisições" );
        
        initComponents();
    }
    
    @Override
    protected void validadeInput(List<String> erros) throws Exception 
    {
        fieldsPane.validateInput( erros );
    }

    @Override
    protected void obtainInput() 
    {
        source.setType( tree.getSelectedType().getId() );
        callback.properties = fieldsPane.getValuesDynamics();
    }

    @Override
    protected void resize(){}

    @Override
    protected void setSource(Request source) {}
    
    private void initComponents()
    {
        pane.setLeft( tree );
        
        pane.setCenter( fieldsPane );
     
        setHeaderText(null);
        
        getDialogPane().setContent( pane );
        
        getDialogPane().setMinSize( ApplicationUtilities.getInstance().getWindow().getWidth(),
                                    ApplicationUtilities.getInstance().getWindow().getHeight() - 20 );
        
        tree.addEventHandler( CategoryTree.Events.ON_SELECT_TYPE , new EventHandler<Event>() 
        {
            @Override
            public void handle(Event t) 
            {
                fieldsPane.loadFields( tree.getSelectedType() );
            }
        });
        
        tree.requestFocus();
    }
    
    private BorderPane   pane       = new BorderPane();    
    private FieldsPane   fieldsPane = new FieldsPane();
    private CategoryTree tree       = new CategoryTree( CategoryTree.MODE_REQUEST );
}