package com.paa.requestnow.control.socket;

import java.io.Serializable;

/**
 * @author artur
 */
public class ServerTest 
{
    public static void main( String[] args )
    {
        try
        {
            Server server = new Server();
            server.start();

            while ( true ) 
            {   
                Thread.sleep( 500 );
                server.send( new Serial( "lucas = " + System.currentTimeMillis() ) );
            }
        }
        
        catch ( Exception e )
        {
            e.printStackTrace();
        }
    }   
    
    private static class Serial
        implements 
            Serializable
    {
        private String name;

        public Serial(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() 
        {
            return "Serial{" + "name=" + name + '}';
        }
    }
}
