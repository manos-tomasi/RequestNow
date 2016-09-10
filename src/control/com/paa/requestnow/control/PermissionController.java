package com.paa.requestnow.control;

import com.paa.requestnow.model.data.Role;
import com.paa.requestnow.model.data.User;
import java.util.List;

/**
 * @author artur
 */
public class PermissionController 
    extends 
        AbstractController<PermissionController, Role>
{
    private static PermissionController instance;
    
    private PermissionController(){}
    
    public static PermissionController getInstance()
    {
        if( instance == null )
        {
            instance = new PermissionController();
        }
        
        return instance;
    }
    
    @Override  
    public String onDelete( Role role ) throws Exception
    {
        if( role != null && role.getState() == Role.STATE_INACTIVE )
        {
            return "Grupo já encontra-se excluido!";
        }

        else if( role == null )
        {
            return "Selecione um grupo para excluir!";
        }
        
        List<User> users = com.paa.requestnow.model.ModuleContext.getInstance().getUserManager().getUsersByRole( role.getId() );
        
        if ( ! users.isEmpty() )
        {
             return "Você nã pode apagar esse grupo pois existem usuários vinculados: \n" + users.toString().replaceAll( "\\[|\\]" , "" );
        }
        
        return null;
    }
    
    @Override
    public String onEdit( Role role )
    {
        if( role == null)
        {
            return "Selecione um Grupo para editar";
        }
        
        return null;
    }

    @Override
    public String onAdd( Role item ) throws Exception 
    {
        return null;
    }
    
}
