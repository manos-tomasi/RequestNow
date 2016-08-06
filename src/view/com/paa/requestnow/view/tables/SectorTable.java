package com.paa.requestnow.view.tables;

import com.paa.requestnow.model.ResourceLocator;
import com.paa.requestnow.model.data.Sector;
import com.paa.requestnow.view.tables.DefaultTable.ItemColumn.ColumnCallback;
import com.paa.requestnow.view.tables.DefaultTable.ItemColumn;
import javafx.scene.control.Labeled;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author lucas
 */
public class SectorTable 
        extends 
            DefaultTable<Sector>
{
    private Image activeUserImage = new Image( ResourceLocator.getInstance().getImageResource( "finish.png" ) ); 
    private Image deleteUserImage = new Image( ResourceLocator.getInstance().getImageResource( "delete.png" ) );
    
    public SectorTable() 
    {
        setColumns( new ItemColumn("Situação", "state" , 100.0, new ColumnCallback<Sector, Integer>() {
            
            @Override
            public void renderer(Integer value, Labeled cell) 
            {
                ImageView iv = new ImageView( (value == Sector.STATE_ACTIVE)? activeUserImage : deleteUserImage );

                iv.setFitHeight( 20 );
                iv.setFitWidth( 20 );

                cell.setGraphic( iv );
            }
        }),
        new ItemColumn( "Nome", "name" , 500.0 ) ); 
    }
}
