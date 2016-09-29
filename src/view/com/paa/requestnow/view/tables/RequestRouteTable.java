package com.paa.requestnow.view.tables;

import com.paa.requestnow.model.ResourceLocator;
import com.paa.requestnow.model.data.Request;
import com.paa.requestnow.model.data.RequestRoute;
import com.paa.requestnow.model.data.Sector;
import com.paa.requestnow.model.data.Type;
import com.paa.requestnow.model.data.User;
import com.paa.requestnow.view.tables.DefaultTable.ItemColumn.ColumnCallback;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import javafx.scene.control.Labeled;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author lucas
 */
public class RequestRouteTable 
        extends 
            DefaultTable<RequestRoute>
{
    private final Image reprovedImage   = new Image( ResourceLocator.getInstance().getImageResource( "reproved.png" ) );
    private final Image aprovedImage    = new Image( ResourceLocator.getInstance().getImageResource( "finish.png"   ) );
    private final Image canceledImage   = new Image( ResourceLocator.getInstance().getImageResource( "delete.png"   ) );
    private final Image stopedImage     = new Image( ResourceLocator.getInstance().getImageResource( "play.png"     ) );
    private final Image waintingImage   = new Image( ResourceLocator.getInstance().getImageResource( "clock.png"    ) );
    
    private final SimpleDateFormat sf   = new SimpleDateFormat("dd/mm/YYYY HH:mm");

    public RequestRouteTable() 
    {        
        setColumns( new ItemColumn("#", "state", new ColumnCallback<RequestRoute, Integer>() 
        {
            
            @Override
            public void renderer(Integer state, Labeled cell) throws Exception 
            {
                ImageView image = new ImageView( state == RequestRoute.APPROVED  ? aprovedImage    :  
                                                 state == RequestRoute.CANCELED ? canceledImage    :
                                                 state == RequestRoute.DISAPPROVED ? reprovedImage :
                                                 state == RequestRoute.STOPED   ? stopedImage      :
                                                 state == RequestRoute.WAINTING ? waintingImage :null );
                
                image.setFitHeight( 20 );
                image.setFitWidth( 20 );
                
                cell.setGraphic( image );
            }
        }),
        new ItemColumn( "Tipo de Requisição", "request", 300.0, new ColumnCallback<Request, Integer>()
        {
            @Override
            public void renderer( Integer request, Labeled cell) throws Exception
            {
                Type type  = com.paa.requestnow.model.ModuleContext.getInstance().getRequestManager().getType(request );
                
                cell.setText( type.getName() );
            }
        }),
        new ItemColumn( "Usuário", "user",  200.0, new ColumnCallback<Request, Integer>() 
        {
            @Override
            public void renderer(Integer refUser, Labeled cell) throws Exception 
            {
                if( refUser != 0 )
                {
                    User user = com.paa.requestnow.model.ModuleContext.getInstance().getUserManager().get(refUser);
                
                    cell.setText( user.getName() );
                }
                else
                {
                    cell.setText( "N/D" );
                }
            }
        }),
        new ItemColumn( "Setor", "sector",  200.0, new ColumnCallback<Request, Integer>() 
        {
            @Override
            public void renderer(Integer refSector, Labeled cell) throws Exception 
            {
                if( refSector != 0 )
                {
                    Sector sector = com.paa.requestnow.model.ModuleContext.getInstance().getSectorManager().get(refSector);
                    cell.setText( sector.getName() );
                }
                else
                {
                    cell.setText( "N/D" );
                }
            }
        }),
        new ItemColumn("Requisição", "request" , 100.0 ),
        new ItemColumn( "Data de Entrada", "in", 200.0, new ColumnCallback<Request, Timestamp>()
        {
            @Override
            public void renderer(Timestamp in, Labeled cell) throws Exception 
            {
                String dateFormatted = sf.format(in);
                
                cell.setText(dateFormatted);
                
            }
            
        }),
        new ItemColumn( "Data de Saída", "out", 200.0, new ColumnCallback<Request, Timestamp>()
        {
            @Override
            public void renderer(Timestamp out, Labeled cell) throws Exception 
            {
                String dateFormatted = sf.format(out);
                
                cell.setText(dateFormatted);
            }
        }),
        new ItemColumn( "Sequência" , "sequence" , 150.0 ));
    }
}
