package com.paa.requestnow.view.editor;

import com.paa.requestnow.model.ApplicationUtilities;
import com.paa.requestnow.model.data.Field;
import com.paa.requestnow.model.data.Request;
import com.paa.requestnow.model.data.Type;
import com.paa.requestnow.view.selectors.TypeSelector;
import com.paa.requestnow.view.util.EditorCallback;
import com.paa.requestnow.view.util.LabelField;
import java.util.List;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

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
        super(callback);
        
        setTitle( "Editor de Requisições" );
        
        setHeaderText( "Editor de Requisições" );

        initComponents();
        
        setSource(source);
    }
    
    @Override
    protected void validadeInput(List<String> erros) throws Exception 
    {
        if( typeField.getSelectedIndex() < 0 )
        {
            erros.add( "Tipo de requisição é obrigatória" );
        }
    }

    @Override
    protected void obtainInput() 
    {
        source.setType( typeField.getSelectedIndex() );
        
        for( Node node : gridPane.getChildren() )
        {
            
        }
    }

    @Override
    protected void resize() 
    {
        gridPane.resize( getWidth() , getHeight() );
        getDialogPane().requestLayout();
    }

    @Override
    protected void setSource(Request source) 
    {
        try 
        {
            typeField.setSelected( com.paa.requestnow.model.ModuleContext.getInstance().getTypeManager().get( source.getType() ) );
        }
        catch ( Exception e ) 
        {
            ApplicationUtilities.logException(e);
        }
    }
    
    private void generateForm( Type type )
    {
        try
        {
            hideButtons();
        
            gridPane.getChildren().clear();
        
            List<Field> fields = com.paa.requestnow.model.ModuleContext.getInstance().getFieldManager().getFieldsType( type.getId() );
            
            for (int i = 0; i < fields.size(); i++) 
            {
                Field field = fields.get(i);
                
                Node component = (Node)Class.forName(field.getHandler()).newInstance();
                
                component.setId( String.valueOf( field.getId() ) );
                
                gridPane.add( new LabelField( field.getLabel() , field.isRequired() ) , 0 , 1 ,1 , i + 1 );   
                gridPane.add( component  , 1 , 1 ,3 , i + 1 );   
            }
        }
        catch ( Exception e )
        {
            ApplicationUtilities.logException( e );
        }
    }
    
    private void initComponents()
    {
        gridPane.setVgap( 20 );
        gridPane.setHgap( 20 );
        gridPane.setStyle( "-fx-padding: 30;" );
        
        typeField.setWidth( 600 );
        
        gridPane.add( lbType    , 0, 1, 1, 1 );
        gridPane.add( typeField , 1, 1, 3, 1 );
        
    
        getDialogPane().setContent( gridPane );
        
        getDialogPane().setPrefWidth( 700 );
        
        typeField.addEventHandler( TypeSelector.Events.ON_SELECT , new EventHandler<Event>() 
        {
            @Override
            public void handle(Event t) 
            {
                generateForm( typeField.getSelected() );
            }
        });
    }
    
    private GridPane gridPane            = new GridPane();
    private LabelField lbType            = new LabelField( "Tipo de Requisição", true );
    private TypeSelector typeField       = new TypeSelector();
}
