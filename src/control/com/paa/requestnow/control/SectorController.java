package com.paa.requestnow.control;

import com.paa.requestnow.model.data.Sector;

/**
 * @author artur
 */
public class SectorController 
    extends 
        AbstractController<SectorController, Sector>
{
    private static SectorController instance;
    
    private SectorController(){}
    
    public static SectorController getInstance()
    {
        if( instance == null )
        {
            instance = new SectorController();
        }
        
        return instance;
    }
    
    @Override
    public String onDelete( Sector sector ) throws Exception
    {
        if( sector != null && sector.getState() == Sector.STATE_INACTIVE )
        {
            return "Setor já encontra-se excluida!";
        }

        else if( sector == null )
        {
            return "Selecione um setor para excluir!";
        }

        else if ( com.paa.requestnow.model.ModuleContext.getInstance().getSectorManager().hasDependences( sector ) )
        {
             return "Existem setores com referências!";
        }
        
        return null;
    }
    
    @Override
    public String onEdit( Sector user )
    {
        if( user == null)
        {
            return "Selecione um setor para editar";
        }
        
        return null;
    }

    @Override
    public String onAdd( Sector item ) throws Exception 
    {
        return null;
    }
}
