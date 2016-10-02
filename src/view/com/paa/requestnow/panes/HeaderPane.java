package com.paa.requestnow.panes;

import com.paa.requestnow.control.socket.Client;
import com.paa.requestnow.model.ApplicationUtilities;
import com.paa.requestnow.model.data.Option;
import com.paa.requestnow.model.data.Request;
import com.paa.requestnow.model.data.RequestRoute;
import com.paa.requestnow.model.data.User;
import com.paa.requestnow.model.filter.RequestRouteFilter;
import com.paa.requestnow.view.util.ContextMenuItem;
import com.paa.requestnow.view.util.Prompts;
import java.util.HashSet;
import java.util.Set;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 * @author artur
 */
public class HeaderPane
    extends 
        HBox
{
    private Set<RequestRoute> routes = new HashSet();
    
    public HeaderPane() 
    {
        initComponents();
    }
   
    
    public void resize( BorderPane pane )
    {
        setLayoutX( pane.getWidth() );
        setSpacing( pane.getWidth() - lbApplication.getWidth() - lbModule.getWidth() );
        requestLayout();
    }

    private void load()
    {
        try
        {
            RequestRouteFilter filter = new RequestRouteFilter();
            filter.addCondition( RequestRouteFilter.MYRESPONSE, Option.YES );
            filter.addCondition( RequestRouteFilter.STATE, RequestRoute.STATE_ACTIVE );

            routes.addAll( com.paa.requestnow.model.ModuleContext.getInstance().getRequestRouteManager().get( filter ) );
            
            for ( RequestRoute r : routes )
            {
                String text = com.paa.requestnow.model.ModuleContext.getInstance().getRequestManager().getRequestNotification( r.getRequest() );

                menu.getItems().add( new ContextMenuItem( text, "dispatch.png", new EventHandler() {
                    @Override
                    public void handle(Event t) {
                        Prompts.alert( "Compre a versão FULL para essa funcionalidade" );
                    }
                } ) );
            }
        }
        
        catch ( Exception e )
        {
            ApplicationUtilities.logException( e );
        }
    }
    
    private void refreshNotifications( RequestRoute req )
    {
        try
        {
            if ( req != null )
            {
                String text = com.paa.requestnow.model.ModuleContext.getInstance().getRequestManager().getRequestNotification( req.getRequest() );

                menu.getItems().add( new ContextMenuItem( text, "dispatch.png", new EventHandler() {
                    @Override
                    public void handle(Event t) {
                        Prompts.alert( "Compre a versão FULL para essa funcionalidade" );
                    }
                } ) );
                
                lbApplication.requestLayout();
            }
        }
        
        catch ( Exception e )
        {
            ApplicationUtilities.logException( e );
        }
    }
    
    public void setApplicationName( String text )
    {
        lbApplication.setText( text );
        lbApplication.autosize();
    }
    
    
    public void setModule( String text )
    {
        lbModule.setText( text );
        lbModule.autosize();
    }
    
    
    private void initComponents()
    {
        lbApplication.setCache( true );
        lbApplication.setText( ApplicationUtilities.getInstance().getCompanny() );
        lbApplication.setStyle( "-fx-padding: 10 10 0 0;" + 
                                "-fx-font-weight: bolder;" +
                                "-fx-font-size: 26pt;" +
                                "-fx-font-family: \"Helvetica, Verdana, sans-serif\";" +
                                "-fx-text-fill: " + ApplicationUtilities.getColor() );
      
        lbModule.setCache( true );
        lbModule.setText( "Home" );
        lbModule.setStyle( "-fx-padding: 10 0 0 10;" +
                           "-fx-font-weight: bolder;" +
                           "-fx-font-size: 26pt;" +
                           "-fx-font-family: \"Helvetica, Verdana, sans-serif\";" +
                           "-fx-text-fill: " + ApplicationUtilities.getColor() );
        
        setStyle( "-fx-border-color:" + ApplicationUtilities.getColor() + "-fx-border-width: 0 0 2 0; -fx-padding: 4 0 4 0;" + ApplicationUtilities.getBackground2() );
        getChildren().addAll( lbModule, lbApplication );
        
        client.start();
        
        lbApplication.setContextMenu( menu );
        
        
        lbApplication.setOnAction( new EventHandler<ActionEvent>()
        {
            @Override
            public void handle( ActionEvent t )
            {
                menu.show( lbApplication, Side.BOTTOM, 0, 0 );
            }
        } );
    }
    
    private Button lbApplication = new Button();
    private Label lbModule = new Label();
    
    private Client client = new Client() 
    {
        @Override
        public void onRecive( Object data ) throws Exception 
        {
            if ( data instanceof RequestRoute )
            {
                RequestRoute req = (RequestRoute) data;
                
                refreshNotifications( req );
            }
        }
    };
    
    private ContextMenu menu = new ContextMenu();
    
}
