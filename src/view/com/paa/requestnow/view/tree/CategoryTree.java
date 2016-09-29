package com.paa.requestnow.view.tree;

import com.paa.requestnow.model.ApplicationUtilities;
import com.paa.requestnow.model.ResourceLocator;
import com.paa.requestnow.model.data.Category;
import com.paa.requestnow.model.data.Core;
import com.paa.requestnow.model.data.Field;
import com.paa.requestnow.model.data.Type;
import com.paa.requestnow.model.data.TypeRoute;
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
    /**
     * 
     */
    public static class Events
    {
        public static final EventType ON_SELECT          = new EventType( "onSelectNode" );
        public static final EventType ON_SELECT_TYPE     = new EventType( "onSelectType" );
        public static final EventType ON_SELECT_FIELD    = new EventType( "onSelectField" );
        public static final EventType ON_SELECT_ROUTE    = new EventType( "onSelectRoute" );
        public static final EventType ON_SELECT_CATEGORY = new EventType( "onSelectCategory" );
    }
    
    public static final int MODE_REQUEST  = 0;
    public static final int MODE_FIELD = 1;
    public static final int MODE_ROUTE = 2;
    public static final int MODE_CATEGORY = 3;
    public static final int MODE_TYPE = 4;
    
    private int mode = MODE_REQUEST;
    
    private Image ICON_TYPE = new Image( ResourceLocator.getInstance().getImageResource( "types.png" ) );
    private Image ICON_CATEGORY = new Image( ResourceLocator.getInstance().getImageResource( "categories.png" ) );
    private Image ICON_ROOT = new Image( ResourceLocator.getInstance().getImageResource( "request_now.png" ) );
    private Image ICON_ROUTE = new Image( ResourceLocator.getInstance().getImageResource( "routes.png" ) );
    private Image ICON_FIELDS = new Image( ResourceLocator.getInstance().getImageResource( "fields.png" ) );
    
    /**
     * 
     */
    private CategoryTree() {};
    
    /**
     * 
     * @param mode 
     */
    public CategoryTree( int mode ) 
    {
        this.mode = mode;
        
        initComponents();
        
        loadData();
    }
    
    /**
     * 
     */
    public void refreshContent()
    {
        Object node = getSelectedNode();
        
        loadData();
        
        setSelectedNode( node );
    }
    
    /**
     * 
     */
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
    
    /**
     * 
     * @param root
     * @throws Exception 
     */
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
            
            if ( mode == MODE_REQUEST || mode == MODE_FIELD  ||
                 mode == MODE_TYPE    || mode == MODE_ROUTE  )
            {
                loadTypes( nodeCategory, category );
            }

            root.getChildren().add( nodeCategory );
        }
    }
        
    /**
     * 
     * @param item
     * @param category
     * @throws Exception 
     */
    private void loadTypes( TreeItem item, Category category ) throws Exception
    {
        List<Type> types;
        
        if ( MODE_REQUEST == mode )
        {
            types = com.paa.requestnow.model.ModuleContext
                                                        .getInstance()
                                                        .getTypeManager()
                                                        .getTypesCategoryWithRoute( category.getId() );
        }
        
        else
        {    
            types = com.paa.requestnow.model.ModuleContext
                                                        .getInstance()
                                                        .getTypeManager()
                                                        .getTypesCategory( category.getId() );
        }

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
              nodeType.setExpanded( true );
              
              if ( mode == MODE_FIELD )
              {
                  loadFields( nodeType, type ); 
              }
              
              else if ( mode == MODE_ROUTE )
              {
                  loadRoutes( nodeType, type );
              }
              
              
              item.getChildren().add( nodeType );
        }
    }
    
    /**
     * 
     * @param item
     * @param type
     * @throws Exception 
     */
    private void loadFields( TreeItem item, Type type ) throws Exception
    {
        List<Field> fields = com.paa.requestnow.model.ModuleContext.getInstance().getFieldManager().getFieldsType( type.getId() );
                  
        for( Field field : fields )
        {
              TreeItem nodeField = new TreeItem( field );

              ImageView imageField = new ImageView();
              imageField.setImage( ICON_FIELDS );
              imageField.setFitHeight( 20 );
              imageField.setFitWidth( 20 );
              imageField.setCache( true );
              imageField.setCacheHint( CacheHint.SPEED );
              nodeField.setGraphic( imageField ); 

              item.getChildren().add( nodeField );
        }
    }
    
    /**
     * 
     * @param item
     * @param type
     * @throws Exception 
     */
    private void loadRoutes( TreeItem item, Type type ) throws Exception
    {
        List<TypeRoute> fields = com.paa.requestnow.model.ModuleContext.getInstance().getTypeRouteManager().getByType( type.getId() );
                  
        for( TypeRoute route : fields )
        {
              TreeItem nodeRoute = new TreeItem( route );

              ImageView imageField = new ImageView();
              imageField.setImage( ICON_ROUTE );
              imageField.setFitHeight( 20 );
              imageField.setFitWidth( 20 );
              imageField.setCache( true );
              imageField.setCacheHint( CacheHint.SPEED );
              nodeRoute.setGraphic( imageField ); 

              item.getChildren().add( nodeRoute );
        }
    }
    
    /**
     * 
     * @return 
     */
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
    
    /**
     * 
     * @return 
     */
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
    
    /**
     * 
     * @return 
     */
    public TypeRoute getSelectedRoute()
    {
        TypeRoute route = null;
        
        TreeItem item = getSelectionModel().getSelectedItem();
        
        if( item != null )
        {
            if( item.getValue() instanceof TypeRoute )
            {
                route = (TypeRoute) item.getValue();
            }
        }
            
        return route;
    }
    
    /**
     * 
     * @return 
     */
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

    public void setSelectedNode( Object o )
    {
        if ( o instanceof TreeItem )
        {
           getSelectionModel().select( (TreeItem) o ); 
        }
    }
    
    /**
     * 
     * @return 
     */
    public Object getSelectedNode()
    {
        return getSelectionModel().getSelectedItem();
    }
    
    public Core getSelectedCore()
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
            
            if( item.getValue() instanceof TypeRoute )
            {
                return (TypeRoute) item.getValue();
            }
    
            if( item.getValue() instanceof Field )
            {
                return (Field) item.getValue();
            }
           
            if( item.getValue() instanceof String )
            {
                return null;
            }
        }
            
        return null;
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
                if( getSelectedType() != null )
                {
                    CategoryTree.this.fireEvent( new Event( Events.ON_SELECT_TYPE ) );
                }
                
                else if( getSelectedField() != null )
                {
                    CategoryTree.this.fireEvent( new Event( Events.ON_SELECT_FIELD ) );
                }
                
                else if( getSelectedRoute() != null )
                {
                    CategoryTree.this.fireEvent( new Event( Events.ON_SELECT_ROUTE ) );
                }
                
                else if( getSelectedCategory() != null )
                {
                    CategoryTree.this.fireEvent( new Event( Events.ON_SELECT_CATEGORY ) );
                }
                
                else
                {
                    CategoryTree.this.fireEvent( new Event( Events.ON_SELECT ) );
                }
            }
      } );
    }
}
