package com.paa.requestnow.control.socket;

import com.paa.requestnow.view.util.Prompts;
import java.io.Serializable;

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
                Prompts.alert( data.toString() );
            }
        };
        
        client.run();
    }
}
