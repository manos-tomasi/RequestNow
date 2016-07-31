package com.paa.requestnow.model.data;

import com.paa.requestnow.model.ApplicationUtilities;
import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * @author artur
 */
public class Encryption 
{
    private static MessageDigest messageDigest = null; 

    public static String hash( String value )
    { 
        try
        {
            if ( value == null )
            {
                return null;
            }

            messageDigest = MessageDigest.getInstance( "SHA1" );
            System.out.println("<" + String.format( "%040x", new BigInteger( messageDigest.digest( value.getBytes() ) ).abs() ) + ">");
            return "<" + String.format( "%040x", new BigInteger( messageDigest.digest( value.getBytes() ) ).abs() ) + ">";
        }
        catch ( Exception e )
        {
            ApplicationUtilities.logException( e );
        }
        
        return value;
    }
}
