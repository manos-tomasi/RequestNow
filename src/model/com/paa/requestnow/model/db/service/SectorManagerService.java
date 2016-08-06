package com.paa.requestnow.model.db.service;

import com.paa.requestnow.model.data.DefaultFilter;
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
    public void deleteValue(Sector value) throws Exception 
    {
        Database db = Database.getInstance();
        try
        {
            transactions.deleteSector(db, value);
        }
        
        finally
        {
            db.release();
        }    
    }

    @Override
    public void updateValue(Sector value) throws Exception 
    {
        Database db = Database.getInstance();
        try
        {
            transactions.updateSector(db, value);
        }
        
        finally
        {
            db.release();
        }    
    }

    @Override
    public Sector getValue(int id) throws Exception 
    {
        Database db = Database.getInstance();
     
        Sector sector;
        try
        {
            sector = transactions.getSector(db, id);
        }
        
        finally
        {
            db.release();
        }
        
        return sector;
    }

    @Override
    public List<Sector> getValues() throws Exception 
    {
        Database db = Database.getInstance();
     
        List<Sector> sectors;
        try
        {
            sectors = transactions.getSectors( db );
        }
        
        finally
        {
            db.release();
        }
        
        return sectors;
    }

    @Override
    public List<String> isUnique(Sector value) throws Exception 
    {
        Database db = Database.getInstance();
     
        List<String> errs;
        try
        {
            errs = transactions.getSectors( db );
        }
        
        finally
        {
            db.release();
        }
        
        return errs;
    }

    @Override
    public List<Sector> getValues(DefaultFilter filter) throws Exception 
    {
        Database db = Database.getInstance();
     
        List<Sector> sectors;
        try
        {
            sectors = transactions.getSectors( db , filter );
        }
        
        finally
        {
            db.release();
        }
        
        return sectors;
    }
}
