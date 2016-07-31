package com.paa.requestnow.model.db;

import com.paa.requestnow.model.db.fetcher.AttachmentFetcher;
import com.paa.requestnow.model.db.fetcher.UserFetcher;
/**
 * @author artur
 */
public class Schema 
{    
    public static class Users
    {
        public static Users alias( String alias )
        {
            return new Users( alias );
        }
        
        public  class Columns
        {
            public String ID;
            public String NAME;
            public String PHONE;
            public String CPF;
            public String BIRTH_DATE;
            public String RG;
            public String MAIL;
            public String GENDER;
            public String STATE;
            public String PASSWORD;
            public String LOGIN;
            public String ROLE;
            
            public Columns( String alias ) 
            {
                ID           = alias + "id";
                NAME         = alias + "name";
                PHONE        = alias + "phone";
                CPF          = alias + "cpf";
                BIRTH_DATE   = alias + "birth_date";
                RG           = alias + "rg";
                MAIL         = alias + "mail";
                GENDER       = alias + "gender";
                STATE        = alias + "state";
                PASSWORD     = alias + "password";
                LOGIN        = alias + "login";
                ROLE         = alias + "role";
            }
            
            @Override
            public String toString() 
            {
                return ID           + ", " +
                       NAME         + ", " +
                       PHONE        + ", " +
                       CPF          + ", " +
                       BIRTH_DATE   + ", " +
                       RG           + ", " +
                       MAIL         + ", " +
                       GENDER       + ", " +
                       STATE        + ", " +
                       PASSWORD     + ", " +
                       LOGIN        + ", " +
                       ROLE;
            }
        }
        
        private final String TABLE_NAME =  "users";

        public String name = TABLE_NAME;

        public final String select;
        
        public final UserFetcher fetcher = new UserFetcher();
        
        public static final Users table = new Users( null );
        
        public final Columns columns;

        private Users( String alias ) 
        {
            this.name = alias != null ? TABLE_NAME + " " + alias : TABLE_NAME;
            
            columns = new Columns( alias != null ? alias + "." : "" );
            
            select = "select " + columns + " from " + this.name;
        }
    }
    
    public static class Attachments
    {
        public static Attachments alias( String alias )
        {
            return new Attachments( alias );
        }
        
        public  class Columns
        {
            public String ID;
            public String NAME;
            public String INFO;
            public String URL;
            public String REF_POSTING;
            
            public Columns( String alias ) 
            {
                ID                    = alias + "id";
                NAME                  = alias + "name";
                INFO                  = alias + "info";
                URL                   = alias + "url";
                REF_POSTING           = alias + "ref_posting";
            }
            

            @Override
            public String toString() 
            {
                return ID                    + ", " +
                       NAME                  + ", " +
                       INFO                  + ", " +
                       URL                   + ", " +
                       REF_POSTING;
            }
        }
        
        private final String TABLE_NAME =  "attachments";

        public String name = TABLE_NAME;

        public final String select;
        
        public final AttachmentFetcher fetcher = new AttachmentFetcher();
        
        public static final Attachments table = new Attachments( null );
        
        public final Columns columns;

        private Attachments( String alias ) 
        {
            this.name = alias != null ? TABLE_NAME + " " + alias : TABLE_NAME;
            
            columns = new Columns( alias != null ? alias + "." : "" );
            
            select = "select " + columns + " from " + this.name;
        }
    }
}
