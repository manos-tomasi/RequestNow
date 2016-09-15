package com.paa.requestnow.view.tree;

import com.paa.requestnow.model.ApplicationUtilities;
import com.paa.requestnow.model.data.Group;
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

/**
 * @author artur
 */
public class RoleTree 
    extends 
        TreeView<Object>
{
    private List<Group> groups;
    
    public static class Events
    {
        public static final EventType ON_SELECT_GROUP = new EventType( "onSelectGroup" );
        public static final EventType ON_SELECT_ROLE = new EventType( "onSelectRole" );
    }
    
    /**
     * 
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
            loadGroups();
            
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

            for ( Group group : groups )
            {
                TreeItem nodeGroup = new TreeItem( group );
                nodeRole.getChildren().add( nodeGroup );
            }
            
            root.getChildren().add( nodeRole );
        }
    }
    
    
    private void loadGroups() throws Exception
    {
        groups = com.paa.requestnow.model.ModuleContext.getInstance().getGroupManager().get();

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
            
            else if ( item.getValue() instanceof Group )
            {
                TreeItem parent = item.getParent();
                
                if ( parent != null && ( parent.getValue() instanceof Role ) )
                {
                    role = (Role) parent.getValue();
                }
            }
        }
            
        return role;
    }
    
    /**
     * 
     * @return 
     */
    public Group getSelectedGroup()
    {
        Group group = null;
        
        TreeItem item = getSelectionModel().getSelectedItem();
        
        if( item != null )
        {
            if( item.getValue() instanceof Role )
            {
                TreeItem parent = item.getParent();
                
                if ( parent != null && ( parent.getValue() instanceof Group ) )
                {
                    group = (Group) parent.getValue();
                }
            }
            
            else if ( item.getValue() instanceof Group )
            {
                group = (Group) item.getValue();
            }
        }
            
        return group;
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
                TreeItem item = getSelectionModel().getSelectedItem();
                
                if ( item != null )
                {
                    if ( item.getValue() instanceof Group )
                    {
                        RoleTree.this.fireEvent( new Event( Events.ON_SELECT_GROUP ) );
                    }
                    
                    else if ( item.getValue() instanceof Role )
                    {
                        RoleTree.this.fireEvent( new Event( Events.ON_SELECT_ROLE ) );
                    }
                }
            }
      } );
    }
}
