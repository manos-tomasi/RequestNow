package com.paa.requestnow.panes.reports;

import com.paa.requestnow.model.ApplicationUtilities;
import com.paa.requestnow.model.data.Core;
import com.paa.requestnow.panes.LegendPane;
import com.paa.requestnow.panes.modules.AbstractModulesPane;
import com.paa.requestnow.panes.modules.ReportPane;
import com.paa.requestnow.view.util.ActionButton;
import com.paa.requestnow.view.util.FileUtilities;
import com.paa.requestnow.view.util.Prompts;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import javafx.concurrent.Task;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

/**
 * @author artur
 */
public class ReportCenterPane 
    extends 
        AbstractModulesPane
{
    public ReportCenterPane() 
    {
        initComponents();
    }
    
    
    public void setSelectedReport( int index )
    {
        switch( index )
        {
            case ReportPane.VIEW_USERS:
            {
                controller = userReportController;
            }
            break;
        }
        
        legendPane.getChildren().clear();
        
        if( index != ReportPane.VIEW_POSTING )
        {
            legendPane.addItems( new LegendPane.LegendItem( Core.STATES[ Core.STATE_ACTIVE ],   "finish.png" ),
                                 new LegendPane.LegendItem( Core.STATES[ Core.STATE_INACTIVE ], "delete.png" ) );
        }
            
    }
    
    
    @Override
    public List<Button> getActions() 
    {
        return Arrays.asList( configureItem, printItem );
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
    
    
    
    private void configure()
    {
        try
        {
            controller.configure();
        }
        
        catch ( Exception e )
        {
            ApplicationUtilities.logException( e );
        }
    }
    
    
    
    private void print()
    {
        try
        {
            File file = FileUtilities.saveFile( "Imprimir Relatório", "HelpFin(" + System.currentTimeMillis() +").pdf" );

            if( file != null )
            {
                Prompts.process( "Gerando Relatório " + file.getName() + "..." , new Task<Void>() 
                {
                    @Override
                    protected Void call() throws Exception 
                    {
                        try
                        {
                            controller.print( file );
                        }
                        
                        catch ( Exception e )
                        {
                            ApplicationUtilities.logException( e );
                        }
                        
                        return null;
                    }
                } );
            }
        }
        
        catch ( Exception e )
        {
            ApplicationUtilities.logException( e );
        }
    }
    
    
    
    private void initComponents()
    {
        legendPane.addItems( new LegendPane.LegendItem( Core.STATES[ Core.STATE_ACTIVE ],   "finish.png" ),
                             new LegendPane.LegendItem( Core.STATES[ Core.STATE_INACTIVE ], "delete.png" ) );
        
        getChildren().addAll( controller.getTable(), legendPane );
    }
    
    
    
    private ActionButton configureItem = new ActionButton( "Filtro", "search.png", new ActionButton.ActionHandler()
    {
        @Override
        public void onEvent( Event t ) 
        {
            configure();
        }
    } );
    
    private ActionButton printItem = new ActionButton( "Imprimir", "pdf.png", new ActionButton.ActionHandler()
    {
        @Override
        public void onEvent( Event t ) 
        {
            print();
        }
    } );
    
    private LegendPane legendPane =  new LegendPane();
    
    private UserReportController userReportController                     = new UserReportController();
    
    private ReportController controller = userReportController;
}
