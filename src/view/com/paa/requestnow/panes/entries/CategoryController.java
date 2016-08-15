package com.paa.requestnow.panes.entries;

import com.paa.requestnow.model.ApplicationUtilities;
import com.paa.requestnow.model.data.Category;
import com.paa.requestnow.model.filter.CategoryFilter;
import com.paa.requestnow.model.filter.DefaultFilter;
import com.paa.requestnow.view.editor.CategoryEditor;
import com.paa.requestnow.view.editor.FilterEditor;
import com.paa.requestnow.view.tables.CategoryTable;
import com.paa.requestnow.view.util.ActionButton;
import com.paa.requestnow.view.util.ActionButton.ActionHandler;
import com.paa.requestnow.view.util.EditorCallback;
import com.paa.requestnow.view.util.Prompts;
import java.util.Arrays;
import java.util.List;
import javafx.event.Event;
import javafx.scene.control.Control;

/**
 * @author artur
 */
public class CategoryController
    implements 
        EntrieController<Category>
{
    private CategoryFilter filter =  new CategoryFilter();
    
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

    public void editItem() throws Exception 
    {
        Category item = table.getSelectedItem();

        if( item != null )
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
        
        else
        {
            Prompts.alert( "Selecione uma categoria para editar" );
        }
    }

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

    public void deleteItem() throws Exception 
    {
        Category item = table.getSelectedItem();

        if( item != null && item.getState() == Category.STATE_INACTIVE )
        {
            Prompts.info( "Categoria já encontra-se excluido!" );
        }

        else if( item == null )
        {
            Prompts.alert( "Selecione uma categoria para excluir!" );
        }

        else
        {
            if( Prompts.confirm( "Você tem certeza que deseja excluir a categoria\n" + item ) )
            {
                com.paa.requestnow.model.ModuleContext.getInstance()
                                          .getCategoryManager()
                                          .delete( item );

                refresh();
            }
        }
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
    public Control getComponent() 
    {
        return  table;
    }

    
    @Override
    public List<ActionButton> getActions() 
    {
        return Arrays.asList( addItem, editItem, deleteItem, filterItem );
    }
    
    private CategoryTable table = new CategoryTable();
    
    private ActionButton addItem = new ActionButton( "Novo", "new.png", new ActionHandler()
    {
        @Override
        public void onEvent( Event t ) throws Exception 
        {
            addItem();
        }
    } );
    
    private ActionButton editItem = new ActionButton( "Editar", "edit.png", new ActionHandler() 
    {
        @Override
        public void onEvent( Event t ) throws Exception 
        {
            editItem();
        }
    } );
    
    private ActionButton deleteItem = new ActionButton( "Excluir", "delete.png", new ActionHandler() 
    {
        @Override
        public void onEvent( Event t ) throws Exception
        {
            deleteItem();
        }
    } );
    
    private ActionButton filterItem = new ActionButton( "Filtro", "search.png", new ActionHandler() 
    {
        @Override
        public void onEvent( Event t ) throws Exception
        {
            filterItem();
        }
    } );
}
