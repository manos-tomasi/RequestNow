package com.paa.requestnow.view.util;

import com.paa.requestnow.model.data.Sector;

public class TESTE
{
    public static void main(String[] args) throws Exception{
        Sector s = new Sector( "Lucas" );
        System.out.println( com.paa.requestnow.model.ModuleContext.getInstance().getSectorManager().getValues() );
    }
     
}