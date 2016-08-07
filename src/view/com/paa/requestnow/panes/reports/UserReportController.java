package com.paa.requestnow.panes.reports;

import com.paa.requestnow.control.reports.UserReport;
import com.paa.requestnow.model.ApplicationUtilities;
import com.paa.requestnow.model.filter.DefaultFilter;
import com.paa.requestnow.model.data.User;
import com.paa.requestnow.model.filter.UserFilter;
import com.paa.requestnow.view.editor.FilterEditor;
import com.paa.requestnow.view.tables.DefaultTable;
import com.paa.requestnow.view.tables.UserTable;
import com.paa.requestnow.view.util.EditorCallback;
import java.io.File;
import javafx.event.Event;

/**
 * @author artur
 */
public class UserReportController
    extends 
        ReportController<User>
{
    @Override
    public void configure() throws Exception 
    {
        new FilterEditor( new EditorCallback<DefaultFilter>( filter ) 
        {
            @Override
            public void handle( Event t )
            {
                filter = (UserFilter)source;
                refresh();
            }
        } ).open();
    }

    @Override
    public void refresh()
    {
        try
        {
            table.setItems(com.paa.requestnow.model.ModuleContext
                                                .getInstance()
                                                .getUserManager()
                                                .get( filter ) );
        }
        catch ( Exception e )
        {
            ApplicationUtilities.logException( e );
        }            
    }

    
    
    @Override
    public void print( File file ) throws Exception 
    {
        UserReport report = new UserReport();
        report.setSource(com.paa.requestnow.model.ModuleContext
                                                .getInstance()
                                                .getUserManager()
                                                .get( filter ) );
        report.generatePDF( file );
    }
    
    
    

    @Override
    public String getReportName() 
    {
        return "Relatório de Usuários";
    }

    @Override
    public DefaultTable<User> getTable() 
    {
        return table;
    }
    
    private UserTable table   = new UserTable();
    private UserFilter filter = new UserFilter();
}
