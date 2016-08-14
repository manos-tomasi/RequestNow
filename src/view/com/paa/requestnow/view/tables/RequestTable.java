package com.paa.requestnow.view.tables;

import com.paa.requestnow.model.ResourceLocator;
import com.paa.requestnow.model.data.Request;
import com.paa.requestnow.model.data.Type;
import com.paa.requestnow.model.data.User;
import com.paa.requestnow.view.tables.DefaultTable.ItemColumn.ColumnCallback;
import java.sql.Date;
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

    public RequestTable() 
    {
        setColumns( new ItemColumn("#", "state", new ColumnCallback<Request, Integer>() 
        {
            private final Image reprovedImage   = new Image( ResourceLocator.getInstance().getImageResource( "reproved.png" ) );
            private final Image aprovedImage    = new Image( ResourceLocator.getInstance().getImageResource( "finish.png" ) );
            private final Image canceledImage   = new Image( ResourceLocator.getInstance().getImageResource( "delete.png" ) );
            private final Image inProgressImage = new Image( ResourceLocator.getInstance().getImageResource( "paly.png"   ) );
            
            @Override
            public void renderer(Integer state, Labeled cell) throws Exception 
            {
                ImageView image = new ImageView( state == Request.APPROVED    ? canceledImage   :  
                                                 state == Request.CANCELED    ? canceledImage   :
                                                 state == Request.REPROVED    ? reprovedImage   :
                                                 state == Request.IN_PROGRESS ? inProgressImage : null );
                
                image.setFitHeight( 20 );
                image.setFitWidth( 20 );
                
                cell.setGraphic( image );
            }
        }) ,
        new ItemColumn( "Tipo de Requisição", "ref_type", 300.0, new ColumnCallback<Request, Integer>()
        {
            @Override
            public void renderer( Integer refType, Labeled cell) throws Exception
            {
                Type type = com.paa.requestnow.model.ModuleContext.getInstance().getTypeManager().get(refType);
                
                cell.setText(type.getName());
            }
        }),
        new ItemColumn( "Usuário", "ref_user",  200.0, new ColumnCallback<Request, Integer>() 
        {
            @Override
            public void renderer(Integer refUser, Labeled cell) throws Exception 
            {
                User user = com.paa.requestnow.model.ModuleContext.getInstance().getUserManager().get(refUser);
                
                cell.setText( user.getName() );
            }
        }),
        new ItemColumn( "Data de Fim", "dt_end", 200.0, new ColumnCallback<Request, Date>()
        {
            @Override
            public void renderer(Date end, Labeled cell) throws Exception 
            {
                SimpleDateFormat sf = new SimpleDateFormat("dd/mm/YYYY");
                java.util.Date date = new java.util.Date( end.getTime() );
                String dateFormatted = sf.format(date);
                
                cell.setText(dateFormatted);
            }
            
        }),
        new ItemColumn( "Data de inicio", "dt_start", 200.0, new ColumnCallback<Request, Date>()
        {
            @Override
            public void renderer(Date start, Labeled cell) throws Exception 
            {
                SimpleDateFormat sf = new SimpleDateFormat("dd/mm/YYYY");
                java.util.Date date = new java.util.Date( start.getTime() );
                String dateFormatted = sf.format(date);
                
                cell.setText(dateFormatted);
            }
        }));
    }
}
