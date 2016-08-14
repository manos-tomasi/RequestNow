package com.paa.requestnow.view.editor.panes;

import com.paa.requestnow.control.RequestController;
import com.paa.requestnow.model.ApplicationUtilities;
import com.paa.requestnow.model.data.Field;
import com.paa.requestnow.model.data.Type;
import com.paa.requestnow.view.selectors.ItemSelector;
import com.paa.requestnow.view.util.DateBetweenField;
import com.paa.requestnow.view.util.DateField;
import com.paa.requestnow.view.util.LabelField;
import com.paa.requestnow.view.util.MaskTextField;
import com.paa.requestnow.view.util.NumberTextField;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

/**
 *
 * @author lucas
 */
public class FieldsPane 
        extends 
            ScrollPane
{
    private HashMap< Field, Node > elements   = new HashMap();
    private RequestController      controller = RequestController.getInstance();
    
    public FieldsPane() 
    {
        initComponents();
    }
    
    public void loadFields( Type t )
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
    
    public Properties getValuesDynamics()
    {
        Properties properties = new Properties();
        
        for (Map.Entry<Field, Node> entry : elements.entrySet()) 
        {
            Field field   = entry.getKey();
            Node  element = entry.getValue();
            
            properties.put(field, getValue(element) );
        }
        
        return properties;
    }
    
    public List<String> validateInput( List<String> erros )
    {
        for (Map.Entry<Field, Node> entry : elements.entrySet()) 
        {
            Field field   = entry.getKey();
            Node  element = entry.getValue();
            
            if( field.isRequired() )
            {
                if( getValue(element) == null )
                {
                    erros.add( field.getLabel() +" é requerido!" );
                }
            }
        }
        return erros;
    }
    
    private Object getValue( Node node )
    {
        if( node instanceof MaskTextField )
        {
            return ( (MaskTextField) node ).getValue();
        }
        
        else if( node instanceof NumberTextField )
        {
            return ( (NumberTextField) node ).getDouble();
        }
        
        else if( node instanceof DateField )
        {
            return ( (DateField) node ).getDate();
        }
        
        else if( node instanceof DateBetweenField )
        {
            return ( (DateBetweenField) node ).getValue();
        }
        
        else if( node instanceof ItemSelector )
        {
            return ( (ItemSelector) node ).getSelected();
        }
        
        return null;
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
            
            LabelField label = new LabelField( field.getLabel(), field.isRequired() );
            
            RowConstraints    rc = new RowConstraints();
            
            rc.setValignment(VPos.TOP);
            
            grid.getRowConstraints().add( rc );
            
            grid.add( label   , 0, row, 1, 1 );
            grid.add( element , 1, row, 3, 1 );
            
            row++;
        }
    }
    
    private void initComponents()
    {
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
        
        setContent( grid );
    }
    
    private GridPane grid = new GridPane();
}
