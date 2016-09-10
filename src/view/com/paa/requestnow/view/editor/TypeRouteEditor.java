package com.paa.requestnow.view.editor;

import com.paa.requestnow.model.ApplicationUtilities;
import com.paa.requestnow.model.data.Sector;
import com.paa.requestnow.model.data.TypeRoute;
import com.paa.requestnow.model.data.User;
import com.paa.requestnow.view.selectors.SectorSelector;
import com.paa.requestnow.view.selectors.UserSelector;
import com.paa.requestnow.view.util.EditorCallback;
import com.paa.requestnow.view.util.LabelField;
import java.util.List;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.GridPane;

/**
 * @author artur
 */
public class TypeRouteEditor
    extends 
         AbstractEditor<TypeRoute>
{
    public TypeRouteEditor( EditorCallback<TypeRoute> callback ) 
    {
        super(callback);
        
        initComponents();
        
        setTitle( "Editor de Rotas" );
        
        setHeaderText( "Editor de Tipos de Rotas" );

        setSource(source);
    } 
    
    @Override
    protected void validadeInput( List<String> erros ) throws Exception 
    {
        if( sectorField.getSelectedIndex() == -1 && 
            userField.getSelectedIndex() == -1 )
        {
            erros.add( "Responsável ou Setor são requeridos" );
        }
        
        if( daysField.getValue() <= 0 )
        {
            erros.add( "Dias/Prazo é requerido" );
        }
    }

    @Override
    protected void obtainInput() 
    {
        source.setDays( daysField.getValue() );
        source.setUser( userField.getSelectedIndex() != -1 ? userField.getSelected().getId() : 0 );
        source.setSector( sectorField.getSelectedIndex() != -1 ? sectorField.getSelected().getId() : 0 );
    }

    @Override
    protected void resize() 
    {
        userField.setPrefWidth( getWidth() );
        getDialogPane().requestLayout();
    }

    
    @Override
    protected void setSource( TypeRoute source ) 
    {
        try
        {
            daysField.increment( source.getDays() );
            
            userField.setSelected( com.paa.requestnow.model.ModuleContext
                                                            .getInstance()
                                                            .getUserManager()
                                                            .get( source.getUser() ) );
            
            sectorField.setSelected( com.paa.requestnow.model.ModuleContext
                                                            .getInstance()
                                                            .getSectorManager()
                                                            .get( source.getSector() ) );
        }
        
        catch ( Exception e )
        {
            ApplicationUtilities.logException( e );
        }
    }
    
    
    private void refreshUsers( Sector sector )
    {
        try
        {
            if ( sector != null )
            {
                userField.setItems( com.paa.requestnow.model.ModuleContext 
                                                .getInstance()
                                                .getUserManager()
                                                .getUsersBySector( sector ) );
                userField.setSelected( null );
            }
            
            else 
            {
                userField.setItems( com.paa.requestnow.model.ModuleContext.getInstance().getUserManager().get() );
            }
        }
        
        catch ( Exception e )
        {
            ApplicationUtilities.logException( e );
        }
    }
    
    
    private void refreshSector( User user )
    {
        try
        {
            if ( user != null)
            {
                sectorField.setSelected( com.paa.requestnow.model.ModuleContext 
                                                .getInstance()
                                                .getSectorManager()
                                                .get( user.getSector() ) );
            }
            
            else 
            {
                sectorField.setItems( com.paa.requestnow.model.ModuleContext 
                                                .getInstance()
                                                .getSectorManager().get() );
            }
        }
        
        catch ( Exception e )
        {
            ApplicationUtilities.logException( e );
        }
    }
    
    private void initComponents()
    {
        gridPane.setVgap( 20 );
        gridPane.setHgap( 20 );
        gridPane.setStyle( "-fx-padding: 30;" );
    
        gridPane.add( lbSector,         0, 0, 1, 1 );
        gridPane.add( sectorField,      1, 0, 3, 1 );
    
        gridPane.add( lbUser,           0, 1, 1, 1 );
        gridPane.add( userField,        1, 1, 3, 1 );
      
        gridPane.add( lbDays,           0, 2, 1, 1 );
        gridPane.add( daysField,        1, 2, 3, 1 );
        
        getDialogPane().setContent( gridPane );
        
        userField.addEventHandler( UserSelector.Events.ON_SELECT, new EventHandler<Event>() 
        {
            @Override
            public void handle( Event t ) 
            {
                refreshSector( userField.getSelected() );
            }
        } );
        
        sectorField.addEventFilter( SectorSelector.Events.ON_SELECT, new EventHandler<Event>() 
        {
            @Override
            public void handle( Event t ) 
            {
                refreshUsers( sectorField.getSelected() );
            }
        } );
    
    }
    
    private GridPane gridPane              = new GridPane();
    
    private LabelField lbSector            = new LabelField( "Setor" );
    private SectorSelector sectorField     = new SectorSelector();
    
    private LabelField lbUser              = new LabelField( "Responsável" );
    private UserSelector userField         = new UserSelector();
    
    
    private LabelField lbDays              = new LabelField( "Dias/Prazo", true );
    private Spinner<Integer> daysField     = new Spinner( new SpinnerValueFactory.IntegerSpinnerValueFactory( 0, 99 ) );
}
