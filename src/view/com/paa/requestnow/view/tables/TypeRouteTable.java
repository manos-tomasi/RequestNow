package com.paa.requestnow.view.tables;

import com.paa.requestnow.model.ResourceLocator;
import com.paa.requestnow.model.data.Sector;
import com.paa.requestnow.model.data.TypeRoute;
import com.paa.requestnow.model.data.User;
import com.paa.requestnow.view.tables.DefaultTable.ItemColumn.ColumnCallback;
import com.paa.requestnow.view.tables.DefaultTable.ItemColumn.IconCallback;
import javafx.scene.control.Labeled;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author artur
 */
public class TypeRouteTable 
    extends 
        DefaultTable<TypeRoute>
{  
    private Image activeImage = new Image( ResourceLocator.getInstance().getImageResource( "finish.png" ) );
    private Image deleteImage = new Image( ResourceLocator.getInstance().getImageResource( "delete.png" ) );
    
    public TypeRouteTable() 
    {
        setColumns( new ItemColumn( "#", "state", new IconCallback<TypeRoute, Integer>() 
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
                    new ItemColumn( "Responsável", "user", 250d, new ColumnCallback<TypeRoute, Integer>() 
                    {
                        @Override
                        public void renderer( Integer value, Labeled cell ) throws Exception 
                        {
                            User user = com.paa.requestnow.model.ModuleContext
                                                                        .getInstance()
                                                                        .getUserManager()
                                                                        .get( value );
                            
                            cell.setText( user != null ? user.getName() : "Não Definido" );
                        }
                    }  ),
                    new ItemColumn( "Setor", "sector", 250d, new ColumnCallback<TypeRoute, Integer>() 
                    {
                        @Override
                        public void renderer( Integer value, Labeled cell ) throws Exception 
                        {
                            Sector sector = com.paa.requestnow.model.ModuleContext
                                                                        .getInstance()
                                                                        .getSectorManager()
                                                                        .get( value );
                            
                            cell.setText( sector != null ? sector.getName() : "Não Definido" );
                        }
                    } ),
                    new ItemColumn( "Sequencia", "sequence" ),
                    new ItemColumn( "Dias/Prazo", "days" ) );
    }
}
