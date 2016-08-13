package com.paa.requestnow.view.util;

import javafx.scene.web.HTMLEditor;

/**
 *
 * @author lucas
 */
public class HtmlEditorField 
        extends 
            HTMLEditor
{

    public HtmlEditorField() 
    {
        setMaxHeight(200);
    }
    
    public HtmlEditorField( double height )
    {
        setMaxHeight(height);
    }
}
