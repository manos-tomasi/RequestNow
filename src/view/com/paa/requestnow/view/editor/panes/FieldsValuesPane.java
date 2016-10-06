package com.paa.requestnow.view.editor.panes;

import com.paa.requestnow.model.ApplicationUtilities;
import com.paa.requestnow.model.data.Field;
import com.paa.requestnow.model.data.FieldValue;
import com.paa.requestnow.model.filter.FieldValueFilter;
import com.paa.requestnow.view.util.ActionButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.VPos;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

/**
 * @author artur
 */
public class FieldsValuesPane 
    extends 
        GridPane
{
    private ObservableList<FieldValue> values = FXCollections.observableArrayList();
    
    private FieldValue current;
    private Field field;
    
    public FieldsValuesPane() 
    {
        initComponents();
    }
    
    
    public void setSource( Field field )
    {
        try
        {
            this.field = field;

            if ( field.getType() == Field.TYPE_CHOICE )
            {
                values.clear();
                values.addAll( com.paa.requestnow.model.ModuleContext.getInstance().getFieldValueManager().get( field ) );

                listView.setItems( values );
                setVisible( true );
            }

            else 
            {
                setVisible( false );
            }
        }
        
        catch ( Exception e )
        {
            ApplicationUtilities.logException( e );
        }
    }
    
    public boolean isEmpty()
    {
        return values.isEmpty();
    }
    
    public ObservableList<FieldValue> getFieldValues()
    {
        return values;
    }
    
    private void add()
    {
        String text = nameField.getText();

        if ( text != null && ! text.isEmpty() )
        {
            if ( current == null )
            {
                current = new FieldValue( field.getId(), text );
                
                values.add( current );
            }
            
            else 
            {
                FieldValue temp = new FieldValue( current.getId(), text );
                
                remove();
                
                values.add( temp ); 
            }
            
            nameField.clear();
            
            nameField.requestFocus();
            
            listView.setItems( values );
            
            listView.requestLayout();
        }
        
        current = null;
    }
    
    private void remove()
    {
        int index = listView.getSelectionModel().getSelectedIndex();
        
        if ( index != -1 )
        {
            values.remove( index );
            
            listView.setItems( values );
            
            listView.requestLayout();
            
            current = null;
            
            nameField.clear();
        }
    }
    
    
    public void refresh( int type )
    {
        if ( type == Field.TYPE_CHOICE )
        {
            setVisible( true );
        }
        
        else 
        {
            setVisible( false );
        }
    }
    
    
    public void resize( double width ) 
    {
        setMinWidth( getWidth() );
        nameField.setMinWidth( getWidth() );
        requestLayout();
    }
    
    private void initComponents()
    {
        add( nameField,    0, 0, 1, 1 );
        add( addButton,    1, 0, 3, 1 );
        
        add( listView,     0, 1, 1, 1 );
        add( removeButton, 1, 1, 3, 1 );
        
        setValignment( removeButton, VPos.TOP );
        
        listView.setOnMouseClicked( new EventHandler<MouseEvent>() 
        {
            @Override
            public void handle( MouseEvent t )
            {
                current = listView.getSelectionModel().getSelectedItem();
                
                if ( current != null )
                {
                    nameField.setText( current.getValue() );
                }
            }
        } );
    }
    
    private ListView<FieldValue> listView = new ListView();
    private TextField nameField = new TextField();
    private ActionButton addButton = new ActionButton( "Novo", "new.png", new ActionButton.ActionHandler() 
    {
        @Override
        public void onEvent( Event t ) throws Exception 
        {
            add();
        }
    } );
    
    private ActionButton removeButton = new ActionButton( "Excluir", "delete.png", new ActionButton.ActionHandler() 
    {
        @Override
        public void onEvent( Event t ) throws Exception 
        {
            remove();
        }
    } );
}
