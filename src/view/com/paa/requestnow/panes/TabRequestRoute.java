package com.paa.requestnow.panes;

import com.paa.requestnow.control.util.JsonUtilities;
import com.paa.requestnow.model.ApplicationUtilities;
import com.paa.requestnow.model.ResourceLocator;
import com.paa.requestnow.model.data.Request;
import com.paa.requestnow.model.data.RequestRoute;
import com.paa.requestnow.view.inspectors.RequestRouteInspector;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import netscape.javascript.JSObject;

/**
 *
 * @author lucas
 */
public class TabRequestRoute 
        extends 
            Tab
{    
    public TabRequestRoute() 
    {
        initComponents();
    }
    private RequestRoute source;
    
    public void setSource( RequestRoute dispatch )
    {
        source = dispatch;
    }
    
    private void refreshContent()
    {
        try 
        {
            if( source != null )
            {
                Request request = com.paa.requestnow.model.ModuleContext.getInstance().getRequestManager().get( source.getRequest() );

                engine.executeScript( "setRequest( " + JsonUtilities.getRequest( request ) + ")" );
                
                String fields  = JsonUtilities.getRequestField( request );
                
                if( fields != null && fields.length() > 5 )
                {
                    fields = "[" + fields.substring(2 , fields.length() -2 ) + "]";
                    fields = fields.replaceAll( "\"" , "" );

                    engine.executeScript( "setFields(  " + fields + " )" );
                }
                
                String dispatch = JsonUtilities.getDispatchs( source );
                
                if( dispatch != null && dispatch.length() > 5 )
                {
                    dispatch = "[" + dispatch.substring(2 , dispatch.length() -2 ) + "]";
                    dispatch = dispatch.replaceAll( "\"" , "" );

                    engine.executeScript( "setDispatchs( " + dispatch + " )" );                
                }
            }
        }
        
        catch ( Exception e )
        {
            ApplicationUtilities.logException( e );
        }
    }
    
    private void initComponents()
    {
        
        engine.load( ResourceLocator.getInstance().getWebResource( "request_routes.html" ) );
        engine.setJavaScriptEnabled( true );
        engine.getLoadWorker().stateProperty().addListener( new ChangeListener<Worker.State>() 
        {  
            @Override 
            public void changed( ObservableValue<? extends Worker.State> ov, Worker.State oldState, Worker.State newState )
            {
              if ( newState == Worker.State.SUCCEEDED )
                ( (JSObject) engine.executeScript( "window" ) ).setMember( "application", TabRequestRoute.this );
            }
        } );
        
        engine.documentProperty().addListener( ( prop, oldDoc, newDoc ) -> refreshContent() );
        
        pane.getChildren().add(webView);
        
        setContent(pane);
        setText( "Detalhes" );
        setClosable(false);
    }  
    private StackPane pane  = new StackPane();
    private WebView webView = new WebView();
    private WebEngine engine= webView.getEngine();
}