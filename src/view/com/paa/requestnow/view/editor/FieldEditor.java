package com.paa.requestnow.view.editor;

import com.paa.requestnow.model.ApplicationUtilities;
import com.paa.requestnow.model.data.Field;
import com.paa.requestnow.panes.InfoPane;
import com.paa.requestnow.view.selectors.CategorySelector;
import com.paa.requestnow.view.selectors.HandlerSelector;
import com.paa.requestnow.view.selectors.StateSelector;
import com.paa.requestnow.view.selectors.TypeSelector;
import com.paa.requestnow.view.selectors.YesNoSelector;
import com.paa.requestnow.view.util.EditorCallback;
import com.paa.requestnow.view.util.LabelField;
import com.paa.requestnow.view.util.MaskTextField;
import java.util.List;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
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
        if( stateField.getSelectedIndex() == -1 )
        {
            erros.add("Situação é requerida");
        }
        
        if( ! labelField.isValid() )
        {
            erros.add("Label é requerido");
        }
        
        if( handlerField.getSelectedIndex() == -1 )
        {
            erros.add( "Tipo é requerido" );
        }
    }

    @Override
    protected void obtainInput() 
    {
        source.setLabel( labelField.getValue() );
        source.setState( stateField.getSelectedIndex() );
        source.setRequired( requeriedField.isYesOption() );
        source.setType( handlerField.getSelectedIndex() != -1 ? handlerField.getSelected().getId() : -1 );
        source.setTypeRequest( typeField.getSelectedIndex() != -1 ? typeField.getSelected().getId() : 0 );
    }

    @Override
    protected void resize() 
    {
        stateField.setPrefWidth( getWidth() );
        getDialogPane().requestLayout();
    }

    @Override
    protected void setSource( Field source ) 
    {
        try
        {
            labelField.setText( source.getLabel() );
            stateField.setSelectedIndex( source.getState() );
            requeriedField.setOption( source.isRequired() );
            handlerField.setSelectedIndex( source.getType() );
            typeField.setSelected( com.paa.requestnow.model.ModuleContext
                                                            .getInstance()
                                                            .getTypeManager()
                                                            .get( source.getTypeRequest() ) );
            
            
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
    
        gridPane.add( lbType,           0, 1, 1, 1 );
        gridPane.add( typeField,        1, 1, 3, 1 );
    
        gridPane.add( lbHandler,        0, 2, 1, 1 );
        gridPane.add( handlerField,     1, 2, 3, 1 );
    
        gridPane.add( lbRequired,       0, 3, 1, 1 );
        gridPane.add( requeriedField,   1, 3, 3, 1 );
        
        gridPane.add( lbState,          0, 4, 1, 1 );
        gridPane.add( stateField,       1, 4, 3, 1 );
        
        getDialogPane().setContent( gridPane );
    
    }
    
    private TabPane tabPane              = new TabPane();
    
    private GridPane gridPane            = new GridPane();
    private LabelField lbLabel           = new LabelField( "Label", true );
    private MaskTextField labelField     = new MaskTextField();
    
    private LabelField lbState           = new LabelField( "Situação", true );
    private StateSelector stateField     = new StateSelector();
    
    private LabelField lbType            = new LabelField( "Tipo Requisição", true );
    private TypeSelector typeField       = new TypeSelector();
    
    private LabelField lbHandler         = new LabelField( "Tipo", true );
    private HandlerSelector handlerField = new HandlerSelector();
    
    private LabelField lbRequired        = new LabelField( "Requerido", true );
    private YesNoSelector requeriedField = new YesNoSelector();
}
