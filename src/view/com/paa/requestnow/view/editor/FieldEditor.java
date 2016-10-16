package com.paa.requestnow.view.editor;

import com.paa.requestnow.model.ApplicationUtilities;
import com.paa.requestnow.model.data.Field;
import com.paa.requestnow.view.editor.panes.FieldsValuesPane;
import com.paa.requestnow.view.selectors.HandlerSelector;
import com.paa.requestnow.view.selectors.TypeSelector;
import com.paa.requestnow.view.selectors.YesNoSelector;
import com.paa.requestnow.view.util.EditorCallback;
import com.paa.requestnow.view.util.LabelField;
import com.paa.requestnow.view.util.MaskTextField;
import java.util.List;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.layout.GridPane;

/**
 * @author artur
 */
public class FieldEditor 
    extends 
        AbstractEditor<Field>
{
    public FieldEditor( EditorCallback<Field> callback ) 
    {
        super(callback);
        
        initComponents();
        
        setTitle( "Editor de Campos" );
        
        setHeaderText( "Editor de Campos Dinâmicos" );

        setSource(source);
    } 
    
    @Override
    protected void validadeInput( List<String> erros ) throws Exception 
    {
        if( ! labelField.isValid() )
        {
            erros.add("Label é requerido");
        }
        
        if( handlerField.getSelectedIndex() == -1 )
        {
            erros.add( "Tipo é requerido" );
        }
        
        else if ( handlerField.getSelected().getId() == Field.TYPE_CHOICE &&
                  valuesPane.isEmpty() )
        {
            erros.add( "Selecione ao menos um item para a lista" );
        }
    }

    @Override
    protected void obtainInput() 
    {
        source.setLabel( labelField.getValue() );
        source.setRequired( requeriedField.isYesOption() );
        source.setType( handlerField.getSelectedIndex() != -1 ? handlerField.getSelected().getId() : -1 );
        source.setTypeRequest( typeField.getSelectedIndex() != -1 ? typeField.getSelected().getId() : 0 );
        
       callback.properties.put( "values", valuesPane.getFieldValues() );
    }

    @Override
    protected void resize() 
    {
        valuesPane.resize( getWidth() -100  );
        valuesPane.setMinWidth( getWidth() - 100  );
        requeriedField.setPrefWidth( getWidth() );
        getDialogPane().requestLayout();
    }

    @Override
    protected void setSource( Field source ) 
    {
        try
        {
            labelField.setText( source.getLabel() );
            requeriedField.setOption( source.isRequired() );
            handlerField.setSelectedIndex( source.getType() );
            typeField.setSelected( com.paa.requestnow.model.ModuleContext
                                                            .getInstance()
                                                            .getTypeManager()
                                                            .get( source.getTypeRequest() ) );
            
            valuesPane.setSource( source );
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
    
        gridPane.add( lbLabel,          0, 0, 1, 1 );
        gridPane.add( labelField,       1, 0, 3, 1 );
        
        gridPane.add( lbHandler,        0, 2, 1, 1 );
        gridPane.add( handlerField,     1, 2, 3, 1 );
    
        gridPane.add( lbRequired,       0, 3, 1, 1 );
        gridPane.add( requeriedField,   1, 3, 3, 1 );
        
        gridPane.add( valuesPane,       0, 4, 4, 1 );
        
        getDialogPane().setContent( gridPane );
        
        handlerField.addEventHandler( HandlerSelector.Events.ON_SELECT, new EventHandler<Event>() 
        {
            @Override
            public void handle(Event t) 
            {
                valuesPane.refresh( handlerField.getSelected() != null ? handlerField.getSelected().getId() : 0 );
            }
        } );
    
    }
    
    private GridPane gridPane            = new GridPane();
    private FieldsValuesPane valuesPane  = new FieldsValuesPane();
    private LabelField lbLabel           = new LabelField( "Label", true );
    private MaskTextField labelField     = new MaskTextField();
    
    private LabelField lbType            = new LabelField( "Tipo Requisição", true );
    private TypeSelector typeField       = new TypeSelector();
    
    private LabelField lbHandler         = new LabelField( "Tipo", true );
    private HandlerSelector handlerField = new HandlerSelector();
    
    private LabelField lbRequired        = new LabelField( "Requerido", true );
    private YesNoSelector requeriedField = new YesNoSelector();
}
