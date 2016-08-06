package com.paa.requestnow.panes.entries;

import com.paa.requestnow.model.ApplicationUtilities;
import com.paa.requestnow.model.data.DefaultFilter;
import com.paa.requestnow.model.data.Sector;
import com.paa.requestnow.model.data.SectorFilter;
import com.paa.requestnow.view.editor.FilterEditor;
import com.paa.requestnow.view.editor.SectorEditor;
import com.paa.requestnow.view.tables.DefaultTable;
import com.paa.requestnow.view.tables.SectorTable;
import com.paa.requestnow.view.util.EditorCallback;
import com.paa.requestnow.view.util.Prompts;
import javafx.event.Event;

/**
 *
 * @author lucas
 */
public class SectorController 
        extends 
            EntrieController<Sector>
{

    SectorFilter filter =  new SectorFilter();
    
    @Override
    public void addItem() throws Exception {
                new SectorEditor( new EditorCallback<Sector>( new Sector() ) 
        {
            @Override
            public void handle( Event t ) 
            {
                try
                {
                    com.paa.requestnow.model.ModuleContext.getInstance()
                                                        .getSectorManager()
                                                        .addValue( source );
                    
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
                                                        .updateValue( source );
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
                                              .deleteValue( item );

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
                                                    .getValues( filter ) );
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
