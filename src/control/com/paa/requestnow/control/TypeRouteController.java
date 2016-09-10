package com.paa.requestnow.control;

import com.paa.requestnow.model.data.Core;
import com.paa.requestnow.model.data.Type;
import com.paa.requestnow.model.data.TypeRoute;

/**
 * @author artur
 */
public class TypeRouteController 
    extends 
        AbstractController<TypeRouteController, Core>
{
    private static TypeRouteController instance;
    
    private TypeRouteController(){}
    
    public static TypeRouteController getInstance()
    {
        if( instance == null )
        {
            instance = new TypeRouteController();
        }
        
        return instance;
    }
    
    @Override
    public String onEdit( Core item) throws Exception 
    {
        if( item == null)
        {
            return "Selecione uma rota para editar";
        }
        
        return null;
    }

    @Override
    public String onDelete( Core item ) throws Exception 
    {
        if( item  == null )
        {
            return "Selecione uma rota para excluir!";
        }
        
        else if( item.getState() == Type.STATE_INACTIVE )
        {
            return "Rota já encontra-se excluida!";
        } 
            
        else if ( com.paa.requestnow.model.ModuleContext.getInstance().getTypeRouteManager().hasDependences( item ) )
        {
             return "Existem campos ou rotas referênciados neste tipo!";
        }
        
        return null;
    }

    @Override
    public String onAdd( Core item ) throws Exception 
    {
        if( item == null)
        {
            return "Selecione um item iserir uma Rota";
        }
        
        if ( ! ( item instanceof TypeRoute ) &&
             ! ( item instanceof Type ) )
        {
             return "Selecione um tipo ou uma rota para iserir uma Rota";
        }
        
        return null;
    }
    
    public TypeRoute createRoute( Object item )
    {
        TypeRoute route = new TypeRoute();
        
        if ( item instanceof Type )
        {
            route.setType( ( (Type) item ).getId() );
        }
        
        else if ( item instanceof TypeRoute ) 
        {
             route.setType( ( (TypeRoute) item ).getType() );
        }
        
        return route;
    }
}
