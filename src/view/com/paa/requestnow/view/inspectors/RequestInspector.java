package com.paa.requestnow.view.inspectors;

import com.paa.requestnow.control.util.JsonUtilities;
import com.paa.requestnow.model.ApplicationUtilities;
import com.paa.requestnow.model.ResourceLocator;
import com.paa.requestnow.model.data.Request;
import com.paa.requestnow.view.util.FileUtilities;
import com.paa.requestnow.view.util.Prompts;
import java.io.File;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import netscape.javascript.JSObject;

/**
 * @author artur
 */
public class RequestInspector
{
    public static void inspect( Request requestRoute )
    {
        RequestInspector inspector = new RequestInspector();
        inspector.setSource( requestRoute );
        inspector.show();
    }
 
    private Request source;
    
    private RequestInspector() 
    {
        initComponents();
    }
    
    private void setSource( Request request )
    {
        source = request;
    }
    
    private void show()
    {
        stage.showAndWait();
    }
    
    private void refreshContent()
    {
        try 
        {
            if( source != null )
            {
                String request = JsonUtilities.getRequest( source );
                engine.executeScript( "setRequest( " + request + " )" );

                String fields  = JsonUtilities.getRequestField( source );
                if( fields != null && fields.length() > 5 )
                {
                    fields = "[" + fields.substring(2 , fields.length() -2 ) + "]";
                    fields = fields.replaceAll( "\"" , "" );

                    engine.executeScript( "setFields(  " + fields + " )" );
                }
            }
        }
        
        catch ( Exception e )
        {
            ApplicationUtilities.logException( e );
        }
    }
    
    public void download( String url )
    {
        File file = FileUtilities.saveFile( "Salvar Anexo", url.substring( url.lastIndexOf( "/" ) + 1 ) );
                
        if( file != null && ! file.exists() )
        {
            Prompts.process( "Salvando arquivo..." , new Task<Void>() 
            {
                @Override
                protected Void call() throws Exception 
                {
                    FileUtilities.download( url, file.getAbsolutePath() );

                    return  null;
                }
            } );
        }
    }
    
    private void initComponents()
    {
        
        engine.load( ResourceLocator.getInstance().getWebResource( "request.html" ) );
        engine.setJavaScriptEnabled( true );
        engine.getLoadWorker().stateProperty().addListener( new ChangeListener<Worker.State>() 
        {  
            @Override 
            public void changed( ObservableValue<? extends Worker.State> ov, Worker.State oldState, Worker.State newState )
            {
              if ( newState == Worker.State.SUCCEEDED )
                ( (JSObject) engine.executeScript( "window" ) ).setMember( "application", RequestInspector.this );
            }
        } );
        
        engine.documentProperty().addListener( ( prop, oldDoc, newDoc ) -> refreshContent() );
        
        pane.getChildren().add(webView);
        
        Scene scene = new Scene(pane) ;
        stage.setScene( scene );
    }  
    
    private Stage     stage = new Stage();
    private StackPane pane  = new StackPane();
    private WebView webView = new WebView();
    private WebEngine engine = webView.getEngine();
}
