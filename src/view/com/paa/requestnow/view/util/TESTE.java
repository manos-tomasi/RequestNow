package com.paa.requestnow.view.util;

import com.paa.requestnow.control.mail.MailManager;

public class TESTE {

    public static void main(String[] args) throws Exception 
    {
        MailManager mm = MailManager.getInstance();
        
        mm.setSubject( "TESTTTTTTT  aartt" );
        mm.sendTo( "tomasi.artur@gmail.com" );
    }
}
