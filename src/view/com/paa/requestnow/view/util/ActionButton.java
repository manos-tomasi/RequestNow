package com.paa.requestnow.view.util;

import com.paa.requestnow.model.ApplicationUtilities;
import com.paa.requestnow.model.ResourceLocator;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author artur
 */
public class ActionButton
    extends 
        Button
{
    private ActionButton(){}
    
    public ActionButton( String name, String icon, EventHandler handler ) 
    {
        try
        {
            ImageView imageView =  new ImageView( new Image( ResourceLocator.getInstance().getImageResource( icon ) ) );
            
            imageView.setFitHeight( 20 );
            imageView.setFitWidth( 20 );

            setGraphic( imageView );
            setText( name );
            setOnAction( handler );
            setCursor( Cursor.HAND );
            setAlignment( Pos.CENTER_LEFT );
            setMinWidth( 95 );
            setMaxWidth( 95 );
        
            setStyle( "-fx-background-radius: 10; " +
                       ApplicationUtilities.getBackground() +
                      "-fx-border-color: " + ApplicationUtilities.getColor2() +
                      "-fx-text-fill: #455A64;" +
                      "-fx-border-radius: 10; " +
                      "-fx-border-width: 2;"      +
                      "-fx-font-size: 10;"      +
                      "-fx-font-weight: bolder;"  );
        }
        
        catch ( Exception e )
        {
            ApplicationUtilities.logException( e );
        }
    }
    
}
