package com.paa.requestnow.panes;

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
        itemOrders.setDisable( ! ApplicationUtilities.getInstance().hasPermission() );
        
        reportPane.setMenuItem( itemReport );
        entriePane.setMenuItem( itemEntries );
        requestPane.setMenuItem( itemRequests );
        
        setStyle( "-fx-border-color: " + ApplicationUtilities.getColor() + " -fx-border-width: 0 2 0 0; -fx-padding: 10 4 4 4" );
        setSpacing( 10 );
        getChildren().addAll( itemRequests, itemOrders, itemReport, itemEntries );
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
        
       itemOrders.setOnAction( new EventHandler<ActionEvent>()
        {
            @Override
            public void handle( ActionEvent t ) 
            {
//                refreshContent( analisysPane );
            }
        });
       
       
        refreshContent( selectedPane );
    }
    
    private MenuItem itemRequests = new MenuItem( "Requisições",   "posting.png" );
    private MenuItem itemOrders   = new MenuItem( "Despachos",     "orders.png" );
    private MenuItem itemReport   = new MenuItem( "Relatórios",    "report.png" );
    private MenuItem itemEntries  = new MenuItem( "Cadastros",     "entries.png" );
    
    private EventHandler handler;
    
    private EntriePane        entriePane        = new EntriePane();
    private RequestPane       requestPane       = new RequestPane();
    private ReportPane        reportPane        = new ReportPane();
    
    
    private AbstractModulesPane selectedPane = entriePane;
}
