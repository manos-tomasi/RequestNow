package com.paa.requestnow.view.tables;

import com.paa.requestnow.model.ResourceLocator;
import com.paa.requestnow.model.data.Request;
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
public class RequestTable 
        extends 
            DefaultTable<Request>
{
    private final Image reprovedImage   = new Image( ResourceLocator.getInstance().getImageResource( "reproved.png" ) );
    private final Image aprovedImage    = new Image( ResourceLocator.getInstance().getImageResource( "finish.png"   ) );
    private final Image canceledImage   = new Image( ResourceLocator.getInstance().getImageResource( "delete.png"   ) );
    private final Image inProgressImage = new Image( ResourceLocator.getInstance().getImageResource( "play.png"     ) );
    
    private final SimpleDateFormat sf   = new SimpleDateFormat("dd/mm/YYYY HH:mm");

    public RequestTable() 
    {
        setColumns( new ItemColumn("#", "state", new ColumnCallback<Request, Integer>() 
        {
            
            @Override
            public void renderer(Integer state, Labeled cell) throws Exception 
            {
                ImageView image = new ImageView( state == Request.APPROVED    ? aprovedImage    :  
                                                 state == Request.CANCELED    ? canceledImage   :
                                                 state == Request.REPROVED    ? reprovedImage   :
                                                 state == Request.IN_PROGRESS ? inProgressImage : null );
                
                image.setFitHeight( 20 );
                image.setFitWidth( 20 );
                
                cell.setGraphic( image );
            }
        }) ,
        new ItemColumn( "Tipo de Requisição", "type", 300.0, new ColumnCallback<Request, Integer>()
        {
            @Override
            public void renderer( Integer refType, Labeled cell) throws Exception
            {
                Type type = com.paa.requestnow.model.ModuleContext.getInstance().getTypeManager().get(refType);
                
                cell.setText(type.getName());
            }
        }),
        new ItemColumn( "Usuário", "user",  200.0, new ColumnCallback<Request, Integer>() 
        {
            @Override
            public void renderer(Integer refUser, Labeled cell) throws Exception 
            {
                User user = com.paa.requestnow.model.ModuleContext.getInstance().getUserManager().get(refUser);
                
                cell.setText( user.getName() );
            }
        }),
        new ItemColumn( "Data de Fim", "end", 200.0, new ColumnCallback<Request, Timestamp>()
        {
            @Override
            public void renderer(Timestamp end, Labeled cell) throws Exception 
            {
                String dateFormatted = sf.format(end);
                
                cell.setText(dateFormatted);
            }
            
        }),
        new ItemColumn( "Data de inicio", "start", 200.0, new ColumnCallback<Request, Timestamp>()
        {
            @Override
            public void renderer(Timestamp start, Labeled cell) throws Exception 
            {
                String dateFormatted = sf.format(start);
                
                cell.setText(dateFormatted);
            }
        }));
    }
}
