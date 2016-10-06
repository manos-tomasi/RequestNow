package com.paa.requestnow.control.socket;

import com.paa.requestnow.model.ApplicationUtilities;
import com.paa.requestnow.model.ConfigurationManager;
import java.io.IOException;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @author artur
 */
public class Server
    extends
        Thread
{
    private Serializable source;
    
    private static final String HOST = ConfigurationManager.getInstance().getProperty( "server.host", "224.0.0.2" );
    private static final Integer PORT = Integer.parseInt( ConfigurationManager.getInstance().getProperty( "server.port", "5555" ) );

    public Server() 
    {
        setDaemon(true);
    }

    public void send( Serializable source ) 
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
                    
                    DatagramPacket pkg = new DatagramPacket( b, b.length, addr, PORT );
                    
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
