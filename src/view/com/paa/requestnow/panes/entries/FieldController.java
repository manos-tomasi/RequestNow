package com.paa.requestnow.panes.entries;

import com.paa.requestnow.model.ApplicationUtilities;
import com.paa.requestnow.model.data.Field;
import com.paa.requestnow.model.filter.DefaultFilter;
import com.paa.requestnow.model.filter.FieldFilter;
import com.paa.requestnow.view.editor.FieldEditor;
import com.paa.requestnow.view.editor.FilterEditor;
import com.paa.requestnow.view.tables.DefaultTable;
import com.paa.requestnow.view.tables.FieldTable;
import com.paa.requestnow.view.util.EditorCallback;
import javafx.event.Event;

/**
 * @author artur
 */
public class FieldController 
    extends 
        EntrieController<Field>
{
    private FieldFilter filter =  new FieldFilter();
    
    @Override
    public void addItem() throws Exception 
    {
        new FieldEditor( new EditorCallback<Field>( new Field() )
        {
            @Override
            public void handle( Event t ) 
            {
                try
                {
                    com.paa.requestnow.model.ModuleContext.getInstance()
                                                        .getFieldManager()
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
    public void editItem( Field item ) throws Exception 
    {
        new FieldEditor( new EditorCallback<Field>( item )
        {
            @Override
            public void handle( Event t ) 
            {
                try
                {
                    com.paa.requestnow.model.ModuleContext.getInstance()
                                                        .getFieldManager()
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
                filter = (FieldFilter) source;
                
                refresh();
            }
        } ).open();
    }

    @Override
    public void deleteItem( Field item ) throws Exception 
    {
        com.paa.requestnow.model.ModuleContext.getInstance()
                                              .getFieldManager()
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
                                                    .getFieldManager()
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
        return "campos dinâmicos";
    }

    @Override
    public DefaultTable<Field> getTable() 
    {
        return  table;
    }
    
    private FieldTable table = new FieldTable();
}
