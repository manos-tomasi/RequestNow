/**
 * Author:  artur
 * Created: Aug 31, 2016
 */

/*############################################################################*/
/*                  Define Sequencia para os campos                           */
/*############################################################################*/

create or replace function makeSequenceField() returns trigger as 
$body$
declare
	sequence integer;
begin

    if ( TG_OP = 'INSERT' )then 
        select count(*) into sequence from fields where ref_type = new.ref_type and state = 0;

        new.sequence := sequence;
    end if;
              
    return new;

end;
$body$ 
	language plpgsql;

drop trigger makeSequenceField on fields;
create trigger makeSequenceField before insert or update on fields for each row execute procedure makeSequenceField();

/*############################################################################*/
/*                  Define Sequencia para as rotas                           */
/*############################################################################*/

create or replace function makeSequenceRoutes() returns trigger as 
$body$
declare
	sequence integer;
begin

    if ( TG_OP = 'INSERT' )then 
        select count(*) into sequence from types_routes where ref_type = new.ref_type and state = 0;

        new.sequence := sequence;
    end if;
              
    return new;

end;
$body$ 
	language plpgsql;

drop trigger makeSequenceRoutes on types_routes;
create trigger makeSequenceRoutes before insert or update on types_routes for each row execute procedure makeSequenceRoutes();

/*############################################################################*/
/*                  Ajusta Sequencia ao deletar os campos                     */
/*############################################################################*/

create or replace function adjustSequenceFields() returns trigger as 
$body$
declare
	field fields%rowtype;
begin
   
    if ( TG_OP = 'UPDATE' and new.state = 1 )then 
        for field in select * from fields where ref_type = new.ref_type and sequence > new.sequence and state = 0 order by sequence
        loop
            update fields set sequence = ( field.sequence - 1 ) where field.id = id;
        end loop;
    end if;

    return new;

end
$body$ 
	language plpgsql;

drop trigger adjustSequenceFields on fields;
create trigger adjustSequenceFields before insert or update on fields for each row execute procedure adjustSequenceFields();


/*############################################################################*/
/*                  Ajusta Sequencia ao deletar os rotas                      */
/*############################################################################*/

create or replace function adjustSequenceRoutes() returns trigger as 
$body$
declare
	route fields%types_routes;
begin
   
    if ( TG_OP = 'UPDATE' and new.state = 1 )then 
        for route in select * from types_routes where ref_type = new.ref_type and sequence > new.sequence and state = 0 order by sequence
        loop
            update types_routes set sequence = ( route.sequence - 1 ) where route.id = id;
        end loop;
    end if;

    return new;

end
$body$ 
	language plpgsql;

drop trigger adjustSequenceRoutes on types_routes;
create trigger adjustSequenceRoutes before insert or update on types_routes for each row execute procedure adjustSequenceRoutes();
