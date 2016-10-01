package com.paa.requestnow.control.socket;

import com.paa.requestnow.control.util.Serializer;
import com.paa.requestnow.model.ApplicationUtilities;
import com.paa.requestnow.model.ConfigurationManager;
import java.io.IOException;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;

/**
 * @author artur
 */
public class Server
    implements 
        Runnable
{
    private Serializable source;
    
    private static final String HOST = ConfigurationManager.getInstance().getProperty( "server.host", "127.0.0.1" );
    private static final Integer PORT = Integer.parseInt( ConfigurationManager.getInstance().getProperty( "server.port", "12345" ) );

    public Server() 
    {
        try
        {
            new ServerSocket( PORT );
        }
        
        catch ( Exception e )
        {
            e.printStackTrace();
        }
    }

    public void sendPacket( Serializable source ) 
    {
        this.source = source;
    }

    @Override
    public void run()
    {
        while ( true )
        {
            try 
            {
                Thread.sleep( 10 );
                
                if ( source != null ) 
                {
                    byte[] b = Serializer.serialize( source );
                    
                    InetAddress addr   = InetAddress.getByName( HOST );
                    DatagramSocket ds  = new DatagramSocket();
                    DatagramPacket pkg = new DatagramPacket(b, b.length, addr, PORT );
                    
                    ds.send( pkg );
                    
                    source = null;
                }
            }
            
            catch ( InterruptedException | IOException e )
            {
                ApplicationUtilities.logException( e );
            }
        }
    }

}
