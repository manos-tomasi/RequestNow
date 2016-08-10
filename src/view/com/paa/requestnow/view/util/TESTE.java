package com.paa.requestnow.view.util;

import com.paa.requestnow.model.data.Request;
import com.paa.requestnow.view.editor.RequestEditor;
import javafx.application.Application;
import javafx.event.Event;
import javafx.stage.Stage;

public class TESTE extends Application{

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        new RequestEditor(new EditorCallback<Request>(new Request()) {
            @Override
            public void handle(Event t) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        }).open();
    }

}
