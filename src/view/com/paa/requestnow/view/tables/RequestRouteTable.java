package com.paa.requestnow.view.tables;

import com.paa.requestnow.model.ResourceLocator;
import com.paa.requestnow.model.data.Request;
import com.paa.requestnow.model.data.RequestRoute;
import com.paa.requestnow.model.data.Sector;
import com.paa.requestnow.model.data.Type;
import com.paa.requestnow.model.data.User;
import com.paa.requestnow.view.tables.DefaultTable.ItemColumn.ColumnCallback;
import com.paa.requestnow.view.tables.DefaultTable.ItemColumn.IconCallback;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;
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
    
    private final SimpleDateFormat sf   = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    public RequestRouteTable() 
    {        
        setColumns( new ItemColumn("#", "state", new IconCallback<RequestRoute, Integer>() 
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
        new ItemColumn("Situação", "id", 200.0, new ColumnCallback<RequestRoute, Integer>() 
        {
            @Override
            public void renderer(Integer id, Labeled cell) throws Exception 
            {
                RequestRoute requestRoute = com.paa.requestnow.model.ModuleContext.getInstance().getRequestRouteManager().get( id );
                int days                  = com.paa.requestnow.model.ModuleContext.getInstance().getRequestRouteManager().getDays( requestRoute );
                Request request           = com.paa.requestnow.model.ModuleContext.getInstance().getRequestManager().get( requestRoute.getRequest() );
                
                Timestamp in  = request.getStart();
                
                if( in != null )
                {
                    Timestamp out;
                    long diff, result;
                    String text;
                    
                    if( requestRoute.getOut() != null )
                    {
                        out = requestRoute.getOut();
                        diff  = TimeUnit.MILLISECONDS.toDays( out.getTime() - in.getTime() );
                        result = days - diff;
                        text = ( result == 0 )? "Finalizado no último dia" :
                               ( result < -1 )? "Finalizado com " + result * -1 + " dias de atraso" :  
                               ( result <  0 )? "Finalizado com " + result * -1 + " dia de atraso" :  
                               ( result >  1 )? "Finalizado "     + result      + " dias adiantados" :  
                                                "Finalizado "     + result      + " dia adiantado" ;
                    }
                    else
                    {
                        out = new Timestamp( System.currentTimeMillis() );
                        diff  = TimeUnit.MILLISECONDS.toDays( out.getTime() - in.getTime() );
                        result = days - diff;
                        text = ( result == 0 )? "Último dia" :
                               ( result < -1 )? "Atrasado: " + result * -1 + " dias" :  
                               ( result <  0 )? "Atrasado: " + result * -1 + " dia " :  
                               ( result >  1 )? "Faltam:   " + result      + " dias" :  
                                                "Faltam:   " + result      + " dia " ;
                    }
                    
                    
                    
                    cell.setText( text );
                }
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
