package com.paa.requestnow.panes;

import com.paa.requestnow.model.ApplicationUtilities;
import com.paa.requestnow.panes.modules.AbstractModulesPane;
import com.paa.requestnow.panes.modules.ReportPane;
import com.paa.requestnow.panes.modules.EntriePane;
import com.paa.requestnow.panes.modules.HomePane;
import com.paa.requestnow.view.util.MenuItem;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.layout.VBox;

/**
 * @author artur
 */
public class MenuPane
    extends 
        VBox
{
    public MenuPane( EventHandler handler ) 
    {
        this.handler = handler;
        
        initComponents();
        initListeners();
    }
    
    private void refreshContent( AbstractModulesPane pane )
    {
        if( handler != null )
        {
            selectedPane = pane;

            handler.handle( new Event( pane, null, EventType.ROOT ) );
            pane.refreshContent();
        }
    }
    
    public void resize()
    {
        if( selectedPane != null )
        {
            selectedPane.resizeComponents( getHeight(), getWidth() );
        }
    }
    
    private void initComponents()
    {
        itemReport.setDisable( ! ApplicationUtilities.getInstance().hasPermission() );
        itemAnalisys.setDisable( ! ApplicationUtilities.getInstance().hasPermission() );
        
        homePane.setMenuItem( itemHome );
        reportPane.setMenuItem( itemReport );
        entriePane.setMenuItem( itemEntries );

        setStyle( "-fx-border-color: " + ApplicationUtilities.getColor() + " -fx-border-width: 0 2 0 0; -fx-padding: 10 4 4 4" );
        setSpacing( 10 );
        getChildren().addAll( itemHome, itemPostings, itemReport, itemAnalisys, itemEntries );
    }
    
    private void initListeners()
    {
        itemPostings.setOnAction( new EventHandler<ActionEvent>()
        {
            @Override
            public void handle( ActionEvent t ) 
            {
//                refreshContent( postingPane );
            }
        });
        
        itemEntries.setOnAction( new EventHandler<ActionEvent>()
        {
            @Override
            public void handle( ActionEvent t ) 
            {
                refreshContent( entriePane );
            }
        });
        
        itemReport.setOnAction( new EventHandler<ActionEvent>()
        {
            @Override
            public void handle( ActionEvent t ) 
            {
                refreshContent( reportPane );
            }
        });
        
       itemHome.setOnAction( new EventHandler<ActionEvent>()
        {
            @Override
            public void handle( ActionEvent t ) 
            {
                refreshContent( homePane );
            }
        });
        
       itemAnalisys.setOnAction( new EventHandler<ActionEvent>()
        {
            @Override
            public void handle( ActionEvent t ) 
            {
//                refreshContent( analisysPane );
            }
        });
       
        refreshContent( selectedPane );
    }
    
    private MenuItem itemHome     = new MenuItem( "Home",          "home.png" );
    private MenuItem itemPostings = new MenuItem( "Lançamentos",   "posting.png" );
    private MenuItem itemReport   = new MenuItem( "Relatórios",    "report.png" );
    private MenuItem itemAnalisys = new MenuItem( "Análises",      "helpFin .png" );
    private MenuItem itemEntries  = new MenuItem( "Cadastros",     "entries.png" );
    
    private EventHandler handler;
    
    private HomePane          homePane          = new HomePane();
    private EntriePane        entriePane        = new EntriePane();
    private ReportPane        reportPane        = new ReportPane();
    
    private AbstractModulesPane selectedPane = entriePane;
}
