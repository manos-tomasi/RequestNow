package com.paa.requestnow.model.db.fetcher;

import java.sql.ResultSet;

/**
 * @author artur
 * @param <T>
 */
public interface Fetcher<T> 

{
    public T fetch( ResultSet resultSet ) throws Exception;
}
