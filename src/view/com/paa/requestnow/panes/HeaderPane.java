package com.paa.requestnow.panes;

import com.paa.requestnow.control.socket.Client;
import com.paa.requestnow.model.ApplicationUtilities;
import com.paa.requestnow.model.data.RequestRoute;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 * @author artur
 */
public class HeaderPane
    extends 
        HBox
{
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
    }
    
    private Label lbApplication = new Label();
    private Label lbModule = new Label();
    
    private Client client = new Client() 
    {
        @Override
        public void onRecive( Object data ) throws Exception 
        {
            System.out.println( data );
            if ( data instanceof RequestRoute )
            {
                Platform.runLater( new Runnable() {
                    @Override
                    public void run() {
                        RequestRoute req = (RequestRoute) data;

                        lbApplication.setText( lbApplication.getText() + req.getId() );
                        lbApplication.requestLayout();
                    }
                });
            }
        }
    };
    
}
