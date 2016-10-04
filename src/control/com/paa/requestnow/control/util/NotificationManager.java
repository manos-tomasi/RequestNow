package com.paa.requestnow.control.util;

import com.paa.requestnow.control.mail.MailManager;
import com.paa.requestnow.model.ApplicationUtilities;
import com.paa.requestnow.model.data.Request;
import com.paa.requestnow.model.data.RequestRoute;
import com.paa.requestnow.model.data.Type;
import com.paa.requestnow.model.data.User;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * @author artur
 */
public class NotificationManager 
{
    private static NotificationManager instance;
    
    public static NotificationManager getInstance()
    {
        if ( instance == null )
        {
            instance = new NotificationManager();
        }
        
        return instance;
    }
    
    private DateFormat df = new SimpleDateFormat( "dd/MM/yyyy HH:mm" );
    
    private NotificationManager()
    {
        
    }
    
    public void notify( Request req, RequestRoute rr ) throws Exception
    {
        User owner  = com.paa.requestnow.model.ModuleContext.getInstance().getUserManager().get( req.getUser() );
        Type type   = com.paa.requestnow.model.ModuleContext.getInstance().getTypeManager().get( req.getType() );
        
        String state = Request.STATES[ req.getState() ];
        
        MailManager mail = MailManager.getInstance();
        
        mail.setHtml( "<html>"+
                            "<body>" +
                                "<h1> Sua requisição " + type + " foi " + state + "</h1>" +
                                "<br>" +
                                "<h3>"+
                                    rr.getInfo() +
                                "</h3>"+
                                "<hr>" +
                                "<table>" +
                                    "<tr>" +
                                        "<td>" +
                                            state + " por: " +
                                        "</td>" +
                                        "<td>" +
                                            ApplicationUtilities.getInstance().getActiveUserName() +
                                        "</td>" +
                                    "</tr>" +
                                    "<tr>" +
                                        "<td>" +
                                            state + " em: " +
                                        "</td>" +
                                        "<td>" +
                                            df.format( req.getEnd() ) +
                                        "</td>" +
                                    "</tr>" +
                                    "<tr>" +
                                        "<td>" +
                                            "Requisitada em: " +
                                        "</td>" +
                                        "<td>" +
                                            df.format( req.getStart() )+
                                        "</td>" +
                                    "</tr>" +
                                "</table>" +
                            "</body>" +
                      "</html>" );
                      
        mail.setSubject( "RequestNow: Requisão " + state );
        mail.sendTo( owner.getMail() );
        
    }
}
