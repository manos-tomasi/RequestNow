package com.paa.requestnow.model.db.service;

import com.paa.requestnow.model.data.TypeRoute;
import com.paa.requestnow.model.db.transactions.TypeRouteManagerTransactions;

/**
 *
 * @author lucas
 */
public class TypeRouteManagerService 
        extends 
            Manager<TypeRoute, TypeRouteManagerTransactions>
{
    private static TypeRouteManagerService service;
   
    public static TypeRouteManagerService getInstance()
    {
        if( service == null )
        {
            service = new TypeRouteManagerService();
        }
        
        return  service;
    }
    
    private TypeRouteManagerService()
    {
        transactions = new TypeRouteManagerTransactions();
    }
}
