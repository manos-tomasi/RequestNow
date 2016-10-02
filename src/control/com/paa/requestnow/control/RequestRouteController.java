package com.paa.requestnow.control;

import com.paa.requestnow.model.ApplicationUtilities;
import com.paa.requestnow.model.data.Lock;
import com.paa.requestnow.model.data.Option;
import com.paa.requestnow.model.data.Request;
import com.paa.requestnow.model.data.RequestRoute;
import com.paa.requestnow.model.data.User;
import com.paa.requestnow.model.filter.RequestRouteFilter;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author lucas
 */
public class RequestRouteController
        extends 
            AbstractController<RequestController, RequestRoute>
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
    
    public void dispatch( RequestRoute dispatch ) throws Exception
    {
        if( dispatch.getState() == RequestRoute.APPROVED )
        {
            boolean isLastDispatch = com.paa.requestnow.model.ModuleContext.getInstance().getRequestRouteManager().approve(dispatch);

            if( isLastDispatch )
            {
                Request request = com.paa.requestnow.model.ModuleContext.getInstance().getRequestManager().get( dispatch.getRequest() );

                request.setEnd( new Timestamp( System.currentTimeMillis() ) );
                request.setState( Request.APPROVED );
                
                com.paa.requestnow.model.ModuleContext.getInstance().getRequestManager().update( request );
            }
        }
        else
        {
            com.paa.requestnow.model.ModuleContext.getInstance().getRequestRouteManager().disapprove( dispatch );

            Request request = com.paa.requestnow.model.ModuleContext.getInstance().getRequestManager().get( dispatch.getRequest() );

            request.setEnd( new Timestamp( System.currentTimeMillis() ) );
            request.setState( Request.DISAPPROVED );
            
            com.paa.requestnow.model.ModuleContext.getInstance().getRequestManager().update( request );
        }
        
        sendNotification(dispatch);
    }
   
    
    private void sendNotification( RequestRoute requestroute ) throws Exception
    {
        RequestRouteFilter filter = new RequestRouteFilter();
        filter.addCondition(RequestRouteFilter.SEQUENCE, requestroute.getSequence() + 1 );
        filter.addCondition(RequestRouteFilter.STATE, new Option( RequestRoute.STOPED, "Stoped in sector or in user" ) );
        List<RequestRoute> nexts = com.paa.requestnow.model.ModuleContext.getInstance().getRequestRouteManager().get(filter);
        
        if( nexts.size() > 1 )
        {
            throw new IllegalArgumentException( "It must be only one route" );
        }
        
        if( nexts.size() == 1 )
        {
            ApplicationUtilities.getInstance().getServer().send( nexts.get(0) );
        }
    }
    
    public Lock lock( RequestRoute requestRoute, User user ) throws Exception
    {
        Lock lock = new Lock( user.getId(), requestRoute.getId() );
         
        com.paa.requestnow.model.ModuleContext.getInstance().getLockManager().add( lock );
        
        return lock;
    }
    
    public String hasLock( RequestRoute requestRoute, User user ) throws Exception
    {
        Lock lock = com.paa.requestnow.model.ModuleContext
                                        .getInstance()
                                        .getLockManager()
                                        .get( requestRoute.getId() );
        if ( lock != null )
        {
            if ( lock.getUser() != user.getId() )
            {
                return "Item bloqueado por " + com.paa.requestnow.model.ModuleContext
                                                                .getInstance()
                                                                .getUserManager()
                                                                .get( lock.getUser() );
            }
            
            else 
            {
                com.paa.requestnow.model.ModuleContext
                                        .getInstance()
                                        .getLockManager()
                                        .delete( lock );
            }
        }
        
        return null;
    }
    
    public void unLock( Lock lock ) throws Exception
    {
        if ( lock != null )
        {
            com.paa.requestnow.model.ModuleContext.getInstance().getLockManager().delete( lock );
        }
    }
    
    @Override
    public String onEdit(RequestRoute item) throws Exception {throw new UnsupportedOperationException("Not supported yet.");}
    @Override
    public String onDelete(RequestRoute item) throws Exception {throw new UnsupportedOperationException("Not supported yet.");}
    @Override
    public String onAdd(RequestRoute item) throws Exception{throw new UnsupportedOperationException("Not supported yet.");}
}
