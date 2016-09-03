package com.paa.requestnow.control;

import com.paa.requestnow.model.data.Category;

/**
 * @author artur
 */
public class CategoryController 
    extends 
        PermissionController<CategoryController>
{
    private static CategoryController instance;
    
    private CategoryController(){}
    
    public static CategoryController getInstance()
    {
        if( instance == null )
        {
            instance = new CategoryController();
        }
        
        return instance;
    }
    
    public String canDelete( Category category )
    {
        try
        {
            if( category != null && category.getState() == Category.STATE_INACTIVE )
            {
                return "Categoria já encontra-se excluida!";
            }

            else if( category == null )
            {
                return "Selecione uma categoria para excluir!";
            }

            else if ( com.paa.requestnow.model.ModuleContext.getInstance().getCategoryManager().hasDependences( category ) )
            {
                 return "Existem tipos de requisição referênciados nesta categoria!";
            }
        }
        
        catch ( Exception e )
        {
            logException( e );
        }
        
        return null;
    }
    
    public String canEdit( Category category )
    {
        if( category == null)
        {
            return "Selecione uma categoria para editar";
        }
        return null;
    }
}
