package com.paa.requestnow.view.editor;

import com.paa.requestnow.model.data.RequestRoute;
import com.paa.requestnow.view.util.EditorCallback;
import java.util.List;

/*
 * @author lucas
 */
public class DispatchEditor 
        extends 
            AbstractEditor<RequestRoute>
{

    public DispatchEditor( EditorCallback<RequestRoute> callback ) 
    {
        super(callback);
    }
    
    @Override
    protected void validadeInput(List<String> erros) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void obtainInput() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void resize() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void setSource(RequestRoute source) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}