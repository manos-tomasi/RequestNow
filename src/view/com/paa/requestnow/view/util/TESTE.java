package com.paa.requestnow.view.util;

import com.paa.requestnow.control.util.JsonUtilities;
import com.paa.requestnow.model.ResourceLocator;
import com.paa.requestnow.model.data.Request;
import com.paa.requestnow.model.data.Type;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import netscape.javascript.JSObject;

public class TESTE extends Application{

    public static void main(String[] args) throws Exception {
        launch(args);
    }
    private void refreshContent()
    {
        try 
        {
            Request r = com.paa.requestnow.model.ModuleContext.getInstance().getRequestManager().get( 15 );
            
            if( r != null )
            {
                String t = JsonUtilities.getRequest( r );
                String f = JsonUtilities.getRequestField(r);
                
                engine.executeScript( "setRequest( " + t + " )" );
            }
        }

        catch ( Exception e) { /*ignore*/ }
                
    }

    @Override
    public void start(Stage stage) throws Exception {
        engine.load( ResourceLocator.getInstance().getWebResource( "request.html" ) );
        engine.setJavaScriptEnabled( true );
        engine.getLoadWorker().stateProperty().addListener( new ChangeListener<Worker.State>() 
        {  
            @Override 
            public void changed( ObservableValue<? extends Worker.State> ov, Worker.State oldState, Worker.State newState )
            {
              if ( newState == Worker.State.SUCCEEDED )
                ( (JSObject) engine.executeScript( "window" ) ).setMember( "application", TESTE.this );
            }
        } );
        
        engine.documentProperty().addListener( ( prop, oldDoc, newDoc ) -> refreshContent() );
        
        pane.getChildren().add( webView );

        stage.setScene( new Scene( pane, 800, 600 ) );
        
        stage.show();
        
    }
    
    private WebView webView = new WebView();
    private WebEngine engine = webView.getEngine();
    
    private StackPane pane = new StackPane();
}
