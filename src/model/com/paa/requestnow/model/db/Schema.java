package com.paa.requestnow.model.db;

import com.paa.requestnow.model.db.fetcher.*;

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
            public String SECTOR;
            
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
                SECTOR       = alias + "ref_sector";
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
                       ROLE         + ", " +
                       SECTOR;
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
    
    public static class Sectors
    {
        public static Sectors alias( String alias )
        {
            return new Sectors( alias );
        }
        
        public  class Columns
        {
            public String ID;
            public String NAME;
            public String STATE;
            
            public Columns( String alias ) 
            {
                ID           = alias + "id";
                NAME         = alias + "name";
                STATE        = alias + "state";
            }
            
            @Override
            public String toString() 
            {
                return ID           + ", " +
                       NAME         + ", " +
                       STATE;
            }
        }
        
        private final String TABLE_NAME =  "sectors";

        public String name = TABLE_NAME;

        public final String select;
        
        public final SectorFetcher fetcher = new SectorFetcher();
        
        public static final Sectors table = new Sectors( null );
        
        public final Columns columns;

        private Sectors( String alias ) 
        {
            this.name = alias != null ? TABLE_NAME + " " + alias : TABLE_NAME;
            
            columns = new Columns( alias != null ? alias + "." : "" );
            
            select = "select " + columns + " from " + this.name;
        }
    }
    
    public static class Categories
    {
        public static Categories alias( String alias )
        {
            return new Categories( alias );
        }
        
        public class Columns
        {
            public String ID;
            public String NAME;
            public String STATE;
            public String INFO;
            
            public Columns( String alias )
            {
                ID    = alias + "id";
                NAME  = alias + "name";
                STATE = alias + "state";
                INFO  = alias + "info";
            }

            @Override
            public String toString() 
            {
                return ID    + ", " +
                       NAME  + ", " +
                       STATE + ", " +
                       INFO;
            }
        }

        private final String TABLE_NAME =  "categories";

        public String name = TABLE_NAME;

        public final String select;
        
        public final CategoryFetcher fetcher = new CategoryFetcher();
        
        public static final Categories table = new Categories( null );
        
        public final Columns columns;
        
        private Categories( String alias )
        {
            this.name = alias != null ? TABLE_NAME + " " + alias : TABLE_NAME;
            
            columns = new Columns( alias != null ? alias + "." : "" );
            
            select = "select " + columns + " from " + this.name;            
        }
    }
    
    public static class Types
    {
        public static Types alias( String alias )
        {
            return new Types( alias );
        }
        
        public class Columns
        {
            public String ID;
            public String NAME;
            public String STATE;
            public String INFO;
            public String CATEGORY;
            
            public Columns( String alias )
            {
                ID        = alias + "id";
                NAME      = alias + "name";
                STATE     = alias + "state";
                INFO      = alias + "info";
                CATEGORY  = alias + "ref_category";
            }

            @Override
            public String toString() 
            {
                return ID    + ", " +
                       NAME  + ", " +
                       STATE + ", " +
                       INFO  + ", " +
                       CATEGORY;
            }
        }

        private final String TABLE_NAME =  "types";

        public String name = TABLE_NAME;

        public final String select;
        
        public final TypeFetcher fetcher = new TypeFetcher();
        
        public static final Types table = new Types( null );
        
        public final Columns columns;
        
        private Types( String alias )
        {
            this.name = alias != null ? TABLE_NAME + " " + alias : TABLE_NAME;
            
            columns = new Columns( alias != null ? alias + "." : "" );
            
            select = "select " + columns + " from " + this.name;            
        }
    }
    
    public static class Fields
    {
        public static Fields alias( String alias )
        {
            return new Fields( alias );
        }
        
        public class Columns
        {
            public String ID;
            public String LABEL;
            public String REQUIRED;
            public String STATE;
            public String TYPE;
            public String TYPE_REQUEST;
            public String SEQUENCE;
            
            public Columns( String alias )
            {
                ID           = alias + "id";
                LABEL        = alias + "label";
                REQUIRED     = alias + "required";
                STATE        = alias + "state";
                TYPE         = alias + "type";
                TYPE_REQUEST = alias + "ref_type";
                SEQUENCE     = alias + "sequence";
            }

            @Override
            public String toString() 
            {
                return ID           + ", " +
                       LABEL        + ", " +
                       REQUIRED     + ", " +
                       STATE        + ", " +
                       TYPE         + ", " +
                       TYPE_REQUEST + ", " +
                       SEQUENCE;
            }
        }

        private final String TABLE_NAME =  "fields";

        public String name = TABLE_NAME;

        public final String select;
        
        public final FieldFetcher fetcher = new FieldFetcher();
        
        public static final Fields table = new Fields( null );
        
        public final Columns columns;
        
        private Fields( String alias )
        {
            this.name = alias != null ? TABLE_NAME + " " + alias : TABLE_NAME;
            
            columns = new Columns( alias != null ? alias + "." : "" );
            
            select = "select " + columns + " from " + this.name;            
        }
    }
    
    public static class TypesRoutes
    {
        public static TypesRoutes alias( String alias )
        {
            return new TypesRoutes( alias );
        }
        
        public class Columns
        {
            public String ID;
            public String SECTOR;
            public String TYPE;
            public String USER;
            public String SEQUENCE;
            public String DAYS;
            public String STATE;
            
            public Columns( String alias )
            {
                ID       = alias + "id";
                SECTOR   = alias + "ref_sector";
                TYPE     = alias + "ref_type";
                USER     = alias + "ref_user";
                SEQUENCE = alias + "sequence";
                DAYS     = alias + "days";
                STATE    = alias + "state";
            }

            @Override
            public String toString() 
            {
                return ID       + ", " +
                       SECTOR   + ", " +
                       TYPE     + ", " +
                       USER     + ", " +
                       SEQUENCE + ", " +
                       DAYS     + ", " +
                       STATE;
            }
        }

        private final String TABLE_NAME =  "types_routes";

        public String name = TABLE_NAME;

        public final String select;
        
        public final TypeRouteFetcher fetcher = new TypeRouteFetcher();
        
        public static final TypesRoutes table = new TypesRoutes( null );
        
        public final Columns columns;
        
        private TypesRoutes( String alias )
        {
            this.name = alias != null ? TABLE_NAME + " " + alias : TABLE_NAME;
            
            columns = new Columns( alias != null ? alias + "." : "" );
            
            select = "select " + columns + " from " + this.name;            
        }
    }
    
    public static class Requests
    {
        public static Requests alias( String alias )
        {
            return new Requests( alias );
        }
        
        public class Columns
        {
            public String ID;
            public String TYPE;
            public String USER;
            public String START;
            public String END;
            public String STATE;
            
            public Columns( String alias )
            {
                ID       = alias + "id";
                TYPE     = alias + "ref_type";
                USER     = alias + "ref_user";
                START    = alias + "dt_start";
                END      = alias + "dt_end";
                STATE    = alias + "state";
            }

            @Override
            public String toString() 
            {
                return ID       + ", " +
                       TYPE     + ", " +
                       USER     + ", " +
                       START    + ", " +
                       END      + ", " +
                       STATE;
            }
        }

        private final String TABLE_NAME =  "requests";

        public String name = TABLE_NAME;

        public final String select;
        
        public final RequestFetcher fetcher = new RequestFetcher();
        
        public static final Requests table = new Requests( null );
        
        public final Columns columns;
        
        private Requests( String alias )
        {
            this.name = alias != null ? TABLE_NAME + " " + alias : TABLE_NAME;
            
            columns = new Columns( alias != null ? alias + "." : "" );
            
            select = "select " + columns + " from " + this.name;            
        }
    }
    
    public static class ValuesRequests
    {
        public static ValuesRequests alias( String alias )
        {
            return new ValuesRequests( alias );
        }
        
        public class Columns
        {
            public String ID;
            public String FIELD;
            public String VALUE;
            public String REQUEST;
            
            public Columns( String alias )
            {
                ID      = alias + "id";
                FIELD   = alias + "ref_field";
                VALUE   = alias + "value";
                REQUEST = alias + "ref_request";
            }

            @Override
            public String toString() 
            {
                return ID     + ", " +
                       FIELD  + ", " +
                       VALUE  + ", " +
                       REQUEST;
            }
        }

        private final String TABLE_NAME =  "values_requests";

        public String name = TABLE_NAME;

        public final String select;
        
        public final ValueRequestFetcher fetcher = new ValueRequestFetcher();
        
        public static final ValuesRequests table = new ValuesRequests( null );
        
        public final Columns columns;
        
        private ValuesRequests( String alias )
        {
            this.name = alias != null ? TABLE_NAME + " " + alias : TABLE_NAME;
            
            columns = new Columns( alias != null ? alias + "." : "" );
            
            select = "select " + columns + " from " + this.name;            
        }
    }
    
    public static class Locks
    {
        public static Locks alias( String alias )
        {
            return new Locks( alias );
        }
        
        public class Columns
        {
            public String ID;
            public String USER;
            public String ROUTE;
            public String DATE;
            
            public Columns( String alias )
            {
                ID    = alias + "id";
                USER  = alias + "ref_user";
                ROUTE = alias + "ref_request_route";
                DATE  = alias + "date";
            }

            @Override
            public String toString() 
            {
                return ID    + ", " +
                       USER  + ", " +
                       ROUTE + ", " +
                       DATE;
            }
        }

        private final String TABLE_NAME =  "locks";

        public String name = TABLE_NAME;

        public final String select;
        
        public final LockFetcher fetcher = new LockFetcher();
        
        public static final Locks table = new Locks( null );
        
        public final Columns columns;
        
        private Locks( String alias )
        {
            this.name = alias != null ? TABLE_NAME + " " + alias : TABLE_NAME;
            
            columns = new Columns( alias != null ? alias + "." : "" );
            
            select = "select " + columns + " from " + this.name;            
        }
    }
    
    public static class FieldsValues
    {
        public static FieldsValues alias( String alias )
        {
            return new FieldsValues( alias );
        }
        
        public class Columns
        {
            public String ID;
            public String STATE;
            public String VALUE;
            public String FIELD;
            
            public Columns( String alias )
            {
                ID    = alias + "id";
                STATE = alias + "state";
                FIELD = alias + "ref_field";
                VALUE = alias + "value";
                
            }

            @Override
            public String toString() 
            {
                return ID       + ", " +
                       STATE    + ", " +
                       FIELD    + ", " +
                       VALUE;
            }
        }

        private final String TABLE_NAME =  "fields_values";

        public String name = TABLE_NAME;

        public final String select;
        
        public final FieldValueFetcher fetcher = new FieldValueFetcher();
        
        public static final FieldsValues table = new FieldsValues( null );
        
        public final Columns columns;
        
        private FieldsValues( String alias )
        {
            this.name = alias != null ? TABLE_NAME + " " + alias : TABLE_NAME;
            
            columns = new Columns( alias != null ? alias + "." : "" );
            
            select = "select " + columns + " from " + this.name;            
        }
    }
    
    public static class RequestsRoutes
    {
        public static RequestsRoutes alias( String alias )
        {
            return new RequestsRoutes( alias );
        }
        
        public class Columns
        {
            public String ID;
            public String USER;
            public String TYPE_ROUTE;
            public String REQUEST;
            public String STATE;
            public String IN;
            public String OUT;
            public String INFO;
            public String SEQUENCE;
            public String SECTOR;
            
            public Columns( String alias )
            {
                ID         = alias + "id";
                USER       = alias + "ref_user";
                TYPE_ROUTE = alias + "ref_type_route";
                REQUEST    = alias + "ref_request";
                STATE      = alias + "state";
                IN         = alias + "date_in";
                OUT        = alias + "date_out";
                INFO       = alias + "info";
                SEQUENCE   = alias + "sequence";
                SECTOR     = alias + "ref_sector";
            }

            @Override
            public String toString() 
            {
                return ID          + ", " +
                       USER        + ", " +
                       TYPE_ROUTE  + ", " +
                       REQUEST     + ", " +
                       STATE       + ", " +
                       IN          + ", " +
                       OUT         + ", " +
                       INFO        + ", " +
                       SEQUENCE    + ", " +
                       SECTOR;
            }
        }

        private final String TABLE_NAME =  "requests_routes";

        public String name = TABLE_NAME;

        public final String select;
        
        public final RequestRouteFetcher fetcher = new RequestRouteFetcher();
        
        public static final RequestsRoutes table = new RequestsRoutes( null );
        
        public final Columns columns;
        
        private RequestsRoutes( String alias )
        {
            this.name = alias != null ? TABLE_NAME + " " + alias : TABLE_NAME;
            
            columns = new Columns( alias != null ? alias + "." : "" );
            
            select = "select " + columns + " from " + this.name;            
        }
    }
    
    public static class Groups
    {
        public static Groups alias( String alias )
        {
            return new Groups( alias );
        }
        
        public  class Columns
        {
            public String ID;
            public String NAME;
            public String ORIGIN;
            
            public Columns( String alias ) 
            {
                ID           = alias + "id";
                NAME         = alias + "name";
                ORIGIN       = alias + "origin";
            }
            
            @Override
            public String toString() 
            {
                return ID           + ", " +
                       NAME         + ", " +
                       ORIGIN;
            }
        }
        
        private final String TABLE_NAME =  "groups";

        public String name = TABLE_NAME;

        public final String select;
        
        public final GroupFetcher fetcher = new GroupFetcher();
        
        public static final Groups table = new Groups( null );
        
        public final Columns columns;

        private Groups( String alias ) 
        {
            this.name = alias != null ? TABLE_NAME + " " + alias : TABLE_NAME;
            
            columns = new Columns( alias != null ? alias + "." : "" );
            
            select = "select " + columns + " from " + this.name;
        }
    }
    
    public static class Roles
    {
        public static Roles alias( String alias )
        {
            return new Roles( alias );
        }
        
        public  class Columns
        {
            public String ID;
            public String NAME;
            public String STATE;
            
            public Columns( String alias ) 
            {
                ID           = alias + "id";
                NAME         = alias + "name";
                STATE        = alias + "state";
            }
            
            @Override
            public String toString() 
            {
                return ID           + ", " +
                       NAME         + ", " +
                       STATE;
            }
        }
        
        private final String TABLE_NAME =  "roles";

        public String name = TABLE_NAME;

        public final String select;
        
        public final RoleFetcher fetcher = new RoleFetcher();
        
        public static final Roles table = new Roles( null );
        
        public final Columns columns;

        private Roles( String alias ) 
        {
            this.name = alias != null ? TABLE_NAME + " " + alias : TABLE_NAME;
            
            columns = new Columns( alias != null ? alias + "." : "" );
            
            select = "select " + columns + " from " + this.name;
        }
    }
    
    public static class Actions
    {
        public static Actions alias( String alias )
        {
            return new Actions( alias );
        }
        
        public  class Columns
        {
            public String ID;
            public String NAME;
            
            public Columns( String alias ) 
            {
                ID           = alias + "id";
                NAME         = alias + "name";
            }
            
            @Override
            public String toString() 
            {
                return ID           + ", " +
                       NAME         ;
            }
        }
        
        private final String TABLE_NAME =  "actions";

        public String name = TABLE_NAME;

        public final String select;
        
        public final ActionFetcher fetcher = new ActionFetcher();
        
        public static final Actions table = new Actions( null );
        
        public final Columns columns;

        private Actions( String alias ) 
        {
            this.name = alias != null ? TABLE_NAME + " " + alias : TABLE_NAME;
            
            columns = new Columns( alias != null ? alias + "." : "" );
            
            select = "select " + columns + " from " + this.name;
        }
    }
    
    public static class ActionsGroups
    {
        public static ActionsGroups alias( String alias )
        {
            return new ActionsGroups( alias );
        }
        
        public  class Columns
        {
            public String ID;
            public String REF_ACTION;
            public String REF_GROUP;
            
            public Columns( String alias ) 
            {
                ID           = alias + "id";
                REF_ACTION   = alias + "ref_action";
                REF_GROUP    = alias + "ref_group";
            }
            
            @Override
            public String toString() 
            {
                return ID           + ", " +
                       REF_ACTION   + ", " +
                       REF_GROUP    ;
            }
        }
        
        private final String TABLE_NAME =  "actions_groups";

        public String name = TABLE_NAME;

        public final String select;
        
        public final ActionGroupFetcher fetcher = new ActionGroupFetcher();
        
        public static final ActionsGroups table = new ActionsGroups( null );
        
        public final Columns columns;

        private ActionsGroups( String alias ) 
        {
            this.name = alias != null ? TABLE_NAME + " " + alias : TABLE_NAME;
            
            columns = new Columns( alias != null ? alias + "." : "" );
            
            select = "select " + columns + " from " + this.name;
        }
    }
    
    public static class Permissions
    {
        public static Permissions alias( String alias )
        {
            return new Permissions( alias );
        }
        
        public  class Columns
        {
            public String ID;
            public String REF_ACTION_GROUP;
            public String REF_ROLE;
            public String ACTIVE;
            public String DESCRIPTION;
            public String GROUP;
            
            public Columns( String alias ) 
            {
                ID               = alias + "id";
                REF_ACTION_GROUP = alias + "ref_action_group";
                REF_ROLE         = alias + "ref_role";
                ACTIVE           = alias + "active";
                DESCRIPTION      = "( select a.name from actions a, actions_groups ag where a.id = ag.ref_action and ag.id = ref_action_group )";
                GROUP            = "( select ag.ref_group from actions_groups ag where ag.id = ref_action_group )";
            }
            
            @Override
            public String toString() 
            {
                return ID               + ", " +
                       REF_ACTION_GROUP + ", " +
                       REF_ROLE         + ", " +
                       ACTIVE           + ", " +
                       DESCRIPTION      + ", " +
                       GROUP;
            }
        }
        
        private final String TABLE_NAME =  "permissions";

        public String name = TABLE_NAME;

        public final String select;
        
        public final PermissionFetcher fetcher = new PermissionFetcher();
        
        public static final Permissions table = new Permissions( null );
        
        public final Columns columns;

        private Permissions( String alias ) 
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
