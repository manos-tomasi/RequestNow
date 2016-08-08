package com.paa.requestnow.panes;

import javafx.scene.control.Tab;
import javafx.scene.web.HTMLEditor;

/**
 * @author artur
 */
public class InfoPane 
    extends 
        Tab
{
    public InfoPane() 
    {
        initComponents();
    }
    
    public String getInfo()
    {
        return htmlEditor.getHtmlText();
    }
    
    public void setInfo( String info )
    {
        htmlEditor.setHtmlText( info );
    }
    
    private void initComponents()
    {
        setClosable( false );
        setText( "Informações" );
        setContent( htmlEditor );
    }
    
    private HTMLEditor htmlEditor = new HTMLEditor();
}
