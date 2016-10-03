package com.paa.requestnow.control.util;

import com.paa.requestnow.model.ApplicationUtilities;
import com.paa.requestnow.model.ResourceLocator;
import java.io.File;
import java.io.InputStream;
import java.net.URI;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * @author artur
 */
public class MediaPlayer 
{
    public static void alert() throws Exception
    {
        new Thread( new Runnable() 
        {
            @Override
            public void run() 
            {
                try 
                {
                    Clip clip = AudioSystem.getClip();
    
                    File file = new File( new URI( ResourceLocator.getInstance().getImageResource( "alert.aiff" ) ) );
                    
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream( file );

                    clip.open( inputStream );

                    clip.start(); 

                    Thread.sleep( 90 );

                    while ( clip.isRunning() )
                    {
                        Thread.sleep( 90 );
                    }
                }
                
                catch ( Exception e )
                {
                    ApplicationUtilities.logException( e );
                }
          }
        } ).start();
    }
}
