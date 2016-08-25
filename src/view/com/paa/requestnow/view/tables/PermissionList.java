package com.paa.requestnow.view.tables;

import com.paa.requestnow.model.data.Permission;
import java.util.List;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.util.Callback;

/**
 *
 * @author artur
 */
public class PermissionList 
    extends 
        ListView<Permission>
{
    public PermissionList() 
    {
         setCellFactory( new Callback<ListView<Permission>, ListCell<Permission>>() 
         {
            @Override
            public ListCell<Permission> call( ListView<Permission> list )
            {
                return new PermissionListCell();
            }
        } );
        
        getSelectionModel().setSelectionMode( SelectionMode.SINGLE );
        
        getStylesheets().add( "config/table.css" );
        
        
    }    
    
    public void setItems( List<Permission> permissions )
    {
        Platform.runLater( new Runnable() 
        {
            @Override
            public void run()
            {
                ObservableList atts = FXCollections.observableArrayList( permissions );
                        
                getItems().clear();

                setItems( atts );

                requestLayout();
            }
        } );
    }
    
    
    public Permission getSelectedPermission()
    {
        return getSelectionModel().getSelectedItem();
    }
    
    
    private class PermissionListCell extends ListCell<Permission> 
    {
        @Override
        protected void updateItem( Permission permission, boolean empty ) 
        {
            super.updateItem( permission, empty );

            if ( permission != null && ! empty ) 
            {
                CheckBox checkBox = new CheckBox( permission + "NAME");
                
                setGraphic( checkBox );
            }
            else
            {
                setGraphic( null );
            }

        }
    }    
}
