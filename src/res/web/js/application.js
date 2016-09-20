function setCategory( category )
{
    if ( category )
    {
        $('#category_id').html( category[ 'id'  ] );
        $('#category_name').html( category[ 'name'  ] );
        $('#category_types').html( composeList( category[ 'types' ] ) );
        $('#category_info').html( composeInfo( category[ 'info'  ] ) );
    }
}

function setField( field )
{
    if ( field )
    {
        $('#field_id').html( field[ 'id'  ] );
        $('#field_label').html( field[ 'label'  ] );
        $('#field_typeRequest').html( field[ 'type_request' ] );
        $('#field_required').html( field[ 'required' ] );
        $('#field_type').html( field[ 'type' ] );
        $('#field_sequence').html( field[ 'sequence'  ] );
    }
}

function setType( type )
{
    if ( type )
    {
        $('#type_id').html( type[ 'id'  ] );
        $('#type_name').html( type[ 'name'  ] );
        $('#type_category').html( type[ 'category' ] );
        $('#type_field').html( composeList( type[ 'fields' ] ) );
        $('#type_route').html( composeList( type[ 'routes' ] ) );
        $('#type_info').html( composeInfo( type[ 'info'  ] ) );
    }
}

function setRoute( route )
{
    if ( route )
    {
        $('#route_name').html( route[ 'user' ] + ' - ' + route[ 'sector' ] );
        $('#route_id').html( route[ 'id'  ] );
        $('#route_type').html( route[ 'type'  ] );
        $('#route_user').html( route[ 'user' ] );
        $('#route_sector').html( route[ 'sector' ] );
        $('#route_days').html( route[ 'days' ] );
        $('#route_sequence').html( route[ 'sequence'  ] );
    }
}

function setRequest( request )
{
    if ( request )
    {
        $('#request_type').html( request[ 'type'  ] );
        $('#request_user').html( request[ 'user'  ] );
        $('#request_id').html( request[ 'id'  ] );
        $('#request_dt_end').html( request[ 'dt_end'  ] );
        $('#request_dt_start').html( request[ 'dt_start'  ] );
        $('#request_state').html( request[ 'state'  ] );
    }

//    if ( fields )
//    {
//        $( '#request_field' ).html( String( fields ) );
//    }
//    else
//    {
//        $( '#request_field' ).html( "String( fields )" );
//    }
}

function definePermissions( permissions )
{
    if ( permissions )
    {
        for( var role in permissions )
        {   
            if ( ! permissions[ role ] )
            {
                $( '#' + role ).attr( 'onclick', 'return false;' );
                $( '#' + role ).attr( 'style', 'opacity:0.4;' );
            }
        }
    }        
}

function composeList( list )
{
    if ( list ) return list.toString()
                            .replace( /{|}/g, "" )
                            .replace( /,/g, "<br>" )
                            .replace( /\"/g , "" );
}

function composeInfo( info )
{
    if( info ) return info.toString().replace( /\"/g , "'" );
}