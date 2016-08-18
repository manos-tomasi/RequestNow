package com.paa.requestnow.control;

import com.paa.requestnow.model.ApplicationUtilities;
import com.paa.requestnow.model.data.Field;
import com.paa.requestnow.model.data.Request;
import com.paa.requestnow.model.data.RequestRoute;
import com.paa.requestnow.model.data.TypeRoute;
import com.paa.requestnow.model.data.ValueRequest;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 *
 * @author lucas
 */
public class RequestController 
{
    
    private static RequestController controller;
    
    public static RequestController getInstance()
    {
        if( controller == null )
        {
            controller = new RequestController();
        }
        
        return controller;
    }
    
    public void save( Properties  properties, Request request ) throws Exception
    {
        //salva a requisição
        request.setStart( new Timestamp( System.currentTimeMillis() ) );
        request.setState( Request.IN_PROGRESS );
        request.setUser( ApplicationUtilities.getInstance().getActiveUser().getId() );
        
        com.paa.requestnow.model.ModuleContext.getInstance().getRequestManager().add( request );
        
        //salvar os valores dos campos
        for ( Map.Entry<Object, Object> entry : properties.entrySet() )  
        {
            Field  field = (Field)entry.getKey();
            Object value = entry.getValue();
            
            ValueRequest valueRequest = new ValueRequest();
            valueRequest.setField( field.getId() );
            valueRequest.setRequest( request.getId() );
            valueRequest.setValue( value.toString() );
            
            com.paa.requestnow.model.ModuleContext.getInstance().getValueRequestManager().add( valueRequest );
        }
        
        //salvar rotas
        List<TypeRoute> routes = com.paa.requestnow.model.ModuleContext.getInstance().getTypeRouteManager().getByType( request.getType() );
        
        boolean hasPassed = false; // coloca data e situação diferentes na primeira rota
        
        for ( TypeRoute route : routes ) 
        {
            
            RequestRoute requestRoute = new RequestRoute();
            
            requestRoute.setIn( (!hasPassed)? new Timestamp( System.currentTimeMillis() ) : null );
            requestRoute.setRequest( request.getId() );
            requestRoute.setTypeRoute( route.getId() );
            requestRoute.setSequence( route.getSequence() );
            
            
            requestRoute.setState( (!hasPassed)? RequestRoute.STOPED : RequestRoute.WAINTING );
            
            com.paa.requestnow.model.ModuleContext.getInstance().getRequestRouteManager().add( requestRoute );
            
            hasPassed = true;
        }
    }
    
    public void cancel( Request request ) throws Exception
    {
        if( request.getState() == Request.IN_PROGRESS )
        {
            List<RequestRoute> routes = com.paa.requestnow.model.ModuleContext.getInstance().getRequestRouteManager().getByRequest( request.getId() );

            for ( RequestRoute route : routes ) 
            {
                if( route.getState() == RequestRoute.WAINTING || route.getState() == RequestRoute.STOPED )
                {
                    route.setUser( ApplicationUtilities.getInstance().getActiveUser().getId() );
                    route.setState( RequestRoute.CANCELED );
                    route.setOut( new Timestamp( System.currentTimeMillis() ) );

                    com.paa.requestnow.model.ModuleContext.getInstance().getRequestRouteManager().update( route );
                }
            }

            request.setEnd( new Timestamp( System.currentTimeMillis() ) );
            request.setState( Request.CANCELED );

            com.paa.requestnow.model.ModuleContext.getInstance().getRequestManager().update( request );
        }
        else
        {
            throw new Exception( "Está requisição já está finalizada!" );
        }
    }
    
    public List<Field> getFields( int id ) throws Exception
    {
        return com.paa.requestnow.model.ModuleContext.getInstance().getFieldManager().getFieldsType( id );
    }
}