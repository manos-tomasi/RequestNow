package com.paa.requestnow.panes.entries;

import com.paa.requestnow.model.ApplicationUtilities;
import com.paa.requestnow.model.filter.DefaultFilter;
import com.paa.requestnow.model.data.Sector;
import com.paa.requestnow.model.filter.SectorFilter;
import com.paa.requestnow.view.editor.FilterEditor;
import com.paa.requestnow.view.editor.SectorEditor;
import com.paa.requestnow.view.tables.DefaultTable;
import com.paa.requestnow.view.tables.SectorTable;
import com.paa.requestnow.view.util.EditorCallback;
import javafx.event.Event;

/**
 *
 * @author lucas
 */
public class SectorController 
        extends 
            EntrieController<Sector>
{

    private SectorFilter filter =  new SectorFilter();
    
    @Override
    public void addItem() throws Exception 
    {
        new SectorEditor( new EditorCallback<Sector>( new Sector() ) 
        {
            @Override
            public void handle( Event t ) 
            {
                try
                {
                    com.paa.requestnow.model.ModuleContext.getInstance()
                                                        .getSectorManager()
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
    public void editItem(Sector item) throws Exception 
    {
         new SectorEditor( new EditorCallback<Sector>( item )
        {
            @Override
            public void handle( Event t )
            {
                try
                {
                    com.paa.requestnow.model.ModuleContext.getInstance()
                                                        .getSectorManager()
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
                filter = (SectorFilter) source;
                
                refresh();
            }
        } ).open();
    }

    @Override
    public void deleteItem(Sector item) throws Exception 
    {   
        com.paa.requestnow.model.ModuleContext.getInstance()
                                              .getSectorManager()
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
                                                    .getSectorManager()
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
        return "setores";
    }

    @Override
    public DefaultTable<Sector> getTable() 
    {
        return table;
    }
    
    private SectorTable table = new SectorTable();
}
