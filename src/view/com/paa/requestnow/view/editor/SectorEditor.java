package com.paa.requestnow.view.editor;

import com.paa.requestnow.model.data.Sector;
import com.paa.requestnow.view.selectors.StateSelector;
import com.paa.requestnow.view.util.EditorCallback;
import com.paa.requestnow.view.util.LabelField;
import com.paa.requestnow.view.util.MaskTextField;
import java.util.List;
import javafx.scene.layout.GridPane;

/**
 *
 * @author lucas
 */
public class SectorEditor 
        extends 
            AbstractEditor<Sector>
{

    public SectorEditor(EditorCallback<Sector> callback) 
    {
        super(callback);
        
        initComponents();
        
        setTitle( "Editor de Setores" );
        
        setHeaderText( "Editor de Setores" );

        setSource(source);
    } 
    
    @Override
    protected void validadeInput(List<String> erros) throws Exception 
    {
        if( stateField.getSelectedIndex() == -1 )
        {
            erros.add("Situação é requirida");
        }
        
        if( !nameField.isValid() )
        {
            erros.add("Nome é requirido");
        }
    }

    @Override
    protected void obtainInput() 
    {
        source.setName( nameField.getValue() );
        source.setState( stateField.getSelectedIndex() );
    }

    @Override
    protected void resize() 
    {
        stateField.setPrefWidth( getWidth() );
        getDialogPane().requestLayout();
    }

    @Override
    protected void setSource(Sector source) 
    {
       nameField.setText(source.getName());
       stateField.setSelectedIndex(source.getState());
    }
    
    private void initComponents()
    {
        gridPane.setVgap( 20 );
        gridPane.setHgap( 20 );
        gridPane.setStyle( "-fx-padding: 30;" );
    
        gridPane.add( lbName,           0, 0, 1, 1 );
        gridPane.add( nameField,        1, 0, 3, 1 );
    
        gridPane.add( lbState,          0, 1, 1, 1 );
        gridPane.add( stateField,       1, 1, 3, 1 );
    
        
        getDialogPane().setContent( gridPane );
    
    }
    
    private GridPane gridPane            = new GridPane();
    private LabelField lbName            = new LabelField( "Nome", true );
    private MaskTextField nameField      = new MaskTextField();
    private LabelField lbState           = new LabelField( "Situação", true );
    private StateSelector stateField     = new StateSelector();
}
