package com.paa.requestnow.view.tables;

import com.paa.requestnow.model.ResourceLocator;
import com.paa.requestnow.model.data.Sector;
import com.paa.requestnow.model.data.Type;
import com.paa.requestnow.view.tables.DefaultTable.ItemColumn.ColumnCallback;
import com.paa.requestnow.view.tables.DefaultTable.ItemColumn.IconCallback;
import javafx.scene.control.Labeled;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author artur
 */
public class TypeTable
    extends 
        DefaultTable<Type>
{
    private Image activeImage = new Image( ResourceLocator.getInstance().getImageResource( "finish.png" ) ); 
    private Image deleteImage = new Image( ResourceLocator.getInstance().getImageResource( "delete.png" ) );
    
    public TypeTable() 
    {
        setColumns( new ItemColumn( "#", "state", new IconCallback<Type, Integer>() 
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
                    new ItemColumn( "Nome", "name", 250d ),
                    new ItemColumn( "Categoria", "category", 250d, new ColumnCallback<Type, Integer>() 
                    {
                        @Override
                        public void renderer( Integer value, Labeled cell ) throws Exception
                        {
                            cell.setText( com.paa.requestnow.model.ModuleContext
                                                                    .getInstance()
                                                                    .getCategoryManager()
                                                                    .get( value ).getName() );
                        }
                    } ) );
    }
}
