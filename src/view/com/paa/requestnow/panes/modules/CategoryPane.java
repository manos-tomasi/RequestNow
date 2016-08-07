package com.paa.requestnow.panes.modules;

import com.paa.requestnow.model.data.Category;
import com.paa.requestnow.view.util.CategoryTree;
import java.util.Collections;
import java.util.List;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author artur
 */
public class CategoryPane 
        extends 
        AbstractModulesPane
{

    public CategoryPane() 
    {
        initComponents();
    }
    
    @Override
    public List<Button> getActions() 
    {
        return Collections.EMPTY_LIST;
    }

    @Override
    public void refreshContent() 
    {
        Object source = tree.getSelectedNode();
        
//        pane.getTabs().clear();
        
        {
//            Category category = (Category) source;
            
//            details.setSource( category );
            
//            postingPane.setPostings( category );
             
//            pane.getTabs().addAll( details, postingPane );
        }
    }

    @Override
    public void resizeComponents( double height, double width )
    {
        borderPane.setPrefSize( width, height );
        
        Platform.runLater( new Runnable() 
        {
            @Override
            public void run() 
            {
//                postingPane.resizeComponents( height - 40 , width - tree.getWidth() );
            }
        } );
    }
    
    private void initComponents()
    {
        borderPane.setLeft( tree );

        tree.addEventFilter( CategoryTree.Events.ON_SELECT, new EventHandler<Event>() 
        {
            @Override
            public void handle(Event t) 
            {
                refreshContent();
            }
        } );
        
        getChildren().add( borderPane );
    }
    
    private BorderPane borderPane = new BorderPane();
    private CategoryTree tree = new CategoryTree();
}