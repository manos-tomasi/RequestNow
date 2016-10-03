package com.paa.requestnow.control.socket;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * @author artur
 */
public class Serializer
{
    public static byte[] serialize( Object data ) throws IOException
    {
        if ( data == null )
        {
            throw new IllegalArgumentException( "Data Object cannot be null" );
        }
        
        if ( ! ( data instanceof Serializable ) )
        {
            throw new InvalidClassException( "Data Object not is serializable" );
        }
        
        ByteArrayOutputStream byteOutput = new ByteArrayOutputStream( 256 );
        
        ObjectOutputStream objectOutput = new ObjectOutputStream( byteOutput );
        
        objectOutput.writeObject( data );
        
        objectOutput.close();
        byteOutput.close();
        
        byte serialize [] = byteOutput.toByteArray();
        
        return serialize;
    }
    
    public static Object deserialize( byte[] data ) throws IOException, ClassNotFoundException 
    {
        ByteArrayInputStream byteInput = new ByteArrayInputStream( data );
        
        ObjectInputStream objectInput    = new ObjectInputStream(byteInput);
        
        Object result = objectInput.readObject();
        
        objectInput.close();
        byteInput.close();
        
        return result;
    }
}
