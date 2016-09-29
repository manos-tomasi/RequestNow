package com.paa.requestnow.view.tables;

import com.paa.requestnow.model.ResourceLocator;
import com.paa.requestnow.model.data.Field;
import com.paa.requestnow.model.data.Sector;
import com.paa.requestnow.view.tables.DefaultTable.ItemColumn.ColumnCallback;
import com.paa.requestnow.view.tables.DefaultTable.ItemColumn.IconCallback;
import javafx.scene.control.Labeled;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author artur
 */
public class FieldTable
    extends 
        DefaultTable<Field>
{
    private Image activeImage = new Image( ResourceLocator.getInstance().getImageResource( "finish.png" ) ); 
    private Image deleteImage = new Image( ResourceLocator.getInstance().getImageResource( "delete.png" ) );
    
    public FieldTable() 
    {
        setColumns( new ItemColumn( "#", "state", new IconCallback<Field, Integer>() 
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
                    new ItemColumn( "Label", "label", 250d ),
                    new ItemColumn( "Tipo", "type", 250d, new ColumnCallback<Field, Integer>() 
                    {
                        @Override
                        public void renderer( Integer value, Labeled cell ) throws Exception
                        {
                            cell.setText( Field.TYPES[ value ] );
                        }
                    } ),
                    new ItemColumn( "Tipo Requisição", "typeRequest", 250d, new ColumnCallback<Field, Integer>() 
                    {
                        @Override
                        public void renderer( Integer value, Labeled cell ) throws Exception
                        {
                            cell.setText( com.paa.requestnow.model.ModuleContext
                                                                    .getInstance()
                                                                    .getTypeManager()
                                                                    .get( value ).getName() );
                        }
                    } ),
                    new ItemColumn( "Requerido", "required", 250d, new ColumnCallback<Field, Boolean>() 
                    {
                        @Override
                        public void renderer( Boolean value, Labeled cell ) throws Exception
                        {
                            cell.setText( value ? "Requerido" : "Opcional" );
                        }
                    } ) );
    }
}
