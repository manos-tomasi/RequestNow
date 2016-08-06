package com.paa.requestnow.model.db.fetcher;

import com.paa.requestnow.model.data.Category;
import java.sql.ResultSet;

/**
 *
 * @author lucas
 */
public class CategoryFetcher 
        implements 
            Fetcher<Category>
{
    
    @Override
    public Category fetch(ResultSet resultSet) throws Exception 
    {
        int i = 0;
        
        Category category = new Category();
        
        category.setId( resultSet.getInt(++i) );
        category.setName( resultSet.getString(++i) );
        category.setState( resultSet.getInt(++i) );
        category.setInfo(resultSet.getString(++i) );
        
        return category;
    }
    
}
