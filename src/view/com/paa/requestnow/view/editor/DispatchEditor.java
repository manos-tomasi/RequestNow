package com.paa.requestnow.view.editor;

import com.paa.requestnow.model.ApplicationUtilities;
import com.paa.requestnow.model.data.RequestRoute;
import com.paa.requestnow.panes.TabDispatch;
import com.paa.requestnow.panes.TabRequestRoute;
import com.paa.requestnow.view.util.ActionButton;
import com.paa.requestnow.view.util.Prompts;
import java.sql.Timestamp;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/*
 * @author lucas
 */
public abstract class DispatchEditor  
{
    private RequestRoute source;
    
    private DispatchEditor(){}
    
    public DispatchEditor( RequestRoute source ) 
    {
        this.source = source;
        
        initComponents();
    }
    
    
    public void open()
    {
        stage.setMaximized(true);
        stage.showAndWait();
    }
    
    private void resize( )
    {
        tabDispatch.setSize( panel.getHeight(), panel.getWidth() );
    }
    
    private void obtainInput( int state )
    {
        source.setInfo( tabDispatch.obtainInfo() );
        source.setUser( ApplicationUtilities.getInstance().getActiveUser().getId() );
        source.setOut( new Timestamp( System.currentTimeMillis() ) );
        source.setState( state );
    }
    
    private void dispatch( int state )
    {
        try
        {
            if ( tabDispatch.obtainInfo() == null || tabDispatch.obtainInfo().isEmpty() )
            {
                Prompts.info( "Preencha uma justificativa para o despacho!" );
            }
            
            else
            {
                obtainInput( state );

                onDispatch( source );
    
                stage.close();
            }
        }
        catch( Exception e )
        {
            ApplicationUtilities.logException(e);
        }
    }
    
    private void cancel()
    {
        try
        {
            onCancel();

            stage.close();
        }
        catch ( Exception e )
        {
            ApplicationUtilities.logException(e);
        }
    }
    
    protected abstract void onCancel() throws Exception;
    protected abstract void onDispatch( RequestRoute source ) throws Exception;
    
    private void initComponents()
    {        
        tabRequestRoute.setSource( source );
        tab.getTabs().addAll( tabDispatch, tabRequestRoute );
        
        boxButton.getChildren().addAll( btnCancel, btnApprove, btnDisapprove );
        boxButton.setPadding( new Insets( 20, 20, 20, 20 ) );
        boxButton.setSpacing( 20 );
        boxButton.setAlignment( Pos.BOTTOM_RIGHT );
        
        btnApprove.setStyle( "-fx-background-color:#5cb85c;-fx-text-fill:#fff" );
        btnDisapprove.setStyle( "-fx-background-color:#d9534f;-fx-text-fill:#fff" );
        btnCancel.setStyle( "-fx-background-color:#fff;-fx-border-color:#d9534f;-fx-font-weight: bolder;-fx-text-fill:#d9534f" );

        stage.setTitle( "Despacho" );
        
        btnApprove.setMinWidth(150);
        btnDisapprove.setMinWidth(150);
        btnCancel.setMinWidth(150);
        
        panel.setCenter( tab );
        panel.setBottom( boxButton );
        
        Scene scene = new Scene(panel) ;
        stage.setScene( scene );
        
        scene.widthProperty().addListener( new ChangeListener<Number>() 
        {
            @Override 
            public void changed( ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth )
            {
               resize();
            }
        });
        
        scene.heightProperty().addListener(new ChangeListener<Number>() 
        {
            @Override 
            public void changed( ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) 
            {
                resize();
            }
        });
        
        stage.setOnCloseRequest(eh -> cancel() );
    }  
    
    private Stage           stage           = new Stage();
    private HBox            boxButton       = new HBox();
    private BorderPane      panel           = new BorderPane();
    private TabPane         tab             = new TabPane();
    private TabDispatch     tabDispatch     = new TabDispatch();
    private TabRequestRoute tabRequestRoute = new TabRequestRoute();
    
    private ActionButton    btnApprove    = new ActionButton( "Approve"   , "finish.png",  new ActionButton.ActionHandler() 
    {
        @Override
        public void onEvent(Event t) throws Exception 
        {
            dispatch( RequestRoute.APPROVED );
        }
    });
    
    private ActionButton    btnDisapprove = new ActionButton( "Disapprove", "reproved.png", new ActionButton.ActionHandler() 
    {
        @Override
        public void onEvent(Event t) throws Exception 
        {
            dispatch( RequestRoute.DISAPPROVED );
        }
    });
    
    private ActionButton    btnCancel = new ActionButton( "Fechar", "delete.png", new ActionButton.ActionHandler() 
    {
        @Override
        public void onEvent(Event t) throws Exception 
        {
            cancel();
        }
    });
}