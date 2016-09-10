package com.paa.requestnow.model.data;

/**
 * @author artur
 */
public class Field
    extends 
        Core<Field>
{
    private String label;
    private boolean required;
    private int type;
    private int typeRequest;
    private int sequence;
    
    public final static int TYPE_TEXT        = 0;
    public final static int TYPE_CHOICE      = 1;
    public final static int TYPE_NUMBER      = 2;
    public final static int TYPE_DATE        = 3;
    public final static int TYPE_FILE        = 4;
    public final static int TYPE_SIMPLE_TEXT = 5;

    
    public final static String TYPES [] =
    {
        "Texto", "Lista", "Número", "Data", "Arquivos", "Texto Simples"
    };
    
    public Field() 
    {
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    
    public String getLabel()
    {
        return label;
    }

    public void setLabel( String label )
    {
        this.label = label;
    }

    public boolean isRequired() 
    {
        return required;
    }

    public void setRequired( boolean required )
    {
        this.required = required;
    }

    public int getType() 
    {
        return type;
    }

    public void setType( int type )
    {
        this.type = type;
    }

    public int getTypeRequest() 
    {
        return typeRequest;
    }

    public void setTypeRequest( int typeRequest )
    {
        this.typeRequest = typeRequest;
    }
    
    public String getHandler()
    {
        switch ( type )
        {
            case Field.TYPE_CHOICE:
                return "com.paa.requestnow.view.selectors.ItemSelector";
            
            case Field.TYPE_NUMBER:
                return "com.paa.requestnow.view.util.NumberTextField";
            
            case Field.TYPE_TEXT:
                return "com.paa.requestnow.view.util.HtmlEditorField";
            
            case Field.TYPE_FILE:
                return "com.paa.requestnow.view.selectors.FileSelector";
            
            case Field.TYPE_DATE:
                return "com.paa.requestnow.view.util.DateField";
            
            case Field.TYPE_SIMPLE_TEXT:
                return "com.paa.requestnow.view.util.MaskTextField";
        }
        
        throw new IllegalArgumentException( "Tipo invĺálido" );
    }
    
    
    @Override
    public String toString() 
    {
        return label;
    }
}
