package com.paa.requestnow.view.tables;

import com.paa.requestnow.model.ResourceLocator;
import com.paa.requestnow.model.data.Sector;
import com.paa.requestnow.model.data.User;
import com.paa.requestnow.view.tables.DefaultTable.ItemColumn;
import com.paa.requestnow.view.tables.DefaultTable.ItemColumn.ColumnCallback;
import javafx.scene.control.Labeled;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author artur
 */
public class UserTable
    extends 
        DefaultTable<User>
{
    private Image activeUserImage = new Image( ResourceLocator.getInstance().getImageResource( "finish.png" ) ); 
    private Image deleteUserImage = new Image( ResourceLocator.getInstance().getImageResource( "delete.png" ) ); 
    
    public UserTable() 
    {
        setColumns( new ItemColumn( "#", "state", new ColumnCallback<User, Integer>()  
        {
            @Override
            public void renderer(Integer value, Labeled cell) 
            {
                ImageView imageView =  new ImageView( value == User.STATE_ACTIVE ? activeUserImage : deleteUserImage );

                imageView.setFitHeight( 20 );
                imageView.setFitWidth( 20 );

                cell.setGraphic( imageView );
            }
        } ),
        new ItemColumn( "Nome", "name" ),
        new ItemColumn( "Email", "mail" ),
        new ItemColumn( "Telefone", "phone", new ColumnCallback<User, String>() 
        {
            @Override
            public void renderer( String value, Labeled cell) 
            {
                cell.setText( value.replaceFirst( "([0-9]{2})([0-9]{4})([0-9]{4,5})$", "($1)$2-$3" ) );
            }
        } ),
        
        new ItemColumn( "Setor", "sector", new ColumnCallback<User, Integer>() 
        {
            @Override
            public void renderer( Integer value, Labeled cell) throws Exception
            {
                Sector sector = com.paa.requestnow.model.ModuleContext.getInstance().getSectorManager().get( value );
                
                cell.setText( sector != null ? sector.getName() : "Não Definido" );
            }
        } ),
        
        new ItemColumn( "CPF", "cpf", new ColumnCallback<User, String>() 
        {
            @Override
            public void renderer( String value, Labeled cell) 
            {
                cell.setText( value.replaceAll( "([0-9]{3})([0-9]{3})([0-9]{3})([0-9]{2})$", "$1.$2.$3-$4" ) );
            }
        } ),
        new ItemColumn( "RG", "rg" ),
        new ItemColumn( "Sexo", "gender",new ColumnCallback<User, Integer>()  
        {
            @Override
            public void renderer(Integer value, Labeled cell) 
            {
                cell.setText( User.GENDER[ value ] );
            }
        } ),
        new ItemColumn( "Categoria", "role",new ColumnCallback<User, Integer>()  
        {
            @Override
            public void renderer(Integer value, Labeled cell) 
            {
                cell.setText( User.ROLE[ value ] );
            }
        } ) );
    }
}
