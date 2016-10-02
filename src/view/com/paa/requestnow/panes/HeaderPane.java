package com.paa.requestnow.panes;

import com.paa.requestnow.control.socket.Client;
import com.paa.requestnow.model.ApplicationUtilities;
import com.paa.requestnow.model.data.Option;
import com.paa.requestnow.model.data.RequestRoute;
import com.paa.requestnow.model.filter.RequestRouteFilter;
import com.paa.requestnow.view.util.NotificationItem;
import com.paa.requestnow.view.util.Prompts;
import java.util.List;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 * @author artur
 */
public class HeaderPane
    extends 
        HBox
{
    private List<RequestRoute> routes;
    
    public HeaderPane() 
    {
        initComponents();
        load();
    }
   
    
    public void resize( BorderPane pane )
    {
        setLayoutX( pane.getWidth() );
        setSpacing( pane.getWidth() - hbox.getWidth() - lbModule.getWidth() );
        requestLayout();
    }

    private void load()
    {
        try
        {
            RequestRouteFilter filter = new RequestRouteFilter();
            filter.addCondition( RequestRouteFilter.MYRESPONSE, Option.YES );
            filter.addCondition( RequestRouteFilter.STATE, new Option( RequestRoute.STOPED, "Em Andamento") );

            routes = com.paa.requestnow.model.ModuleContext.getInstance().getRequestRouteManager().get( filter );
            
            refreshNotifications();
        }
        
        catch ( Exception e )
        {
            ApplicationUtilities.logException( e );
        }
    }
    
    private void refreshNotifications()
    {
        Platform.runLater( new Runnable() 
        {
            @Override
            public void run() 
            {
                try
                {
                    menu.getItems().clear();

                    for ( RequestRoute r : routes )
                    {
                        String text = com.paa.requestnow.model.ModuleContext.getInstance().getRequestManager().getRequestNotification( r.getRequest() );

                        menu.getItems().add( new NotificationItem( text, "orders_white.png", new EventHandler() 
                        {
                            @Override
                            public void handle( Event t )
                            {
                                Prompts.alert( "Compre a versão FULL para essa funcionalidade" );
                            }
                        } ) );

                    }

                    if ( routes.size() > 0 )
                    {
                        btnNotifications.setText( String.valueOf( routes.size() ) );
                        btnNotifications.setVisible( true );
                    }

                    else
                    {
                        btnNotifications.setVisible( false );
                    }

                    btnNotifications.requestLayout();
                    
                    hbox.requestLayout();
                    
                    requestLayout();
                }

                catch ( Exception e )
                {
                    ApplicationUtilities.logException( e );
                }
            }
        } );
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
        
        btnNotifications.setCache( true );
        btnNotifications.setCursor( Cursor.HAND );
        btnNotifications.setStyle(  "-fx-font-weight: bolder;" +
                                    "-fx-font-size: 10pt;" +
                                    "-fx-padding: 5px;" +
                                    "-fx-border-radius: 100;" +
                                    "-fx-border-width: 10px;" +
                                    "-fx-min-width: 25px;" +
                                    "-fx-font-family: \"Helvetica, Verdana, sans-serif\";" +
                                    ApplicationUtilities.getBackground() +
                                    "-fx-background-radius: 25;" +
                                    "-fx-text-fill: " + ApplicationUtilities.getColor2() );
      
        btnNotifications.setContextMenu( menu );
        
        menu.setStyle( ApplicationUtilities.getBackground2() );
        
        lbModule.setCache( true );
        lbModule.setText( "Home" );
        lbModule.setStyle( "-fx-padding: 10 0 0 10;" +
                           "-fx-font-weight: bolder;" +
                           "-fx-font-size: 26pt;" +
                           "-fx-font-family: \"Helvetica, Verdana, sans-serif\";" +
                           "-fx-text-fill: " + ApplicationUtilities.getColor() );
        
        setStyle( "-fx-border-color:" + ApplicationUtilities.getColor() + "-fx-border-width: 0 0 2 0; -fx-padding: 4 0 4 0;" + ApplicationUtilities.getBackground2() );
        
        getChildren().addAll( lbModule, hbox );
        
        client.start();

        btnNotifications.setOnAction( new EventHandler<ActionEvent>()
        {
            @Override
            public void handle( ActionEvent t )
            {
                menu.show( btnNotifications, Side.BOTTOM, 0, 0 );
            }
        } );
    }
    
    private Label lbApplication = new Label();
    private Label lbModule = new Label();
    private Button btnNotifications = new Button();
    
    private HBox hbox = new HBox(lbApplication, btnNotifications ); 
    
    private Client client = new Client() 
    {
        @Override
        public void onRecive( Object data ) throws Exception 
        {
            load();

            refreshNotifications();
        }
    };
    
    private ContextMenu menu = new ContextMenu();
    
}
