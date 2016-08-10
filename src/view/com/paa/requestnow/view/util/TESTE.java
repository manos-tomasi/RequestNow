package com.paa.requestnow.view.util;

import com.paa.requestnow.model.data.Sector;
import com.paa.requestnow.model.data.TypeRoute;
import com.paa.requestnow.view.editor.TypeRouteEditor;
import javafx.application.Application;
import javafx.event.Event;
import javafx.stage.Stage;

public class TESTE
        extends 
        Application
{

    @Override
    public void start(Stage stage) throws Exception 
    {
        new TypeRouteEditor( new EditorCallback<TypeRoute>(new TypeRoute() ) {
            @Override
            public void handle(Event t) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        } ).open();
    }
    
    public static void main(String[] args) throws Exception{
        launch(args);
    }
     
}