package com.paa.requestnow.panes.entries;

import com.paa.requestnow.model.ApplicationUtilities;
import com.paa.requestnow.model.data.Request;
import com.paa.requestnow.model.filter.DefaultFilter;
import com.paa.requestnow.model.filter.RequestFilter;
import com.paa.requestnow.view.editor.FilterEditor;
import com.paa.requestnow.view.editor.RequestEditor;
import com.paa.requestnow.view.tables.DefaultTable;
import com.paa.requestnow.view.tables.RequestTable;
import com.paa.requestnow.view.util.EditorCallback;
import javafx.event.Event;

/**
 *
 * @author lucas
 */
public class RequestController 
        extends 
            EntrieController<Request>
{    
    private static RequestController controller;
    private RequestFilter     filter = new RequestFilter();
    
    public static RequestController getInstance()
    {
        if( controller == null )
        {
            controller = new RequestController();
        }
        return controller;
    }
    
    @Override
    public void addItem() throws Exception 
    {
        new RequestEditor( new EditorCallback<Request>( new Request() ) 
        {
            @Override
            public void handle( Event t ) 
            {
                try
                {
                    com.paa.requestnow.model.ModuleContext.getInstance()
                                                        .getRequestManager()
                                                        .add( source );
                    
                    table.setSelectedItem( source );
                    
                    refresh();
                }
                
                catch( Exception e )
                {
                    ApplicationUtilities.logException( e );
                }
            }
        } ).open();
    }

    @Override
    public void editItem(Request item) throws Exception 
    {
        new RequestEditor( new EditorCallback<Request>( new Request() ) 
        {
            @Override
            public void handle( Event t ) 
            {
                try
                {
                    com.paa.requestnow.model.ModuleContext.getInstance()
                                                        .getRequestManager()
                                                        .update( source );
                    
                    table.setSelectedItem( source );
                    
                    refresh();
                }
                
                catch( Exception e )
                {
                    ApplicationUtilities.logException( e );
                }
            }
        } ).open();    
    }

    @Override
    public void filterItem() throws Exception 
    {
        new FilterEditor( new EditorCallback<DefaultFilter>( filter )
        {
            @Override
            public void handle( Event t ) 
            {
                filter = ( RequestFilter ) source;
                
                refresh();
            }
        } ).open();
    }

    @Override
    public void deleteItem(Request item) throws Exception 
    {
        com.paa.requestnow.model.ModuleContext.getInstance()
                                              .getRequestManager()
                                              .delete( item );

        refresh();
    }

    @Override
    public void refresh() 
    {
        try 
        {
            table.setItems( com.paa.requestnow.model.ModuleContext
                               .getInstance().getRequestManager()
                               .get(filter) );
        }
        catch ( Exception e ) 
        {
            ApplicationUtilities.logException( e );
        }
    }

    @Override
    public String getEntrieName() 
    {
        return "requisições";
    }

    @Override
    public DefaultTable<Request> getTable() 
    {
        return table;
    }
    
    private RequestTable table = new RequestTable();
}
