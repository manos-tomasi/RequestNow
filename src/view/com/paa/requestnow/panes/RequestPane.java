package com.paa.requestnow.panes;

import com.paa.requestnow.control.RequestController;
import com.paa.requestnow.model.ApplicationUtilities;
import com.paa.requestnow.model.data.Request;
import com.paa.requestnow.model.filter.DefaultFilter;
import com.paa.requestnow.model.filter.RequestFilter;
import com.paa.requestnow.panes.modules.AbstractModulesPane;
import com.paa.requestnow.view.editor.FilterEditor;
import com.paa.requestnow.view.editor.RequestEditor;
import com.paa.requestnow.view.tables.RequestTable;
import com.paa.requestnow.view.util.ActionButton;
import com.paa.requestnow.view.util.EditorCallback;
import com.paa.requestnow.view.util.Prompts;
import com.paa.requestnow.view.util.RequestLegendPane;
import java.util.ArrayList;
import java.util.List;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

/**
 *
 * @author lucas
 */
public class RequestPane 
        extends 
            AbstractModulesPane
{

    private RequestFilter requestFilter;
    private RequestController controller;
    
    public RequestPane()
    {
        initComponents();
        load();
    }
    
    
    private void load()
    {
        controller = RequestController.getInstance();
        requestFilter = new RequestFilter();
    }
    
    private void add()
    {
        new RequestEditor( new EditorCallback<Request>( new Request() ) 
        {
            @Override
            public void handle(Event t) 
            {
                try 
                {
                    controller.save( properties, source );
                    refresh();
                    
                    Prompts.info( "Requisição criada com sucesso!" );
                } 
                catch (Exception e) 
                {
                    ApplicationUtilities.logException( e );
                }
            }
        }).open();
    }
    
    private void cancel()
    {
        if( isSelected() )
        {
            if( Prompts.confirm( "Confirma o cancelamento da requisição" ) )
            {
                try 
                {
                    controller.cancel( table.getSelectedItem() );
                } 
                catch (Exception e) 
                {
                    ApplicationUtilities.logException(e);
                }
            }
        }
        else
        {
            Prompts.alert( "Selecione a requisição que deseja cancelar!" );
        }
    }
    
    private void inspect()
    {
        if( isSelected() )
        {
            
        }
        else
        {
            Prompts.alert( "Selecione a requisição que deseja visualisar!" );
        }
    }
    
    private void filter()
    {
        new FilterEditor( new EditorCallback<DefaultFilter>( requestFilter ) 
        {
            @Override
            public void handle(Event t) 
            {
                requestFilter = (RequestFilter) source;
            }
        }).open();
    }
    
    private boolean isSelected()
    {
        return table.getSelectedItem() != null;
    }
    
    public void refresh() 
    {
        try
        {
            table.setItems(com.paa.requestnow.model.ModuleContext
                                                    .getInstance()
                                                    .getRequestManager()
                                                    .get( requestFilter ) );
        }
        
        catch( Exception e )
        {
            ApplicationUtilities.logException( e );
        }
    }
    
    @Override
    public List<Button> getActions() 
    {
        List<Button> actions = new ArrayList();

        actions.add(add);
        actions.add(cancel);
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
                            .getRequestManager()
                            .get(requestFilter));
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
        legend = new RequestLegendPane();
        table  = new RequestTable();
        
        getChildren().addAll( table, legend );
    }
    
    private RequestLegendPane legend;
    private RequestTable      table;
    
    private ActionButton add = new ActionButton( "Novo", "new.png", new EventHandler<Event>() 
    {
        @Override
        public void handle( Event t ) 
        {
            add();
        }
    });
    
    private ActionButton inspect = new ActionButton( "Visualisar", "details.png", new EventHandler<Event>() 
    {
        @Override
        public void handle( Event t ) 
        {
            inspect();
        }
    });
    
    private ActionButton cancel = new ActionButton( "Cancelar", "delete.png", new EventHandler<Event>() 
    {
        @Override
        public void handle( Event t ) 
        {
            cancel();
        }
    });
    
    private ActionButton filter = new ActionButton( "Filtro", "search.png", new EventHandler<Event>() 
    {
        @Override
        public void handle( Event t ) 
        {
            filter();
        }
    });
}
