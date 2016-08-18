package com.paa.requestnow.panes.entries;

import com.paa.requestnow.model.ApplicationUtilities;
import com.paa.requestnow.model.data.Core;
import com.paa.requestnow.model.data.User;
import com.paa.requestnow.panes.LegendPane;
import com.paa.requestnow.panes.modules.AbstractModulesPane;
import java.util.List;
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
       return controller.getActions();
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
        getChildren().addAll( controller.getComponent(), legendPane );
        
        legendPane.setPrefWidth( width );
        legendPane.setLayoutY( height - legendPane.getHeight() );
        
        controller.getComponent().setPrefSize( width, height - legendPane.getHeight() );
        
        requestLayout();
    }
    
    private void initComponents()
    {
        legendPane.addItems( new LegendPane.LegendItem( User.STATES[ User.STATE_ACTIVE ],   "finish.png" ),
                             new LegendPane.LegendItem( User.STATES[ User.STATE_INACTIVE ], "delete.png" ) );
        
        getChildren().addAll( controller.getComponent(), legendPane );
    }
    
    
    private LegendPane legendPane =  new LegendPane();
    
    private UserController   userController       = new UserController();
    private SectorController sectorController     = new SectorController();
    private CategoryController categoryController = new CategoryController();
    private TypeController typeController         = new TypeController();
    private FieldController fieldController       = new FieldController();
    private TypeRouteController routeController   = new TypeRouteController();
    
    private EntrieController controller = userController;
}
