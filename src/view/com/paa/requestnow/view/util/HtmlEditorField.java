package com.paa.requestnow.view.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
    
    public boolean isEmpty()
    {        
        return stripHTMLTags( getHtmlText() ).isEmpty();
    }

    private String stripHTMLTags(String htmlText) 
    {
        Pattern pattern = Pattern.compile("<[^>]*>");

        Matcher matcher = pattern.matcher(htmlText);

        final StringBuffer sb = new StringBuffer(htmlText.length());
        
        while( matcher.find() ) 
        {
            matcher.appendReplacement(sb, " ");
        }
        
        matcher.appendTail(sb);
        
        return sb.toString().replaceAll("&nbsp;", "").trim();
    }
}
