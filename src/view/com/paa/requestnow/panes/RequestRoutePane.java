package com.paa.requestnow.panes;

import com.paa.requestnow.control.RequestRouteController;
import com.paa.requestnow.model.ApplicationUtilities;
import com.paa.requestnow.model.data.Option;
import com.paa.requestnow.model.data.RequestRoute;
import com.paa.requestnow.model.filter.DefaultFilter;
import com.paa.requestnow.model.filter.RequestRouteFilter;
import com.paa.requestnow.panes.modules.AbstractModulesPane;
import com.paa.requestnow.view.editor.DispatchEditor;
import com.paa.requestnow.view.editor.FilterEditor;
import com.paa.requestnow.view.tables.RequestRouteTable;
import com.paa.requestnow.view.util.ActionButton;
import com.paa.requestnow.view.util.EditorCallback;
import com.paa.requestnow.view.util.Prompts;
import com.paa.requestnow.view.util.RequestRouteLegendPane;
import java.util.ArrayList;
import java.util.List;
import javafx.event.Event;
import javafx.scene.control.Button;

/**
 *
 * @author lucas
 */
public class RequestRoutePane 
        extends 
            AbstractModulesPane
{
    private RequestRouteFilter     requestRouteFilter;
    private RequestRouteController controller;

    public RequestRoutePane() 
    {
        initComponents();
        
        load();
    }
    
    private void load()
    {
        controller = RequestRouteController.getInstance();
        requestRouteFilter = new RequestRouteFilter();
        
        requestRouteFilter.addCondition( RequestRouteFilter.MYRESPONSE , Option.YES );
        requestRouteFilter.setBlockedFilters( RequestRouteFilter.MYRESPONSE );
        requestRouteFilter.addCondition( RequestRouteFilter.STATE, new Option( RequestRoute.STOPED, "Em Andamento") );
                
        refreshContent();
    }

    private void onFilter()
    {
        new FilterEditor( new EditorCallback<DefaultFilter>( requestRouteFilter ) 
        {
            @Override
            public void handle(Event t) 
            {
                requestRouteFilter = (RequestRouteFilter) source;
                refreshContent();
            }
        }).open();    
    }
    
    private void onDispatch()
    {
        if( isSelected() )
        {
            RequestRoute route = table.getSelectedItem();
            if( route.getState() == RequestRoute.STOPED )
            {
                DispatchEditor.edit( route );
                refreshContent();
            }
            else
            {
                Prompts.alert( "Essa requisição está " + RequestRoute.STATES[ table.getSelectedItem().getState() ] + "!" );
            }
        }
        else
        {
            Prompts.alert( "Selecione a rota que deseja despachar!" );
        }
    }
    
    private void onInspect()
    {
        
    }
    
    private boolean isSelected()
    {
        return table.getSelectedItem() != null;
    }
    
    @Override
    public List<Button> getActions() 
    {
        List<Button> actions = new ArrayList();

        actions.add(dispatch);
        actions.add(inspect);
        actions.add(filter);
     
        return actions;
    }

    @Override
    public void refreshContent() 
    {
        try
        {
            table.setItems( com.paa.requestnow.model.ModuleContext.getInstance()
                            .getRequestRouteManager()
                            .get(requestRouteFilter));
            requestLayout();
            requestFocus();
        }
        catch( Exception e )
        {
            ApplicationUtilities.logException(e);
        }
    }

    @Override
    public void resizeComponents(double height, double width) 
    {
        legend.setPrefWidth( width );
        legend.setLayoutY( height - legend.getHeight() );
        
        table.setPrefSize( width, height - legend.getHeight() );
    }
    
    private void initComponents()
    {
        legend = new RequestRouteLegendPane();
        table  = new RequestRouteTable();
        
        getChildren().addAll( table, legend );
    }
    
    private RequestRouteLegendPane legend;
    private RequestRouteTable      table;
    
    
    private ActionButton dispatch = new ActionButton( "Despachar", "orders.png", new ActionButton.ActionHandler() 
    {
        @Override
        public void onEvent( Event t ) 
        {
            onDispatch();
        }
    });
    
    private ActionButton inspect = new ActionButton( "Visualisar", "details.png", new ActionButton.ActionHandler() 
    {
        @Override
        public void onEvent( Event t ) 
        {
            onInspect();
        }
    });
    
    
    private ActionButton filter = new ActionButton( "Filtro", "search.png", new ActionButton.ActionHandler() 
    {
        @Override
        public void onEvent( Event t ) 
        {
            onFilter();
        }
    });
}
