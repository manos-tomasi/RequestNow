package com.paa.requestnow.model;

import com.paa.requestnow.control.socket.Server;
import com.paa.requestnow.control.util.MediaPlayer;
import com.paa.requestnow.model.data.User;
import com.paa.requestnow.view.util.FileUtilities;
import com.paa.requestnow.view.util.Prompts;
import java.util.Locale;
import javafx.scene.Parent;
import javafx.stage.Window;

public class ApplicationUtilities
{
    private static ApplicationUtilities ac = null;
    private static User activeUser;
    private static Locale locale = new Locale( "pt", "BR" );
    private Parent root;
    private Window window;
    private Server server;
    
    private ApplicationUtilities()
    {
        server = new Server();
        server.start();
    }
 
    public static ApplicationUtilities getInstance()
    {
        if ( ac == null )
        {
            ac = new ApplicationUtilities();
        }

        return ac;
    }

    public static void logException( Throwable e )
    {
        try
        {   
            MediaPlayer.error();
            FileUtilities.logException( e );
            e.printStackTrace();
            Prompts.error( "Ocorreu um Erro Inesperado !", e.getMessage() );
        } 
        
        catch ( Exception ex )
        { 
            System.err.print( ex );
        }
    }
    
    
    public static Locale getLocale()
    {
        return locale;
    }

    
    public static  String getBackground()
    {
        return "-fx-background-color: #ECEFF1;";
    }

    
    public static  String getColor()
    {
        return "#ECEFF1;";
    }

    
    public static  String getColor2()
    {
        return "#607D8B;";
    }
    
    
    public static  String getBackground2()
    {
        return "-fx-background-color: #607D8B;";
    }
    
    public User getActiveUser()
    {
        return activeUser;
    }

    public void setActiveUser( User user )
    {
        activeUser = user;
    }
    
    public String getActiveUserName()
    {
        if( getActiveUser() != null )
        {
            return  getActiveUser().toString();
        }
        
        return null;
    }
    
    public Window getWindow()
    {
        return window;
    }

    public void setWindow( Window window )
    {
        this.window = window;
    }

    public void setRootComponent( Parent root )
    {
       this.root = root;
    }
    
    public Parent getRootComponent ()
    {
        return root;
    }
    
    public String getCompanny()
    {
        return "RequestNow";
    }
    
    public Server getServer()
    {
        return server;
    }
    
    public void logout()
    {
        setActiveUser( null );
    }
}
    
