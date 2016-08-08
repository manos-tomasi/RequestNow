package com.paa.requestnow.view.tables;

import com.paa.requestnow.model.ResourceLocator;
import com.paa.requestnow.model.data.Category;
import com.paa.requestnow.model.data.Sector;
import com.paa.requestnow.view.tables.DefaultTable.ItemColumn.ColumnCallback;
import javafx.scene.control.Labeled;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author artur
 */
public class CategoryTable 
    extends 
        DefaultTable<Category>
{
    private Image activeImage = new Image( ResourceLocator.getInstance().getImageResource( "finish.png" ) ); 
    private Image deleteImage = new Image( ResourceLocator.getInstance().getImageResource( "delete.png" ) );
    
    public CategoryTable() 
    {
        setColumns( new ItemColumn( "#", "state", new ColumnCallback<Category, Integer>()
                    {
                        @Override
                        public void renderer( Integer value, Labeled cell )
                        {
                            ImageView iv = new ImageView( value == Sector.STATE_ACTIVE ? activeImage : deleteImage );

                            iv.setFitHeight( 20 );
                            iv.setFitWidth( 20 );

                            cell.setGraphic( iv );
                        }
                    } ),
                    new ItemColumn( "Nome", "name", 250d ) );
    }
}
