package com.paa.requestnow.control.socket;

import com.paa.requestnow.control.util.Serializer;
import com.paa.requestnow.model.ApplicationUtilities;
import com.paa.requestnow.model.ConfigurationManager;
import java.io.IOException;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 * @author artur
 */
public abstract class Client
    extends 
        Thread
{
    private static final String HOST = ConfigurationManager.getInstance().getProperty( "server.host", "224.0.0.2" );
    private static final Integer PORT = Integer.parseInt( ConfigurationManager.getInstance().getProperty( "server.port", "5555" ) );
    
    public Client()
    {
    }

    
    public void send( Serializable  source ) throws Exception
    {
        if ( source != null ) 
        {
            byte[] b = Serializer.serialize( source );

            InetAddress addr   = InetAddress.getByName( HOST );

            DatagramSocket ds  = new DatagramSocket();

            DatagramPacket pkg = new DatagramPacket( b, b.length, addr, PORT );

            ds.send( pkg );
        }
    }
    
    public abstract void onRecive( Object  data ) throws Exception;
    
    @Override
    public void run() 
    {
        MulticastSocket socket = null;
        
        try 
        {
            // determina endereco e porta
            InetAddress grupo = InetAddress.getByName( HOST );

            // cria multicast socket e se une ao grupo
            socket = new MulticastSocket( PORT );
            
            socket.joinGroup( grupo );
        }
        
        catch ( IOException e )
        {
            ApplicationUtilities.logException( e );
        }
        
        try
        {
            while ( true ) 
            {
                // prepara buffer (vazio)
                byte[] buf = new byte[256];

                // prepara pacote para resposta
                DatagramPacket pacote = new DatagramPacket( buf, buf.length );

                // recebe pacote
                socket.receive( pacote );

                onRecive( Serializer.deserialize( pacote.getData() ) );
            }
        }

        catch ( Exception ex )
        {
            ApplicationUtilities.logException( ex );
        }
            
        // fecha socket
        socket.close();
    }
}
