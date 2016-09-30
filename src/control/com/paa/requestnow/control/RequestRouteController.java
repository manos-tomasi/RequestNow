package com.paa.requestnow.control;

import com.paa.requestnow.model.data.Lock;
import com.paa.requestnow.model.data.Request;
import com.paa.requestnow.model.data.RequestRoute;
import com.paa.requestnow.model.data.User;
import java.sql.Timestamp;

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
        
    }
    
    public String onDispatch( RequestRoute requestRoute ) throws Exception
    {
        String message = "";
        
        if( requestRoute == null )
        {
            message = "Selecione uma rota para despachar";
        }
        else
        {
            dispatch( requestRoute );
        }
        
        return message;
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
