package com.paa.requestnow.panes.entries;

import com.paa.requestnow.model.data.Core;
import com.paa.requestnow.view.tables.DefaultTable;

/**
 * @author artur
 * @param <T>
 */
public abstract class EntrieController <T extends Core>
{
    public T getSelectedItem()
    {
        return getTable().getSelectedItem();
    }
    
    public abstract void addItem() throws Exception;
    public abstract void editItem( T item ) throws Exception;
    public abstract void filterItem() throws Exception;
    public abstract void deleteItem( T item ) throws Exception;
    public abstract void refresh();
    
    public abstract String getEntrieName();

    public abstract DefaultTable<T> getTable();
}
