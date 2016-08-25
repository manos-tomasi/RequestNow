package com.paa.requestnow.model;

/**
 * @author artur
 */
public class SecurityManager 
{
    private SecurityManager secMan;
    
    private SecurityManager(){}
    
    public SecurityManager getInstance()
    {
        if ( secMan == null )
        {
            secMan = new SecurityManager();
        }
        
        return secMan;
    }
    
}
