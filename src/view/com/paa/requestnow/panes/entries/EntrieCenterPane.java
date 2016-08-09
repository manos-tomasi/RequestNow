package com.paa.requestnow.panes.entries;

import com.paa.requestnow.model.ApplicationUtilities;
import com.paa.requestnow.model.data.Core;
import com.paa.requestnow.model.data.User;
import com.paa.requestnow.panes.LegendPane;
import com.paa.requestnow.panes.modules.AbstractModulesPane;
import com.paa.requestnow.view.util.ActionButton;
import com.paa.requestnow.view.util.Prompts;
import java.util.Arrays;
import java.util.List;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import static com.paa.requestnow.panes.modules.EntriePane.*;

/**
 * @author artur
 * @param <T>
 */
public class EntrieCenterPane <T extends Core>
    extends 
        AbstractModulesPane
{
    public EntrieCenterPane() 
    {
        initComponents();
    }
    
    
    public void setSelectedEntrie( int index )
    {
        switch( index )
        {
            case VIEW_USERS:
            {
                controller = userController;
            }      
          
            break;
            
            case VIEW_SECTORS:
            {
                controller = sectorController;
            }
            break;
            
            case VIEW_FILDS:
            {
                controller =  fieldController;
            }
            break;
            
            case VIEW_TYPES:
            {
                controller =  typeController;
            }
            break;
            
            case VIEW_CATEGORIES:
            {
                controller =  categoryController;
            }
            break;
            
            case VIEW_ROUTE:
            {
                controller =  routeController;
            }
            break;
        }
    }
    
    
    @Override
    public List<Button> getActions() 
    {
        filterItem.setDisable( ! ApplicationUtilities.getInstance().hasPermission() );
        
        return Arrays.asList( addItem, editItem, deleteItem, filterItem );
    }

    
    
    @Override
    public void refreshContent() 
    {
        try
        {
            controller.refresh();
        }
        
        catch( Exception e )
        {
            ApplicationUtilities.logException( e );
        }
    }

    
    
    @Override
    public void resizeComponents( double height, double width )
    {
        getChildren().clear();
        getChildren().addAll( controller.getTable(), legendPane );
        
        legendPane.setPrefWidth( width );
        legendPane.setLayoutY( height - legendPane.getHeight() );
        
        controller.getTable().setPrefSize( width, height - legendPane.getHeight() );
        
        requestLayout();
    }
    
    
    
    private void deleteItem()
    {
        try
        {
            Core item = controller.getSelectedItem();

            if( item != null && item.getState() == Core.STATE_INACTIVE )
            {
                Prompts.info( controller.getEntrieName() + " já encontra-se excluido!" );
            }
           
            else if( item == null )
            {
                Prompts.alert( "Selecione um " + controller.getEntrieName() + " para excluir" );
            }
            
            else
            {
                if( Prompts.confirm( "Você tem certeza que deseja excluir o " + controller.getEntrieName() + "\n" + item ) )
                    controller.deleteItem( item );
            }

        }
        
        catch ( Exception e )
        {
            ApplicationUtilities.logException( e );
        }
    }
    
    
    
    private void editItem()
    {
        try
        {
            Core item = controller.getSelectedItem();

            if( item != null )
            {
                controller.editItem( item );
            }

            else
            {
                Prompts.alert( "Selecione um " + controller.getEntrieName() + " para editar" );
            }
        }
        
        catch ( Exception e )
        {
            ApplicationUtilities.logException( e );
        }
    }
    
    
    
    private void addItem()
    {
        try
        {
            controller.addItem();
        }
        
        catch ( Exception e )
        {
            ApplicationUtilities.logException( e );
        }
    }
    
    
    
    private void filterItem()
    {
        try
        {
            controller.filterItem();
        }
        
        catch( Exception e )
        {
            ApplicationUtilities.logException( e );
        }
    }
    
    
    
    private void initComponents()
    {
        legendPane.addItems( new LegendPane.LegendItem( User.STATES[ User.STATE_ACTIVE ],   "finish.png" ),
                             new LegendPane.LegendItem( User.STATES[ User.STATE_INACTIVE ], "delete.png" ) );
        
        getChildren().addAll( controller.getTable(), legendPane );
    }
    
    
    
    private ActionButton addItem = new ActionButton( "Novo", "new.png", new EventHandler<Event>() 
    {
        @Override
        public void handle( Event t ) 
        {
            addItem();
        }
    } );
    
    private ActionButton editItem = new ActionButton( "Editar", "edit.png", new EventHandler<Event>() 
    {
        @Override
        public void handle( Event t ) 
        {
            editItem();
        }
    } );
    
    private ActionButton deleteItem = new ActionButton( "Excluir", "delete.png", new EventHandler<Event>() 
    {
        @Override
        public void handle( Event t ) 
        {
            deleteItem();
        }
    } );
    
    private ActionButton filterItem = new ActionButton( "Filtro", "search.png", new EventHandler<Event>() 
    {
        @Override
        public void handle( Event t ) 
        {
            filterItem();
        }
    } );
    
    private LegendPane legendPane =  new LegendPane();
    
    private UserController   userController       = new UserController();
    private SectorController sectorController     = new SectorController();
    private CategoryController categoryController = new CategoryController();
    private TypeController typeController         = new TypeController();
    private FieldController fieldController       = new FieldController();
    private TypeRouteController routeController   = new TypeRouteController();
    
    private EntrieController controller = userController;
}
