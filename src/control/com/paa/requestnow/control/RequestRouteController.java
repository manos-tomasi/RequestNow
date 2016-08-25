package com.paa.requestnow.control;

/**
 *
 * @author lucas
 */
public class RequestRouteController
{
    private static RequestRouteController controller;
    
    public static RequestRouteController getInstance()
    {
        if( controller == null )
        {
            controller = new RequestRouteController();
        }
        
        return controller;
    }
}
