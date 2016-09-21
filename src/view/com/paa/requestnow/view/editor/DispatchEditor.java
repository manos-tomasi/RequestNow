package com.paa.requestnow.view.editor;

import com.paa.requestnow.control.RequestRouteController;
import com.paa.requestnow.model.ApplicationUtilities;
import com.paa.requestnow.model.data.RequestRoute;
import com.paa.requestnow.panes.TabDispatch;
import com.paa.requestnow.panes.TabRequest;
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
public class DispatchEditor  
{
    public static void edit( RequestRoute requestRoute )
    {
        DispatchEditor editor = new DispatchEditor();
        editor.setSource( requestRoute );
        editor.show();
    }
 
    private RequestRoute           source;
    private RequestRouteController controller = RequestRouteController.getInstance();
    
    private DispatchEditor() 
    {
        initComponents();
    }
    
    private void setSource( RequestRoute requestRoute )
    {
        source = requestRoute;
        tabRequest.setSource( requestRoute );
    }
    
    private void show()
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
            obtainInput( state );
            controller.dispatch( source );
            Prompts.info( "A rota foi "+ RequestRoute.STATES[state]  +" , com sucesso!" );
            stage.close();
        }
        catch( Exception e )
        {
            Prompts.error( "Houve um erro ao despachar" );
            ApplicationUtilities.logException(e);
        }
    }

    
    private void initComponents()
    {
        tab.getTabs().addAll( tabDispatch, tabRequest, tabRequestRoute );
        
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

    }  
    
    private Stage           stage           = new Stage();
    private HBox            boxButton       = new HBox();
    private BorderPane      panel           = new BorderPane();
    private TabPane         tab             = new TabPane();
    private TabDispatch     tabDispatch     = new TabDispatch();
    private TabRequest      tabRequest      = new TabRequest();
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
            stage.close();
        }
    });
}