package com.paa.requestnow.panes;

import com.paa.requestnow.view.util.HtmlEditorField;
import javafx.scene.control.Tab;

/**
 *
 * @author lucas
 */
public class TabDispatch 
        extends 
            Tab
{

    public TabDispatch() 
    {
        initComponent();
    }
    
    public void setSize( double x, double y )
    {
        info.setMinSize( y , x - 100 );
        info.requestLayout();
    }
    
    public String obtainInfo()
    {
        return info.getHtmlText();
    }
     
    private void initComponent()
    {
        setText( "Despacho" );
        
        setContent(info);
        setClosable(false);
    }
    
    private HtmlEditorField info          = new HtmlEditorField();
}