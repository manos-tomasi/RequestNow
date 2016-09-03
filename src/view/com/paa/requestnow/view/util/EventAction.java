package com.paa.requestnow.view.util;

import com.paa.requestnow.model.ApplicationUtilities;
import javafx.event.Event;
import javafx.event.EventHandler;

/**
 *
 * @author lucas
 */
public abstract class EventAction 
        implements 
            EventHandler<Event>
{
    @Override
    public void handle(Event t)
    {
        try 
        {
            onEvent(t);
        } 
        catch (Exception e)
        {
            ApplicationUtilities.logException(e);
        }
    }
    
    public abstract void onEvent( Event e ) throws Exception;
}
