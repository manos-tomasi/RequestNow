package com.paa.requestnow.control.util;

import com.paa.requestnow.model.ApplicationUtilities;
import com.paa.requestnow.model.ResourceLocator;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * @author artur
 */
public class MediaPlayer 
{
    private static void play( File file )
    {
        play( file , 0 );
    }
    
    private static void play( File file , int count )
    {
        new Thread( new Runnable() 
        {
            @Override
            public void run() 
            {
                try 
                {
                    Clip clip = AudioSystem.getClip();
                    
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream( file );

                    clip.open( inputStream );

                    clip.loop(count);
                    clip.start(); 

                    do
                    {
                        Thread.sleep( 90 );
                    }
                    while ( clip.isRunning() );
                }
                
                catch ( LineUnavailableException | UnsupportedAudioFileException | IOException | InterruptedException e )
                {
                    ApplicationUtilities.logException( e );
                }
          }
        } ).start();
        
    }
    
    public static void wellcome() throws Exception
    {
        play( new File( new URI( ResourceLocator.getInstance().getSondsResource( "alert.aiff" ) ) ) );
    }
    
    public static void alert() throws Exception
    {
        play( new File( new URI( ResourceLocator.getInstance().getSondsResource( "chewbacca.aiff" ) ) ) , 1 );        
    }
    
    public static void error() throws Exception
    {
        play( new File( new URI( ResourceLocator.getInstance().getSondsResource( "nooo.aiff" ) ) ) );        
    }
    
    public static void loginError() throws Exception
    {
        play( new File( new URI( ResourceLocator.getInstance().getSondsResource( "gandalf.aiff" ) ) ) );        
    }
}
