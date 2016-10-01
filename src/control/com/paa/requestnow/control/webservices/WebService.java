package com.paa.requestnow.control.webservices;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

/**
 * 
 * @author lucas
 */

public class WebService
{      
    public static JSONObject get( String path ) throws Exception
    {
        URL url    = new URL( path );
	HttpURLConnection conn   = (HttpURLConnection) url.openConnection();
        conn.setRequestProperty("Accept", "application/json" );
        conn.setRequestMethod( "GET" );
        
        if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "+ conn.getResponseCode());
        }
        
        BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

        String output,result = "";
        
        while ((output = br.readLine()) != null) {result += output;}
        
        JSONObject object  = new JSONObject(result);
        
        conn.disconnect();
        
        return object;
    }
}