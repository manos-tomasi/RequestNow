package com.paa.requestnow.view.tree;

import com.paa.requestnow.model.ApplicationUtilities;
import com.paa.requestnow.model.ResourceLocator;
import com.paa.requestnow.model.data.Role;
import java.util.List;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.scene.CacheHint;
import javafx.scene.Cursor;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author artur
 */
public class RoleTree 
    extends 
        TreeView<Object>
{
    public static class Events
    {
        public static final EventType ON_SELECT = new EventType( "onSelectRole" );
    }
    
    private Image ICON_ROOT = new Image( ResourceLocator.getInstance().getImageResource( "request_now.png" ) );
    private Image ICON_ROLE = new Image( ResourceLocator.getInstance().getImageResource( "role.png" ) );
    
    /**
     */
    public RoleTree() 
    {
        initComponents();
        loadData();
    };
    
    /**
     * 
     */
    public void refreshContent()
    {
        loadData();
    }
    
    /**
     * 
     */
    private void loadData()
    {
        try
        {
            TreeItem<Object> rootItem = new TreeItem( "RequestNow" );
            rootItem.setExpanded( true );
           
            ImageView imageRoot = new ImageView();
            imageRoot.setImage( ICON_ROOT );
            imageRoot.setFitHeight( 20 );
            imageRoot.setFitWidth( 20 );
            imageRoot.setCache( true );
            imageRoot.setCacheHint( CacheHint.SPEED );
            rootItem.setGraphic( imageRoot ); 
            
            loadRole( rootItem );

            setRoot( rootItem );
        }
        
        catch( Exception e )
        {
            ApplicationUtilities.logException( e );
        }
    }
    
    /**
     * 
     * @param root
     * @throws Exception 
     */
    private void loadRole( TreeItem<Object> root ) throws Exception
    {
        List<Role> roles = com.paa.requestnow.model.ModuleContext.getInstance().getRoleManager().get();
      
        for( Role role : roles )
        {
            TreeItem nodeRole = new TreeItem( role );
            
            ImageView imageType = new ImageView();
            imageType.setImage( ICON_ROLE );
            imageType.setFitHeight( 20 );
            imageType.setFitWidth( 20 );
            imageType.setCache( true );
            imageType.setCacheHint( CacheHint.SPEED );
            nodeRole.setGraphic( imageType ); 
            nodeRole.setExpanded(true);
            
            root.getChildren().add( nodeRole );
        }
    }
        
    /**
     * 
     * @return 
     */
    public Role getSelectedRole()
    {
        Role role = null;
        
        TreeItem item = getSelectionModel().getSelectedItem();
        
        if( item != null )
        {
            if( item.getValue() instanceof Role )
            {
                role = (Role) item.getValue();
            }
        }
            
        return role;
    }
    
    public void setSelectedNode( Object o )
    {
        if ( o != null )
        {
           getSelectionModel().select( new TreeItem(o) );
        }
    }
    
    /**
     * 
     */
    private void initComponents()
    {
        setCache( true );
        setCacheHint(CacheHint.SPEED);
        setCacheShape( true );
        setShowRoot( true );
        setCursor( Cursor.HAND );
        setWidth(300);
        getStylesheets().add( "config/tree.css" );
        setStyle( "-fx-background-color: transparent;" );
        
        getSelectionModel().selectedItemProperty().addListener( new ChangeListener() 
        {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) 
            {
                RoleTree.this.fireEvent( new Event( Events.ON_SELECT ) );
            }
      } );
    }
}
