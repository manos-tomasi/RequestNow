package com.paa.requestnow.panes.entries;

import com.paa.requestnow.model.ApplicationUtilities;
import com.paa.requestnow.model.data.Category;
import com.paa.requestnow.model.filter.CategoryFilter;
import com.paa.requestnow.model.filter.DefaultFilter;
import com.paa.requestnow.view.editor.CategoryEditor;
import com.paa.requestnow.view.editor.FilterEditor;
import com.paa.requestnow.view.tables.CategoryTable;
import com.paa.requestnow.view.tables.DefaultTable;
import com.paa.requestnow.view.util.EditorCallback;
import javafx.event.Event;

/**
 * @author artur
 */
public class CategoryController
    extends 
        EntrieController<Category>
{
    private CategoryFilter filter =  new CategoryFilter();
    
    @Override
    public void addItem() throws Exception 
    {
        new CategoryEditor( new EditorCallback<Category>( new Category() )
        {
            @Override
            public void handle( Event t ) 
            {
                try
                {
                    com.paa.requestnow.model.ModuleContext.getInstance()
                                                        .getCategoryManager()
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
    public void editItem( Category item ) throws Exception 
    {
        new CategoryEditor( new EditorCallback<Category>( item )
        {
            @Override
            public void handle( Event t ) 
            {
                try
                {
                    com.paa.requestnow.model.ModuleContext.getInstance()
                                                        .getCategoryManager()
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
                filter = (CategoryFilter) source;
                
                refresh();
            }
        } ).open();
    }

    @Override
    public void deleteItem( Category item ) throws Exception 
    {
        com.paa.requestnow.model.ModuleContext.getInstance()
                                              .getCategoryManager()
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
                                                    .getCategoryManager()
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
        return "categorias";
    }

    @Override
    public DefaultTable<Category> getTable() 
    {
        return  table;
    }
    
    private CategoryTable table = new CategoryTable();
}
