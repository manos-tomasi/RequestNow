package com.paa.requestnow.control.socket;

import com.paa.requestnow.model.data.RequestRoute;
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
                    if( data instanceof RequestRoute )
                    {
                        RequestRoute r = (RequestRoute) data;
                        if( r.getUser() != 6 )
                        {
                            System.out.println( ((RequestRoute)data).getUser());
                        }
                    }
                }
            };
            
            client.start();
            
            while ( true )
            {
                Thread.sleep( 1000 );  
                RequestRoute r = new RequestRoute();
                r.setUser(5);
                client.send( r );
                
                RequestRoute r3 = new RequestRoute();
                r3.setUser(2);
                client.send( r3 );
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
