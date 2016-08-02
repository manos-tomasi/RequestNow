package com.paa.requestnow.model.db.service;

import com.paa.requestnow.model.data.Sector;
import com.paa.requestnow.model.db.Database;
import com.paa.requestnow.model.db.transactions.SectorManagerTransactions;
import java.util.List;

/**
 *
 * @author lucas
 */
public class SectorManagerService 
        implements 
            Manager<Sector>
{

    private static SectorManagerTransactions transactions;
    private static SectorManagerService service;
    
    public static SectorManagerService getInstance()
    {
        if( service == null )
        {
            service = new SectorManagerService();
        }
        
        return  service;
    }
    
    private SectorManagerService()
    {
        transactions = new SectorManagerTransactions();
    }
    
    @Override
    public void addValue(Sector value) throws Exception 
    {
        Database db = Database.getInstance();
        try
        {
            transactions.addSector(db, value);
        }
        
        finally
        {
            db.release();
        }
    }

    @Override
    public void deleteValue(Sector value) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateValue(Sector value) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Sector getValue(int id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Sector getValue(String value) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Sector> getValues() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Sector> getValues(boolean showInactives) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> isUnique(Sector value) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
