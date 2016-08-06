package com.paa.requestnow.model.data;

/**
 *
 * @author lucas
 */
public class FieldValue
        extends 
            Core<FieldValue>
{
   private int field;
   private String value;

    public FieldValue() {}

    public FieldValue(int field, String value) 
    {
        this.field = field;
        this.value = value;
    }

    public int getField() 
    {
        return field;
    }

    public void setField(int field)
    {
        this.field = field;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(String value) 
    {
        this.value = value;
    }

    @Override
    public String toString() 
    {
        return this.value;
    }    
}
