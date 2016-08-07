package com.paa.requestnow.panes.entries;

import com.paa.requestnow.model.ApplicationUtilities;
import com.paa.requestnow.model.data.Core;
import com.paa.requestnow.model.filter.DefaultFilter;
import com.paa.requestnow.model.data.Option;
import com.paa.requestnow.model.data.User;
import com.paa.requestnow.model.filter.UserFilter;
import com.paa.requestnow.view.editor.FilterEditor;
import com.paa.requestnow.view.editor.UserEditor;
import com.paa.requestnow.view.tables.DefaultTable;
import com.paa.requestnow.view.tables.UserTable;
import com.paa.requestnow.view.util.EditorCallback;
import com.paa.requestnow.view.util.Prompts;
import javafx.event.Event;

/**
 *
 * @author artur
 */
public class UserController 
    extends 
        EntrieController<User>
{
    private UserFilter filter = new UserFilter();

    public UserController() 
    {
        filter.addCondition( UserFilter.STATE, new Option( Core.STATE_ACTIVE, Core.STATES[ Core.STATE_ACTIVE ] ) );
    }


    @Override
    public void filterItem() throws Exception 
    {
        new FilterEditor( new EditorCallback<DefaultFilter>( filter )
        {
            @Override
            public void handle( Event t ) 
            {
                filter = (UserFilter) source;
                
                refresh();
            }
        } ).open();
    }
    
    
    
    @Override
    public void addItem() throws Exception 
    {
        new UserEditor( new EditorCallback<User>( new User() ) 
        {
            @Override
            public void handle( Event t ) 
            {
                try
                {
                    com.paa.requestnow.model.ModuleContext.getInstance()
                                                        .getUserManager()
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
    public void editItem( User item ) throws Exception 
    {
        new UserEditor( new EditorCallback<User>( item )
        {
            @Override
            public void handle( Event t )
            {
                try
                {
                    com.paa.requestnow.model.ModuleContext.getInstance()
                                                        .getUserManager()
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
    public void deleteItem( User item ) throws Exception 
    {
        if( item.equals( ApplicationUtilities.getInstance().getActiveUser() ) )
        {
            Prompts.info( "Você não pode excluir um usuário logado no sistema !" );
            
            return;
        }
        
        com.paa.requestnow.model.ModuleContext.getInstance()
                        .getUserManager()
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
                                                    .getUserManager()
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
        return "usuário";
    }

    
    
    @Override
    public DefaultTable getTable()
    {
        return table;
    }
    
    
    private UserTable table = new UserTable();
}
