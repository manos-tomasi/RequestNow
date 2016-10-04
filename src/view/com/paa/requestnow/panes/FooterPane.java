package com.paa.requestnow.panes;

import com.paa.requestnow.control.webservices.WebService;
import com.paa.requestnow.model.ApplicationUtilities;
import com.paa.requestnow.view.util.ContextMenuItem;
import com.paa.requestnow.view.util.FileUtilities;
import com.paa.requestnow.view.util.Prompts;
import java.sql.Date;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Side;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import org.json.JSONObject;

/**
 * @author artur
 */
public class FooterPane 
    extends 
        HBox
{
    public static class Events 
    {
        public static final EventType ON_LOGOUT = new EventType( "onLogout" );
    }
        
    public FooterPane() 
    {
        initComponents();
    }
    
    public void resize( BorderPane pane )
    {
        setLayoutX( pane.getWidth() );
        setSpacing( pane.getWidth() - btSystem.getWidth() - lbUser.getWidth() );
    }
    
    private void logout()
    {
        fireEvent( new Event( Events.ON_LOGOUT ) );
    }
    
    private void getQuotation()
    {
        try
        {
            JSONObject cotacao = WebService.get( "http://api.promasters.net.br/cotacao/v1/valores" ).getJSONObject("valores");
            
            SimpleDateFormat df = new SimpleDateFormat( "dd/MM/yyyy HH:mm:ss" );
            String title   = "Cotação - "   + df.format( new Date( System.currentTimeMillis() ) );
            
            NumberFormat.getCurrencyInstance().setMaximumFractionDigits(3);
            NumberFormat.getCurrencyInstance().setMinimumFractionDigits(3);
            
            String usd = NumberFormat.getCurrencyInstance().format( cotacao.getJSONObject("USD").getDouble("valor" ) );
            String btc = NumberFormat.getCurrencyInstance().format( cotacao.getJSONObject("BTC").getDouble("valor" ) );
            String eur = NumberFormat.getCurrencyInstance().format( cotacao.getJSONObject("EUR").getDouble("valor" ) );
            
            String message = "Dólar:    \n" +
                             "   - Valor: " + usd + "\n" +
                             "   - Fonte: " + cotacao.getJSONObject("USD").get( "fonte" ) + "\n"+ 
                             "Bitcoin:  \n" +
                             "   - Valor: " + btc + "\n" + 
                             "   - Fonte: " + cotacao.getJSONObject("BTC").get( "fonte" ) + "\n"+
                             "\bEuro\b:     \n" +
                             "   - Valor: " + eur + "\n" +
                             "   - Fonte: " + cotacao.getJSONObject("EUR").get( "fonte" ) + "\n";
            
            Prompts.info( title , message );
        }
        catch( Exception e )
        {
            ApplicationUtilities.logException( e );
        }
        
    }
    
    private void backup()
    {
        Prompts.process( "Gerando backup...", new Task<Void>() 
        {
            @Override
            protected Void call() throws Exception 
            {
                FileUtilities.generateBackup();
                return null;
            }
        } );
    }
    
    private void initComponents()
    {
        lbUser.setCache( true );
        lbUser.setText( "   " + ApplicationUtilities.getInstance().getActiveUserName() );
        lbUser.setStyle( "-fx-padding: 4 0 0 0;" + 
                         "-fx-font-size: 14pt;" +
                         "-fx-font-family: \"Helvetica, Verdana, sans-serif\";" +
                         "-fx-text-fill:" + ApplicationUtilities.getColor());
        
        btSystem.setCache( true );
        btSystem.setCursor( Cursor.HAND );
        btSystem.setStyle( "-fx-background-color: transparent; " + 
                           "-fx-font-size: 10pt;" +
                           "-fx-border-color:" + ApplicationUtilities.getColor() +
                           "-fx-text-fill:" + ApplicationUtilities.getColor() +
                           "-fx-font-family: \"Helvetica, Verdana, sans-serif\";" +
                           "-fx-border-width: 1; " + 
                           "-fx-border-radius: 10;" );
       
        btSystem.setText( "Sistema" );
        
        setStyle( "-fx-border-color: " + ApplicationUtilities.getColor() + 
                  "-fx-border-width: 2 0 0 0;" + 
                  "-fx-padding: 3 0 4 0; " + 
                  ApplicationUtilities.getBackground2() +
                  "-fx-text-fill:" + ApplicationUtilities.getColor() );
        
        menu.setStyle( ApplicationUtilities.getBackground2() );
        getChildren().addAll( lbUser, btSystem );
        
        btSystem.setContextMenu( menu );
        
        btSystem.setOnAction( new EventHandler<ActionEvent>()
        {
            @Override
            public void handle( ActionEvent t )
            {
                menu.show( btSystem, Side.TOP, 0, 0 );
            }
        } );
    }
    
    private Button btSystem = new Button();
    private Label lbUser = new Label();
    
    private ContextMenu menu = new ContextMenu
    ( 
        new ContextMenuItem( "Backup", "backup.png", new EventHandler<ActionEvent>() 
        {
            @Override
            public void handle( ActionEvent t ) 
            {
                backup();
            }
        } ),
                    
        new ContextMenuItem( "Logout", "logout.png", new EventHandler<ActionEvent>() 
        {
            @Override
            public void handle( ActionEvent t) 
            {
                logout();
            }
        } ),
        
        new ContextMenuItem( "Cotação", "currency.png", new EventHandler<ActionEvent>() 
        {
            @Override
            public void handle(ActionEvent t) 
            {
                getQuotation();
            }
    })
    );
}
