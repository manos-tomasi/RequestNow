package com.paa.requestnow.model.db.transactions;

import com.paa.requestnow.model.data.Attachment;
import com.paa.requestnow.model.db.Database;
import com.paa.requestnow.model.db.Schema.Attachments;
import com.paa.requestnow.model.filter.DefaultFilter;
import com.paa.requestnow.view.util.FileUtilities;
import java.util.List;

/**
 * @author artur
 */
public class AttachmentManagerTransactions 
        extends 
            ManagerTransaction<Attachment>
{
    public void add( Database db,  Attachment attachment ) throws Exception
    {
        Attachments A = Attachments.table;
        
        String sql = " insert into " + A.name +
                     "( " +
                     A.columns.NAME + ", " +
                     A.columns.INFO + ", " +
                     A.columns.URL  + ", " +
                     A.columns.REF_POSTING + 
                     " ) values ( " +
                     db.quote( attachment.getName() ) + ", " +
                     db.quote( attachment.getInfo() ) + ", " +
                     db.quote( attachment.getUrl()  ) + ", " +
                     attachment.getPosting()          +
                     " ) ";
        
        db.executeCommand( sql );
    }   

    public void update( Database db,  Attachment attachment ) throws Exception
    {
        Attachments A = Attachments.table;
        
        String sql = " update " + A.name    + " set " +
                     A.columns.NAME         + " = " + db.quote( attachment.getName() ) + ", " +
                     A.columns.INFO         + " = " + db.quote( attachment.getInfo() ) + ", " +
                     A.columns.URL          + " = " + db.quote( attachment.getUrl()  ) + ", " +
                     A.columns.REF_POSTING  + " = " + attachment.getPosting()          + 
                     " where " +
                     A.columns.ID   + " = " + attachment.getId();
        
        db.executeCommand( sql );
    }
    
    public void delete( Database db,  Attachment attachment ) throws Exception
    {
        Attachments A = Attachments.table;
        
        String sql = " delete from " + A.name +
                     " where " +
                     A.columns.ID   + " = " + attachment.getId();
        
        db.executeCommand( sql );
        
        FileUtilities.deleteFile( attachment.getUrl() );
    }
    
    public Attachment get( Database db,  int attachmentId ) throws Exception
    {
        Attachments A = Attachments.table;
        
        String sql = A.select + 
                     " where " +
                     A.columns.ID   + " = " + attachmentId;
        
        return db.fetchOne( sql, A.fetcher );
    }
    
    public Attachment get( Database db, String name ) throws Exception
    {
        Attachments A = Attachments.table;
       
        String sql = A.select + 
                     " where " +
                     db.upper( A.columns.NAME  ) + " like " + db.upper( db.quote( name ) ) +
                     " or " +
                     db.upper( A.columns.INFO  ) + " like " + db.upper( db.quote( name ) );
       
        return db.fetchOne( sql, A.fetcher );
    }
    
    public List<Attachment> get( Database db ) throws Exception
    {
        Attachments A = Attachments.table;
       
        return db.fetchAll( A.select, A.fetcher );
    }

    @Override
    public List get(Database db, DefaultFilter filter) throws Exception 
    {
        throw new Exception("Not implemented");
    }
}