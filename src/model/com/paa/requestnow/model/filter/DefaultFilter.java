package com.paa.requestnow.model.filter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author artur
 */
public abstract class DefaultFilter 
{
    private List<FilterItem> items = new ArrayList();
    private List<FilterItem> allowItems = new ArrayList();
    
    private HashMap<Integer, List<Object>> conditions = new HashMap();

    public DefaultFilter() 
    {
        createComponents( items );
    }
    
    
    public void addCondition( int id, Object value )
    {
        List values = conditions.get( id );
        
        if( values == null )
            values = new ArrayList();
        
        values.add( value );
        
        conditions.put( id, values );
    }
    
    
    public void clearConditions()
    {
        conditions.clear();
    }
        
    
    
    public HashMap<Integer, List<Object>> getConditions()
    {
        return conditions;
    }
    
    
    
    public void setBlockedFilters( Integer... ids  )
    {
        allowItems = new ArrayList();
        
        items.forEach( it ->
        {
            boolean allow = true;
            
            for ( int id : ids )
            {
                allow &= it.getId() != id;
            }
            
            it.setDisabled( ! allow );
            
            if ( allow )
                allowItems.add( it );
        } );
    }
    
    
    public List<FilterItem> getAllowFilters()
    {
        return allowItems;
    }
    

    
    public List<FilterItem> getComponents()
    {
        return items;
    }
    
    
    protected abstract void createComponents( List<FilterItem> filters );
    
    public class FilterItem
    {
        private int id;
        private String name;
        private String component;
        private boolean disabled;

        public FilterItem( int id, String name, String component )
        {
            this( id, name, component, false );
        }
        
        public FilterItem( int id, String name, String component, boolean disabled )
        {
            this.id = id;
            this.name     = name;
            this.component = component;
        }

        public int getId() 
        {
            return id;
        }

        public void setId( int id )
        {
            this.id = id;
        }
        
        public String getName() 
        {
            return name;
        }

        public void setName( String name )
        {
            this.name = name;
        }

        public String getComponent() 
        {
            return component;
        }

        public void setComponent( String component )
        {
            this.component = component;
        }

        public boolean isDisabled() {
            return disabled;
        }

        public void setDisabled(boolean disabled) {
            this.disabled = disabled;
        }

        
        @Override
        public boolean equals(Object obj)
        {
            if ( obj instanceof FilterItem )
            {
                return ( (FilterItem) obj ).getId() == this.id;
            }
            
            return false;
        }

        @Override
        public int hashCode() 
        {
            int hash = 1;
            
            hash = 27 * hash + id;
            
            return hash;
        }
        
        @Override
        public String toString() 
        {
            return name;
        }
    }
}
