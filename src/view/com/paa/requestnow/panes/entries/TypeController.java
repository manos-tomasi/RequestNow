package com.paa.requestnow.panes.entries;

import com.paa.requestnow.model.ApplicationUtilities;
import com.paa.requestnow.model.data.Type;
import com.paa.requestnow.model.filter.DefaultFilter;
import com.paa.requestnow.model.filter.TypeFilter;
import com.paa.requestnow.view.editor.FilterEditor;
import com.paa.requestnow.view.editor.TypeEditor;
import com.paa.requestnow.view.tables.DefaultTable;
import com.paa.requestnow.view.tables.TypeTable;
import com.paa.requestnow.view.util.EditorCallback;
import javafx.event.Event;

/**
 * @author artur
 */
public class TypeController
    extends 
        EntrieController<Type>
{
    private TypeFilter filter =  new TypeFilter();
    
    @Override
    public void addItem() throws Exception 
    {
        new TypeEditor( new EditorCallback<Type>( new Type() )
        {
            @Override
            public void handle( Event t ) 
            {
                try
                {
                    com.paa.requestnow.model.ModuleContext.getInstance()
                                                        .getTypeManager()
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
    public void editItem( Type item ) throws Exception 
    {
        new TypeEditor( new EditorCallback<Type>( item )
        {
            @Override
            public void handle( Event t ) 
            {
                try
                {
                    com.paa.requestnow.model.ModuleContext.getInstance()
                                                        .getTypeManager()
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
                filter = (TypeFilter) source;
                
                refresh();
            }
        } ).open();
    }

    @Override
    public void deleteItem( Type item ) throws Exception 
    {
        com.paa.requestnow.model.ModuleContext.getInstance()
                                              .getTypeManager()
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
                                                    .getTypeManager()
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
        return "tipos de requisições";
    }

    @Override
    public DefaultTable<Type> getTable() 
    {
        return  table;
    }
    
    private TypeTable table = new TypeTable();
}
