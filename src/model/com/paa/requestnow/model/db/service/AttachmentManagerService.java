package com.paa.requestnow.model.db.service;

import com.paa.requestnow.model.data.Attachment;
import com.paa.requestnow.model.db.transactions.AttachmentManagerTransactions;

/**
 * @author artur
 */
public class AttachmentManagerService
    extends 
        Manager<Attachment , AttachmentManagerTransactions>
        
{
    private static AttachmentManagerTransactions transactions;
    private static AttachmentManagerService service;
    
    public static AttachmentManagerService getInstance()
    {
        if( service == null )
        {
            service = new AttachmentManagerService();
        }
        
        return  service;
    }
    
    private AttachmentManagerService()
    {
        transactions = new AttachmentManagerTransactions();
    }
}
