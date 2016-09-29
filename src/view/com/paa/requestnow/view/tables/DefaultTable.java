package com.paa.requestnow.view.tables;

import com.paa.requestnow.model.ApplicationUtilities;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Labeled;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

/**
 * @author artur
 * @param <T>
 */
public class DefaultTable<T>
    extends 
        Pagination
{
    private static final int MAX_ROWS = 40;
    
    private final ObservableList<T> list = FXCollections.observableArrayList();
    
    public DefaultTable()
    {
        initComponents();
    }
    
    public void setOnClick( EventHandler<MouseEvent> handler )
    {
        tableView.setOnMouseClicked( handler );
    }
    
    public DefaultTable( ItemColumn ... items )
    {
        initComponents();
        
        setColumns( items );
    }
    
    public Node createPage( int pageIndex )
    {
        int lastIndex;
        int displace = list.size() % MAX_ROWS;
        
        if (displace > 0)
        {
            lastIndex = list.size() / MAX_ROWS;
        }
        else
        {
            lastIndex = list.size() / MAX_ROWS - 1;
        }
        
        int page = pageIndex * MAX_ROWS;
        
        for ( int i = page; i < page + MAX_ROWS; i++ )
        {
            if (  lastIndex == pageIndex )
            {
                tableView.setItems( FXCollections.observableArrayList( list.subList( pageIndex * MAX_ROWS,
                                                                                     Math.min( ( pageIndex * MAX_ROWS + displace ), list.size() ) ) ) );
            }
            else
            {
                tableView.setItems( FXCollections.observableArrayList( list.subList( pageIndex * MAX_ROWS, 
                                                                                     Math.min( ( pageIndex * MAX_ROWS + MAX_ROWS ), list.size() ) ) ) );
            }

        }
        return tableView;
    }
    
    public void setColumns( ItemColumn ... items )
    {
        if( items != null )
        {
            for ( ItemColumn item : items )
            {
                TableColumn column = new TableColumn( item.getLabel() );
                column.setCellValueFactory( new PropertyValueFactory( item.getAttribute() ) );

                if( ! item.getWidth().isNaN() )
                    column.setPrefWidth( item.getWidth() );
                
                if( item.getCallback() != null )
                    column.setCellFactory( (Callback) item.getCallback() );

                tableView.getColumns().add( column );
            }
        }
    }
   
    public void setItems( List<T> items )
    {
        try
        {
            long max = Math.round( new Double( items.size() ) / MAX_ROWS );
            
            setMaxPageIndicatorCount( Math.max( (int) max, 1 ) );
            setPageCount(  Math.max( (int) max, 1 ) ); 
           
            list.setAll( items );
            
            tableView.setItems( list );
            tableView.requestFocus();
            tableView.requestLayout();
        }
        
        catch ( Exception e )
        {
            ApplicationUtilities.logException( e );
        }
    }
    
    public T getSelectedItem()
    {
        return tableView.getSelectionModel().getSelectedItem();
    }
    
    public void setSelectedItem( T item )
    {
        if( item != null )
        {
            tableView.getSelectionModel().select( item );
        }
    }
    
    public List<T> getSelectedItems()
    {
        return tableView.getSelectionModel().getSelectedItems();
    }
    
    private void initComponents() 
    {
        setPageFactory ( this::createPage );
        
        setStyle( "-fx-page-information-visible: false;" );
        tableView.getStylesheets().add( "config/table.css" );
        
        getChildren().add( tableView );
    }
    
    private TableView<T> tableView = new TableView();
    
    public static class ItemColumn
    {
        private String label = "";
        private String attribute = "";
        private AbstractCallback callback;
        private Double width;
        
        public ItemColumn( String label, String attribute )
        {
            this( label, attribute, Double.NaN );
        }
        
        public ItemColumn( String label, String attribute, Double width )
        {
            this( label, attribute, width, null );
        }
        
        public ItemColumn( String label, String attribute, AbstractCallback calback )
        {
            this( label, attribute, Double.NaN, calback );
        }
        
        public ItemColumn( String label, String attribute, Double width, AbstractCallback calback ) 
        {
            this.label = label;
            this.attribute = attribute;
            this.callback = calback;
            this.width = width;
        }

        public String getLabel() 
        {
            return label;
        }

        public String getAttribute() 
        {
            return attribute;
        }

        public AbstractCallback getCallback()
        {
            return callback;
        }

        public Double getWidth()
        {
            return width;
        }
        
        public abstract static class ColumnCallback<A, T>
            implements 
                Callback< TableColumn<A,T>, TableCell<A,T>>, AbstractCallback<T>
        {
            @Override
            public TableCell<A, T> call( TableColumn<A, T> p ) 
            {
                return new TableCell<A, T>()
                {
                    @Override
                    protected void updateItem( T t, boolean bln ) 
                    {
                        super.updateItem( t, bln );
                        
                        if ( bln || t == null )
                        {
                            setText( null);
                        }

                        else if( ! bln )
                        {
                            try
                            {
                                renderer( t, this );
                            }
                            
                            catch ( Exception e )
                            {
                                ApplicationUtilities.logException( e );
                            }
                        }
                    }
                  
                };
            }
            
            public abstract void renderer( T value, Labeled cell ) throws Exception;
        }
        
        public abstract static class IconCallback<A, T>
            implements 
                Callback< TableColumn<A,T>, TableCell<A,T>>, AbstractCallback<T>
        {
            @Override
            public TableCell<A, T> call( TableColumn<A, T> p ) 
            {
                return new TableCell<A, T>()
                {
                    @Override
                    protected void updateItem( T t, boolean bln ) 
                    {
                        super.updateItem( t, bln );
                        
                        if ( bln || t == null )
                        {
                            setGraphic( null);
                        }

                        else if( ! bln )
                        {
                            try
                            {
                                renderer( t, this );
                            }
                            
                            catch ( Exception e )
                            {
                                ApplicationUtilities.logException( e );
                            }
                        }
                    }
                  
                };
            }
            
            @Override
            public abstract void renderer( T value, Labeled cell ) throws Exception;
        }
        
        public interface AbstractCallback<T>
        {
            public void renderer( T value, Labeled cell ) throws Exception;
        }
    }
}
