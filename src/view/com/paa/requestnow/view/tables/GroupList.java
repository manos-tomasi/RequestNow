package com.paa.requestnow.view.tables;

import com.paa.requestnow.model.data.Group;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

/**
 * @author artur
 */
public class GroupList 
   extends 
        ListView<Group>
{
    public GroupList() 
    {
        initComponents();
        loadData();
    }
    
    private void loadData()
    {
        List items = null;
        
        ObservableList atts = FXCollections.observableArrayList( items );
                        
        getItems().clear();

        setItems( atts );

        requestLayout();
    }
        
    public Group getSelectedGroup()
    {
        return getSelectionModel().getSelectedItem();
    }
    
    private void initComponents()
    {
        getSelectionModel().setSelectionMode( SelectionMode.SINGLE );
        
        getStylesheets().add( "config/table.css" );
    }
}    
