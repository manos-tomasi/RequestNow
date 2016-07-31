package com.paa.requestnow.view.util;

import com.paa.requestnow.model.ApplicationUtilities;
import com.paa.requestnow.model.ResourceLocator;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author artur
 */
public class ContextMenuItem 
    extends 
        javafx.scene.control.MenuItem
{

    public ContextMenuItem( String text, String icon, EventHandler eventHandler ) 
    {
        setOnAction( eventHandler );
    
        ImageView imageView =  new ImageView( new Image( ResourceLocator.getInstance().getImageResource( icon ) ) );
        
        imageView.setFitHeight( 20 );
        imageView.setFitWidth( 20 );

        setGraphic( imageView );
        
        setText( text );
        setStyle( "-fx-background-color: transparent;"+
                  "-fx-text-fill:" + ApplicationUtilities.getColor() +
                  "-fx-border-color: " + ApplicationUtilities.getColor2() +
                  "-fx-border-width: 1;"  );
    }
    
}
