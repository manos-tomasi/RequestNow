package com.paa.requestnow.view.editor;

import com.paa.requestnow.control.RequestController;
import com.paa.requestnow.model.ApplicationUtilities;
import com.paa.requestnow.model.data.Field;
import com.paa.requestnow.model.data.Request;
import com.paa.requestnow.model.data.Type;
import com.paa.requestnow.view.util.CategoryTree;
import com.paa.requestnow.view.util.EditorCallback;
import com.paa.requestnow.view.util.LabelField;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

/**
 *
 * @author lucas
 */
public class RequestEditor 
        extends 
            Dialog<Request>
{

    private RequestController controller    = RequestController.getInstance();
    private HashMap< Field, Node > elements = new HashMap();
    
    public RequestEditor(EditorCallback<Request> callback) 
    {        
        setTitle( "Editor de Requisições" );
        
        setHeaderText( "Editor de Requisições" );
        
        initComponents();
    }
    
    public void open()
    {        
        show();
    }
    
    private void renderer()
    {
        grid.getChildren().clear();
        grid.requestLayout();
        
        int row = 0;
        for ( Map.Entry<Field, Node> entry : elements.entrySet()) 
        {
            Field field   = entry.getKey();
            Node  element = entry.getValue();
            
            grid.add( new LabelField( field.getLabel(), field.isRequired() ), 0, row, 1, 1 );
            grid.add( element                                               , 1, row, 3, 1 );
            
            row++;
        }
    }
    
    private void loadFields( Type t )
    {
        try
        {
            List<Field> fields = controller.getFields( t.getId() );
            
            elements.clear();
            
            for( Field field : fields )
            {
                Node node = (Node)Class.forName(field.getHandler()).newInstance();
                
                elements.put( field, node );
            }
            
            renderer();
        }
        catch( Exception e )
        {
            ApplicationUtilities.logException( e );
        }
    }
    
    private void initComponents()
    {
        // Fecha janela
        getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        Node closeButton = getDialogPane().lookupButton(ButtonType.CLOSE);
        closeButton.managedProperty().bind(closeButton.visibleProperty());
        closeButton.setVisible(false);
        
        grid.setVgap( 20 );
        grid.setHgap( 20 );
        grid.setStyle( "-fx-padding: 30;" );
        
            
        ColumnConstraints colName = new ColumnConstraints();
        colName.setMinWidth( 200);
        colName.setHalignment( HPos.LEFT );
     
        ColumnConstraints colNode = new ColumnConstraints();
        colNode.setFillWidth(true);
        colNode.setHalignment( HPos.LEFT );
        
        grid.getColumnConstraints().addAll( colName, colNode );

        grid.setPrefWidth( ApplicationUtilities.getInstance().getWindow().getWidth() - 300 );
        
        pane.setLeft( tree );
        pane.setCenter( grid );
        
        getDialogPane().setContent( pane );
        
        getDialogPane().setMinSize( ApplicationUtilities.getInstance().getWindow().getWidth(),
                                    ApplicationUtilities.getInstance().getWindow().getHeight());
        
        tree.addEventHandler( CategoryTree.Events.ON_SELECT_TYPE , new EventHandler<Event>() 
        {
            @Override
            public void handle(Event t) 
            {
                loadFields( tree.getSelectedType() );
            }
        });
    }
    
    private BorderPane   pane = new BorderPane();
    private CategoryTree tree = new CategoryTree();
    private GridPane     grid = new GridPane();
}