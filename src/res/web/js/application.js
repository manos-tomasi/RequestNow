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
}

function setFields( fields )
{
    if( fields )
    {
        fields.forEach( function( element ){
            
            if( element['type'] != 4 )
            {
                $('#campos').after( "<tr><td class='myfield'>"+ element['field'] +"</td><td class='myvalue'>"+ element['value'] +"</td></tr>" );
            }
            
            else
            {
                var src = "/" + element['request'] + "/" + element['id'] + "/" + element['value'];
                
                $('#campos').after( "<tr>" +
                                        "<td class=\"myfield\">"+ 
                                            element['field'] + 
                                        "</td>" + 
                                        "<td class=\"myvalue\">" + 
                                            "<i class=\"fa fa-download fa-2x\"" +
                                               "onclick=\"application.download( '" + src + "' );\"" +
                                               " style=\"float: left; cursor: pointer; padding-right: 10px; vertical-align: middle;\" aria-hidden=\"true\"></i>" +
                                            "<div style=\"cursor: pointer;\" onclick=\"application.download( '" + src + "' );\">"+ 
                                                element['value'] +
                                            "</div>" + 
                                        "</td>" +
                                    "</tr>" );
            }
        } );
    }           
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

function setDispatchs( dispatchs )
{
    if( dispatchs )
    {
        dispatchs.forEach( function( element ){ 
            $('#request_route').append("<tr id='dispatchs_title'><th id='dispatch_title' colspan='1' class='head'>Despacho "+ element[ 'sequence' ]+" de " + dispatchs.length + "</th><th><div style='text-align: right;'><i style='color: #2f4e76' class='fa fa-legal fa-3x'></i></div></th></tr>");        
            var info = composeInfo( element[ 'info' ] );
            if( info == undefined )
            {
                info = '';
            }
            $('#request_route').append("<tr><td colspan='2'>"+ info  +"</td></tr>");        
            
            $('#request_route').append("<tr><td class='myfield'>Situação    </td><td class='myvalue'>"+ element[ 'state' ]    +"</td></tr>");        
            $('#request_route').append("<tr><td class='myfield'>Data entrada</td><td class='myvalue'>"+ element[ 'date_in' ]  +"</td></tr>");        
            $('#request_route').append("<tr><td class='myfield'>Data saída  </td><td class='myvalue'>"+ element[ 'date_out' ] +"</td></tr>");        
            $('#request_route').append("<tr><td class='myfield'>Usuário     </td><td class='myvalue'>"+ element[ 'user' ]     +"</td></tr>");        
            $('#request_route').append("<tr><td class='myfield'>Setor       </td><td class='myvalue'>"+ element[ 'sector' ]   +"</td></tr>");        

        }); 
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