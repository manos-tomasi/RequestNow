package com.paa.requestnow.control.socket;

import java.io.Serializable;

/**
 * @author artur
 */
public class ClientTest 
{
    public static void main(String[] args) 
    {
        try
        {
            Client client = new Client() 
            {
                @Override
                public void onRecive( Object data ) throws Exception 
                {
                    System.out.println( data );
                }
            };
            
            client.start();
            
            while ( true )
            {
                Thread.sleep( 1000 );
                client.send( new Serial( "oiiiiii" ) );
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
