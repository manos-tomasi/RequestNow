package com.paa.requestnow.panes;

import com.paa.requestnow.control.RequestController;
import com.paa.requestnow.control.RequestRouteController;
import com.paa.requestnow.model.ApplicationUtilities;
import com.paa.requestnow.panes.modules.AbstractModulesPane;
import com.paa.requestnow.panes.modules.ReportPane;
import com.paa.requestnow.panes.modules.EntriePane;
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
    RequestController requestController           = RequestController.getInstance();
    RequestRouteController requestRouteController = RequestRouteController.getInstance();
    
    public MenuPane( EventHandler handler ) 
    {
        this.handler = handler;
        
        initComponents();
        initListeners();
        composePermission();
    }
    
    private void composePermission()
    {
        itemRequests.setDisable( requestController.hasPermissionView() );
        itemRequestRoute.setDisable( requestRouteController.hasPermissionView() );        
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
        reportPane.setMenuItem( itemReport );
        entriePane.setMenuItem( itemEntries );
        requestPane.setMenuItem( itemRequests );
        requestRoutePane.setMenuItem( itemRequestRoute );
        
        setStyle( "-fx-border-color: " + ApplicationUtilities.getColor() + " -fx-border-width: 0 2 0 0; -fx-padding: 10 4 4 4" );
        setSpacing( 10 );
        getChildren().addAll( itemRequests, itemRequestRoute, itemReport, itemEntries );
    }
    
    private void initListeners()
    {
        itemRequests.setOnAction( new EventHandler<ActionEvent>()
        {
            @Override
            public void handle( ActionEvent t ) 
            {
                refreshContent( requestPane );
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
        
        itemRequestRoute.setOnAction( new EventHandler<ActionEvent>()
        {
            @Override
            public void handle( ActionEvent t ) 
            {
                refreshContent( requestRoutePane );
            }
        });
       
       
        refreshContent( selectedPane );
    }
    
    private MenuItem itemRequests     = new MenuItem( "Requisições",   "posting.png" );
    private MenuItem itemRequestRoute = new MenuItem( "Despachos",     "orders.png" );
    private MenuItem itemReport       = new MenuItem( "Relatórios",    "report.png" );
    private MenuItem itemEntries      = new MenuItem( "Cadastros",     "entries.png" );
    
    private EventHandler handler;
    
    private EntriePane        entriePane        = new EntriePane();
    private RequestPane       requestPane       = new RequestPane();
    private RequestRoutePane  requestRoutePane  = new RequestRoutePane();
    private ReportPane        reportPane        = new ReportPane();
    
    
    private AbstractModulesPane selectedPane = entriePane;
}
