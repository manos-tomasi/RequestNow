package com.paa.requestnow.view.selectors;

import com.paa.requestnow.model.ResourceLocator;
import com.paa.requestnow.view.util.EditorCallback;
import java.util.List;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

/**
 * @author artur
 * @param <T>
 */
public class ListPicker<T> 
    extends 
        Dialog<T>
{
    public EditorCallback callback;
    public T source;
    public List<T> items;
    private ButtonType btSave   = new ButtonType( "Selecionar", ButtonBar.ButtonData.OK_DONE );
    private ButtonType btCancel = new ButtonType( "Cancelar",   ButtonBar.ButtonData.CANCEL_CLOSE );
    private ButtonType selectedButton;
    
    public ListPicker() 
    {
        initComponents();
        
        setTitle( "Seletor" );
    }
    
    public void setItems( List<T> items )
    {
        this.items = items;
        
        list.setItems( FXCollections.observableList( items ) );
    }
    
    public void setSelected( T source )
    {
        list.getSelectionModel().select( source );
    }
    
    public T getSelected()
    {
        return list.getSelectionModel().getSelectedItem();
    }
    
    public void setSelectedIndex( int index )
    {
        list.getSelectionModel().select( index );
    }
    
    public int getSelectedIndex()
    {
        return list.getSelectionModel().getSelectedIndex();
    }
    
    private void resize()
    {
        textField.setPrefWidth( getWidth() - searchButton.getWidth() );
        list.requestLayout();
    }
    
    private boolean validate()
    {
        return list.getSelectionModel().getSelectedItem() != null;
    }
    
    public void open( String text )
    {
        HBox hbox = new HBox( textField, searchButton );
        VBox vBox = new VBox( hbox, list );
        
        getDialogPane().setContent( vBox );
        
        setHeaderText( text );
        showAndWait();
    }
    
    private void initComponents()
    {
        textField.setPromptText( "Digite para filtrar" );
        
        ImageView imageView = new ImageView( new Image( ResourceLocator.getInstance().getImageResource( "search.png" ) ) );
        imageView.setFitHeight( 20 );
        imageView.setFitWidth( 20 );
        
        searchButton.setGraphic( imageView );
        searchButton.setStyle( "-fx-background-color: transparent" );
        
        list.setMinSize( 600, 450 );
        getDialogPane().setMinHeight( 600);
        
        getDialogPane().getButtonTypes().addAll( btCancel, btSave );
        
        list.setOnMouseClicked( new EventHandler<MouseEvent>() 
        {
            @Override
            public void handle( MouseEvent t )
            {
                if(  validate() )
                {
                    close();
                }
            }
        } );
        
        textField.textProperty().addListener( obs->
        {
            ObservableList<T> filterList = FXCollections.observableArrayList();
            
            items.stream().forEach( (item) -> 
            {
                String filter = textField.getText().toLowerCase();
            
                if ( item.toString().toLowerCase().startsWith( filter ) )
                        filterList.add( item );
            } );
            
            list.setItems( filterList );
        } );
        
        widthProperty().addListener( new ChangeListener<Number>() 
        {
            @Override 
            public void changed( ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth )
            {
               resize();
            }
        } );
        
        setOnCloseRequest( new EventHandler() {

            @Override
            public void handle( Event t ) 
            {
               if(  selectedButton == btSave )
               {
                   if ( ! validate() )
                   {
                        t.consume();
                   }
               }
            }
        } );

        setResultConverter( new Callback() 
        {
            @Override
            public Object call(  Object p ) 
            {
                return selectedButton = (ButtonType) p;
            }
        } );
    }
    
    private ListView<T> list = new ListView();
    private Button searchButton = new Button();
    private TextField textField = new TextField();
}
