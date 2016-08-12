package com.paa.requestnow.view.util;

import com.paa.requestnow.model.ApplicationUtilities;
import com.paa.requestnow.model.ResourceLocator;
import com.paa.requestnow.model.data.Category;
import com.paa.requestnow.model.data.Field;
import com.paa.requestnow.model.data.Type;
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
 *
 * @author artur
 */
public class CategoryTree 
    extends 
        TreeView<Object>
{
    private Image ICON_TYPE = new Image( ResourceLocator.getInstance().getImageResource( "types.png" ) );
    private Image ICON_CATEGORY = new Image( ResourceLocator.getInstance().getImageResource( "category.png" ) );
    private Image ICON_ROOT = new Image( ResourceLocator.getInstance().getImageResource( "posting.png" ) );
    
    public static class Events
    {
        public static final EventType ON_SELECT    = new EventType( "onSelectNode" );
        public static final EventType ON_SELECT_TYPE = new EventType( "onSelectType" );
    }
    
    public CategoryTree() 
    {
        initComponents();
        
        loadData();
    }
    
    public void refreshContent()
    {
        loadData();
    }
    
    private void loadData()
    {
        try
        {
            TreeItem<Object> rootItem = new TreeItem<>( "RequestNow" );
            rootItem.setExpanded( true );
           
            ImageView imageRoot = new ImageView();
            imageRoot.setImage( ICON_ROOT );
            imageRoot.setFitHeight( 20 );
            imageRoot.setFitWidth( 20 );
            imageRoot.setCache( true );
            imageRoot.setCacheHint( CacheHint.SPEED );
            rootItem.setGraphic( imageRoot ); 
            
            loadCategory( rootItem);

            setRoot( rootItem );
        }
        
        catch( Exception e )
        {
            ApplicationUtilities.logException( e );
        }
    }
    
    private void loadCategory( TreeItem<Object> root ) throws Exception
    {
        List<Category> categories = com.paa.requestnow.model.ModuleContext.getInstance().getCategoryManager().get();
      
        for( Category category : categories )
        {
            TreeItem nodeCategory = new TreeItem( category );
            
            ImageView imageType = new ImageView();
            imageType.setImage( ICON_CATEGORY );
            imageType.setFitHeight( 20 );
            imageType.setFitWidth( 20 );
            imageType.setCache( true );
            imageType.setCacheHint( CacheHint.SPEED );
            nodeCategory.setGraphic( imageType ); 
            nodeCategory.setExpanded(true);
            loadTypes( nodeCategory, category );

            root.getChildren().add( nodeCategory );
        }
    }
    
    private void loadTypes( TreeItem item, Category category ) throws Exception
    {
        List<Type> types = com.paa.requestnow.model.ModuleContext.getInstance().getTypeManager().getTypesCategory( category.getId() );

        for( Type type : types )
        {
              TreeItem nodeType = new TreeItem( type );

              ImageView imageCategory = new ImageView();
              imageCategory.setImage( ICON_TYPE );
              imageCategory.setFitHeight( 20 );
              imageCategory.setFitWidth( 20 );
              imageCategory.setCache( true );
              imageCategory.setCacheHint( CacheHint.SPEED );
              nodeType.setGraphic( imageCategory );
              
              item.getChildren().add( nodeType );
        }
    }
    

    public Category getSelectedCategory()
    {
        Category category = null;
        
        TreeItem item = getSelectionModel().getSelectedItem();
        
        if( item != null )
        {
            if( item.getValue() instanceof Category )
            {
                category = (Category) item.getValue();
            }
        }
            
        return category;
    }

    public Type getSelectedType()
    {
        Type type = null;
        
        TreeItem item = getSelectionModel().getSelectedItem();
        
        if( item != null )
        {
            if( item.getValue() instanceof Type )
            {
                type = (Type) item.getValue();
            }
        }
            
        return type;
    }

    public Field getSelectedField()
    {
        Field field = null;
        
        TreeItem item = getSelectionModel().getSelectedItem();
        
        if( item != null )
        {
            if( item.getValue() instanceof Field )
            {
                field = (Field) item.getValue();
            }
        }
            
        return field;
    }

    public Object getSelectedNode()
    {
        TreeItem item = getSelectionModel().getSelectedItem();
        
        if( item != null )
        {
            if( item.getValue() instanceof Type )
            {
                return (Type) item.getValue();
            }
            
            if( item.getValue() instanceof Category )
            {
                return (Category) item.getValue();
            }
            
            if( item.getValue() instanceof Field )
            {
                return (Field) item.getValue();
            }
           
            if( item.getValue() instanceof String )
            {
                return (String) item.getValue();
            }
        }
            
        return null;
    }
    
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

                if( getSelectedType() != null )
                {
                    CategoryTree.this.fireEvent( new Event( Events.ON_SELECT_TYPE ) );
                }
                else
                {
                    CategoryTree.this.fireEvent( new Event( Events.ON_SELECT ) );
                }
            }
      } );
    }
}
