package com.paa.requestnow.model.db.service;

import com.paa.requestnow.model.data.RequestRoute;
import com.paa.requestnow.model.db.transactions.RequestRouteManagerTransaction;

/**
 *
 * @author lucas
 */
public class RequestRouteManagerService 
        extends 
            Manager<RequestRoute, RequestRouteManagerTransaction>
{
    private static RequestRouteManagerService service;

    public static RequestRouteManagerService getInstance()
    {
        if( service == null )
        {
            service = new RequestRouteManagerService();
        }

        return  service;
    }

    private RequestRouteManagerService()
    {
        transactions = new RequestRouteManagerTransaction();
    }
}
