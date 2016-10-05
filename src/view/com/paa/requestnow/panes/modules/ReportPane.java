package com.paa.requestnow.panes.modules;

import com.paa.requestnow.model.ResourceLocator;
import java.util.Collections;
import java.util.List;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.scene.control.Button;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;

/**
 * @author artur
 */
public class ReportPane 
    extends 
        AbstractModulesPane
{
  private String serie;
    private String drilldown;
    
    public ReportPane() 
    {
        initComponents();
    }

    @Override
    public List<Button> getActions() 
    {
        return Collections.EMPTY_LIST;
    }

    @Override
    public void resizeComponents(double height, double width) 
    {
        webView.setMinSize( width, height );
        webView.requestLayout();
    }
    
    @Override
    public void refreshContent()
    {
        try 
        {
            makeJsonTypes();
            
            engine.executeScript( "drilldown( " + serie + ", " + drilldown + " );" );
        }

        catch ( Exception e) {/*ignore*/ }
    }
    
    private void makeJsonTypes() throws Exception
    {
        serie = com.paa.requestnow.model.ModuleContext.getInstance().getCategoryManager().getDrilldownCategories();
        drilldown = com.paa.requestnow.model.ModuleContext.getInstance().getTypeManager().getDrilldownTypes();
    }
    
    private void initComponents()
    {
        engine.load( ResourceLocator.getInstance().getWebResource( "drilldown.html" ) );
        engine.setJavaScriptEnabled( true );
        engine.getLoadWorker().stateProperty().addListener(new ChangeListener<Worker.State>() 
        {  
            @Override 
            public void changed( ObservableValue<? extends Worker.State> ov, Worker.State oldState, Worker.State newState )
            {
              if ( newState == Worker.State.SUCCEEDED )
                ( (JSObject) engine.executeScript( "window" ) ).setMember("application", ReportPane.this );
            }
        } );
        
        engine.documentProperty().addListener( ( t ) -> refreshContent() );
        
        getChildren().add( webView );
    }
    
    private WebView webView = new WebView();
    private WebEngine engine = webView.getEngine();
}
