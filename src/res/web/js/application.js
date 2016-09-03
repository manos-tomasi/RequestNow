function setCategory( category )
{
    $('#category_id').html( category[ 'id'  ] );
    $('#category_name').html( category[ 'name'  ] );
    $('#category_types').html( composeList( category[ 'types' ] ) );
    $('#category_info').html( composeInfo( category[ 'info'  ] ) );
}

function setField( field )
{
    $('#field_id').html( field[ 'id' ] );
    $('#field_label').html( field[ 'label' ] );
    $('#field_type').html( field[ 'type' ] );
    $('#field_typeRequest').html( field[ 'typeRequest' ] );
    $('#field_required').html( field[ 'required' ] );
    $('#field_state').html( field[ 'state' ] );
    $('#field_sequence').html( field[ 'sequence' ] );
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