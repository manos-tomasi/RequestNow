package com.paa.requestnow.panes;

import com.paa.requestnow.control.util.JsonUtilities;
import com.paa.requestnow.model.ResourceLocator;
import com.paa.requestnow.model.data.Request;
import com.paa.requestnow.model.data.RequestRoute;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.scene.control.Tab;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;

/**
 *
 * @author lucas
 */
public class TabRequest 
        extends 
            Tab
{
    private RequestRoute requestRoute;
    
    public TabRequest() 
    {
        initComponent();
    }
    
    private void refreshContent()
    {
        try 
        {
            if( requestRoute != null )
            {
                Request r = com.paa.requestnow.model.ModuleContext.getInstance().getRequestManager().get( requestRoute.getRequest() );

                if( r != null )
                {
                    String request = JsonUtilities.getRequest( r );
                    engine.executeScript( "setRequest( " + request + " )" );
                    
                    String fields  = JsonUtilities.getRequestField(r);
                    if( fields != null && fields.length() > 5 )
                    {
                        fields = "[" + fields.substring(2 , fields.length() -2 ) + "]";
                        fields = fields.replaceAll( "\"" , "" );
                        
                        engine.executeScript( "setFields(  " + fields + " )" );
                    }
                }
            }
        }

        catch ( Exception e) { /*ignore*/ }
                
    }

    public void setSource( RequestRoute requestRoute )
    {
        this.requestRoute = requestRoute;
    }
    
    private void initComponent()
    {
        setText( "Requisição" );
        
        engine.load( ResourceLocator.getInstance().getWebResource( "request.html" ) );
        engine.setJavaScriptEnabled( true );
        engine.getLoadWorker().stateProperty().addListener( new ChangeListener<Worker.State>() 
        {  
            @Override 
            public void changed( ObservableValue<? extends Worker.State> ov, Worker.State oldState, Worker.State newState )
            {
              if ( newState == Worker.State.SUCCEEDED )
                ( (JSObject) engine.executeScript( "window" ) ).setMember( "application", TabRequest.this );
            }
        } );
        
        engine.documentProperty().addListener( ( prop, oldDoc, newDoc ) -> refreshContent() );
        
        setContent(webView);
        setClosable(false);
    }
    
    private WebView webView = new WebView();
    private WebEngine engine = webView.getEngine();
    private StackPane pane = new StackPane();
}