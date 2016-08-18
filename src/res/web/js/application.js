function setCategory( category )
{
    $('#categoy_id').html( category[ 'id'  ] );
    $('#categoy_name').html( category[ 'name'  ] );
    $('#categoy_state').html( category[ 'state' ] );
    $('#categoy_info').html( category[ 'info'  ].toString().replace( /\"/g , "'" ) );
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
    $('#type_id').html( type[ 'id'  ] );
    $('#type_name').html( type[ 'name'  ] );
    $('#type_state').html( type[ 'state' ] );
    $('#type_category').html( type[ 'category' ] );
    $('#type_info').html( type[ 'info'  ].toString().replace( /\"/g , "'" ) );
}