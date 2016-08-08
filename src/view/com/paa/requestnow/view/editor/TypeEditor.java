package com.paa.requestnow.view.editor;

import com.paa.requestnow.model.ApplicationUtilities;
import com.paa.requestnow.model.data.Type;
import com.paa.requestnow.panes.InfoPane;
import com.paa.requestnow.view.selectors.CategorySelector;
import com.paa.requestnow.view.selectors.StateSelector;
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
public class TypeEditor 
    extends 
        AbstractEditor<Type>
{
    public TypeEditor( EditorCallback<Type> callback ) 
    {
        super(callback);
        
        initComponents();
        
        setTitle( "Editor de Tipo" );
        
        setHeaderText( "Editor de Tipo de Requisição" );

        setSource(source);
    } 
    
    @Override
    protected void validadeInput(List<String> erros) throws Exception 
    {
        if( stateField.getSelectedIndex() == -1 )
        {
            erros.add("Situação é requerida");
        }
        
        if( ! nameField.isValid() )
        {
            erros.add("Nome é requerido");
        }
        
        if( categoryField.getSelectedIndex() == -1 )
        {
            erros.add( "Categoria é requerido" );
        }
    }

    @Override
    protected void obtainInput() 
    {
        source.setName( nameField.getValue() );
        source.setState( stateField.getSelectedIndex() );
        source.setCategory( categoryField.getSelectedIndex() != -1 ? categoryField.getSelected().getId() : 0 );
        source.setInfo( infoField.getInfo() );
    }

    @Override
    protected void resize() 
    {
        stateField.setPrefWidth( getWidth() );
        getDialogPane().requestLayout();
    }

    @Override
    protected void setSource( Type source ) 
    {
        try
        {
            nameField.setText( source.getName() );
            stateField.setSelectedIndex( source.getState() );
            categoryField.setSelected( com.paa.requestnow.model.ModuleContext
                                                                .getInstance()
                                                                .getCategoryManager()
                                                                .get( source.getCategory() ) );
            infoField.setInfo( source.getInfo() );
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
    
        gridPane.add( lbName,           0, 0, 1, 1 );
        gridPane.add( nameField,        1, 0, 3, 1 );
    
        gridPane.add( lbCategory,       0, 1, 1, 1 );
        gridPane.add( categoryField,    1, 1, 3, 1 );
    
        gridPane.add( lbState,          0, 2, 1, 1 );
        gridPane.add( stateField,       1, 2, 3, 1 );
        
        tabPane.getTabs().addAll( new Tab( "Geral", gridPane ), infoField );
        
        getDialogPane().setContent( tabPane );
    
    }
    
    private TabPane tabPane              = new TabPane();
    
    private GridPane gridPane            = new GridPane();
    private LabelField lbName            = new LabelField( "Nome", true );
    private MaskTextField nameField      = new MaskTextField();
    private LabelField lbState           = new LabelField( "Situação", true );
    private StateSelector stateField     = new StateSelector();
    private LabelField lbCategory        = new LabelField( "Categoria", true );
    private CategorySelector categoryField = new CategorySelector();
    private InfoPane infoField           = new InfoPane();
}
