package com.paa.requestnow.control.socket;

/**
 * @author artur
 */
public class ClientTest 
{
    public static void main(String[] args) 
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
    }
}
