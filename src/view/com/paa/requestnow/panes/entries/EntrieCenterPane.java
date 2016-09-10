package com.paa.requestnow.panes.entries;

import com.paa.requestnow.model.ApplicationUtilities;
import com.paa.requestnow.model.data.Core;
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
            
            case VIEW_PERMISSION:
            {
                controller =  permissionController;
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
        getChildren().addAll( controller.getComponent() );
        
        controller.getComponent().setPrefSize( width, height );
        
        requestLayout();
    }
    
    private void initComponents()
    {
        getChildren().addAll( controller.getComponent() );
    }
    
    private UserController   userController       = new UserController();
    private SectorController sectorController     = new SectorController();
    private CategoryController categoryController = new CategoryController();
    private TypeController typeController         = new TypeController();
    private FieldController fieldController       = new FieldController();
    private TypeRouteController routeController       = new TypeRouteController();
    private PermissionController permissionController = new PermissionController();
    
    private EntrieController controller = userController;
}
