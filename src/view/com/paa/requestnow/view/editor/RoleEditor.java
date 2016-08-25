package com.paa.requestnow.view.editor;

import com.paa.requestnow.model.data.Role;
import com.paa.requestnow.view.util.EditorCallback;
import com.paa.requestnow.view.util.LabelField;
import com.paa.requestnow.view.util.MaskTextField;
import java.util.List;
import javafx.scene.layout.GridPane;

/**
 * @author artur
 */
public class RoleEditor 
    extends 
        AbstractEditor<Role>
{

    public RoleEditor(EditorCallback<Role> callback) 
    {
        super(callback);
        
        initComponents();
        
        setTitle( "Editor de Role" );
        
        setHeaderText( "Editor de Role" );

        setSource(source);
    } 
    
    @Override
    protected void validadeInput(List<String> erros) throws Exception 
    {
        if( ! nameField.isValid() )
        {
            erros.add("Nome é requerido");
        }
    }

    @Override
    protected void obtainInput() 
    {
        source.setName( nameField.getValue() );
    }

    @Override
    protected void resize() 
    {
        getDialogPane().requestLayout();
    }

    @Override
    protected void setSource( Role source) 
    {
       nameField.setText(source.getName());
    }
    
    private void initComponents()
    {
        gridPane.setVgap( 20 );
        gridPane.setHgap( 20 );
        gridPane.setStyle( "-fx-padding: 30;" );
    
        gridPane.add( lbName,           0, 0, 1, 1 );
        gridPane.add( nameField,        1, 0, 3, 1 );
    
        
        getDialogPane().setContent( gridPane );
    
    }
    
    private GridPane gridPane            = new GridPane();
    private LabelField lbName            = new LabelField( "Nome", true );
    private MaskTextField nameField      = new MaskTextField();
}
