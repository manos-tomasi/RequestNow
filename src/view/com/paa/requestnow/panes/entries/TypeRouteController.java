package com.paa.requestnow.panes.entries;

import com.paa.requestnow.model.ApplicationUtilities;
import com.paa.requestnow.model.data.TypeRoute;
import com.paa.requestnow.model.filter.DefaultFilter;
import com.paa.requestnow.model.filter.TypeRouteFilter;
import com.paa.requestnow.view.editor.FilterEditor;
import com.paa.requestnow.view.editor.TypeRouteEditor;
import com.paa.requestnow.view.tables.DefaultTable;
import com.paa.requestnow.view.tables.TypeRouteTable;
import com.paa.requestnow.view.util.EditorCallback;
import javafx.event.Event;

/**
 * @author artur
 */
public class TypeRouteController 
    extends 
        EntrieController<TypeRoute>
{
    private TypeRouteFilter filter =  new TypeRouteFilter();
    
    @Override
    public void addItem() throws Exception 
    {
        new TypeRouteEditor( new EditorCallback<TypeRoute>( new TypeRoute() )
        {
            @Override
            public void handle( Event t ) 
            {
                try
                {
                    com.paa.requestnow.model.ModuleContext.getInstance()
                                                        .getTypeRouteManager()
                                                        .add( source );
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
    public void editItem( TypeRoute item ) throws Exception 
    {
        new TypeRouteEditor( new EditorCallback<TypeRoute>( item )
        {
            @Override
            public void handle( Event t ) 
            {
                try
                {
                    com.paa.requestnow.model.ModuleContext.getInstance()
                                                        .getTypeRouteManager()
                                                        .update( source );
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
                filter = (TypeRouteFilter) source;
                
                refresh();
            }
        } ).open();
    }

    @Override
    public void deleteItem( TypeRoute item ) throws Exception 
    {
        com.paa.requestnow.model.ModuleContext.getInstance()
                                              .getTypeRouteManager()
                                              .delete( item );

        refresh();
        
    }

    @Override
    public void refresh() 
    {
        try
        {
            table.setItems(com.paa.requestnow.model.ModuleContext
                                                    .getInstance()
                                                    .getTypeRouteManager()
                                                    .get( filter ) );
        }
        
        catch( Exception e )
        {
            ApplicationUtilities.logException( e );
        }
    }

    @Override
    public String getEntrieName() 
    {
        return "tipos de rotas";
    }

    @Override
    public DefaultTable<TypeRoute> getTable() 
    {
        return  table;
    }
    
    private TypeRouteTable table = new TypeRouteTable();
}
