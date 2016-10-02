package com.paa.requestnow.panes.modules;

import com.paa.requestnow.model.ResourceLocator;
import java.util.Collections;
import java.util.List;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.scene.control.Button;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;

/**
 * @author artur
 */
public class HomePane 
    extends 
        AbstractModulesPane
{
    public HomePane() 
    {
        initComponents();
    }

    @Override
    public List<Button> getActions() 
    {
        return Collections.EMPTY_LIST;
    }

    @Override
    public void refreshContent()
    {
        
    }

    @Override
    public void resizeComponents(double height, double width) 
    {
        view.setPrefSize( width, height );
    }
    
    
    private void initComponents()
    {
        engine.load( ResourceLocator.getInstance().getWebResource( "chart.html" ) );
        engine.setJavaScriptEnabled( true );
        engine.getLoadWorker().stateProperty().addListener( new ChangeListener<State>() 
        {  
            @Override 
            public void changed( ObservableValue<? extends State> ov, State oldState, State newState )
            {
              if ( newState == State.SUCCEEDED )
                ( (JSObject) engine.executeScript( "window" ) ).setMember( "application", HomePane.this );
            }
        } );
        
        
        getChildren().add( view );
    }
   
    private WebView view = new WebView();
    private WebEngine engine = view.getEngine();
}
