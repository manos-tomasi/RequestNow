/**
 * Author:  artur
 * Created: Aug 31, 2016
 */



/*############################################################################*/
/*                 VIEWS ROTAS                                                */
/*############################################################################*/

CREATE OR REPLACE VIEW view_routes AS
SELECT tr.id AS id,
       tr.days AS days,
       tr.sequence AS sequence,
       coalesce( u.name, 'n/d' ) AS user,
       coalesce( s.name, 'n/d' ) AS sector,
       t.name AS type
FROM types_routes tr
LEFT JOIN users u ON (tr.ref_user = u.id)
LEFT JOIN sectors s ON (tr.ref_sector = s.id)
INNER JOIN types t ON (tr.ref_type = t.id)
WHERE tr.state = 0;

/*############################################################################*/
/*                 VIEWS CATEGORY                                              */
/*############################################################################*/

CREATE OR REPLACE VIEW view_categories AS
SELECT c.id AS id,
       c.name AS name,
       c.info AS info,
       ARRAY( SELECT t.name FROM types t WHERE t.ref_category = c.id and t.state = 0 ) AS types
FROM 
    categories c
where 
    c.state = 0;

/*############################################################################*/
/*                 VIEWS TYPES                                                */
/*############################################################################*/

CREATE OR REPLACE VIEW view_types AS
SELECT t.id AS id,
       t.name AS name,
       c.name AS category,
       t.info AS info,
       ARRAY( SELECT f.label FROM fields f WHERE f.ref_type = t.id and f.state = 0 ) AS fields,
       ARRAY( SELECT coalesce( u.name, 'n/d' ) || ' - ' || coalesce( s.name, 'n/d' ) FROM types_routes r, users u, sectors s WHERE r.ref_type = t.id and r.ref_sector = s.id and r.ref_user = u.id order by r.sequence ) AS routes
FROM types t
INNER JOIN categories c ON (t.ref_category = c.id)
WHERE t.state = 0;

/*############################################################################*/
/*                 VIEWS FIELDS                                               */
/*############################################################################*/

CREATE OR REPLACE VIEW view_fields AS
SELECT f.id AS id,
       f.label AS label,
       CASE
           WHEN f.type = 0 THEN 'Texto'
           WHEN f.type = 1 THEN 'Lista'
           WHEN f.type = 2 THEN 'Número'
           WHEN f.type = 3 THEN 'Data'
           WHEN f.type = 4 THEN 'Arquivo'
           ELSE 'Texto Simples'
       END AS type,
       t.name AS type_request,
       CASE
           WHEN f.required = 1 THEN 'Sim'
           ELSE 'Não'
       END AS required,
       f.sequence AS sequence
FROM types t ,
     fields f
WHERE t.id = f.ref_type
  AND f.state = 0;


CREATE OR REPLACE VIEW view_requests AS
SELECT r.id AS id,
       t.name AS type,
       u.name AS user,
       coalesce ( to_char( dt_end::date ,'DD/MM/YYYY' ), 'n/d' ) AS dt_end ,
       coalesce ( to_char( dt_start::date, 'DD/MM/YYYY'), 'n/d' ) AS dt_start ,
       CASE
           WHEN r.state = 1 THEN 'Aprovada'
           WHEN r.state = 2 THEN 'Cancelada'
           WHEN r.state = 3 THEN 'Reprovada'
           ELSE 'Em Andamento'
       END AS state
FROM requests r
INNER JOIN types t ON (t.id = r.ref_type)
INNER JOIN users u ON (u.id = r.ref_user);


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

drop trigger if exists makeSequenceField on fields;
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

drop trigger if exists makeSequenceRoutes on types_routes;
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

drop trigger if exists adjustSequenceFields on fields;
create trigger adjustSequenceFields before insert or update on fields for each row execute procedure adjustSequenceFields();


/*############################################################################*/
/*                  Ajusta Sequencia ao deletar os rotas                      */
/*############################################################################*/

create or replace function adjustSequenceRoutes() returns trigger as 
$body$
declare
	route types_routes%rowtype;
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

drop trigger if exists adjustSequenceRoutes on types_routes;
create trigger adjustSequenceRoutes before insert or update on types_routes for each row execute procedure adjustSequenceRoutes();


/*############################################################################*/
/*                  Ajusta Sequencia ao deletar os rotas                      */
/*############################################################################*/

create or replace function getJson( id in integer, clazz in varchar ) returns varchar as 
$body$
begin
   
    if ( $2 = 'route' ) then

        return ( select '{' ||
                            'id:'''       || r.id       || ''','  || 
                            'user:'''     || r.user     || ''','  || 
                            'sector:'''   || r.sector   || ''','  || 
                            'sequence:''' || r.sequence || ''','  || 
                            'type:'''     || r.type     || ''','  || 
                            'days:'''     || r.days     || ''''  || 
                        '}' 
               from 
                   view_routes r
               where 
                   r.id = $1  );

    elseif ( $2 = 'category' ) then
    
        return ( select '{' ||
                            'id:'''       || c.id          || ''',' || 
                            'name:'''     || c.name        || ''',' || 
                            'info:'''     || c.info        || ''',' ||
                            'types:'''    || c.types::text || ''''  ||
                       '}' 
               from 
                   view_categories c
               where 
                   c.id = $1  );
    
    elseif ( $2 = 'type' ) then
        
        return ( select '{'  ||
                             'id:'''       || t.id           || ''',' || 
                             'name:'''     || t.name         || ''',' || 
                             'info:'''     || t.info         || ''',' || 
                             'category:''' || t.category     || ''',' || 
                             'routes:'''   || t.routes::text || ''',' ||
                             'fields:'''   || t.fields::text || ''''  ||
                        '}'
                from 
                    view_types t
                where 
                    t.id = $1  );
    
    elseif ( $2 = 'field' ) then

        return ( select '{' ||
                             'id:'''           || f.id           || ''',' || 
                             'label:'''        || f.label        || ''',' || 
                             'type_request:''' || f.type_request || ''',' || 
                             'sequence:'''     || f.sequence     || ''',' || 
                             'type:'''         || f.type         || ''',' || 
                             'required:'''     || f.required     || ''''  || 
                        '}' 
                from 
                    view_fields f
                where 
                    f.id = $1  );

    elseif ( $2 = 'request' ) then

            return ( select '{' ||
                                'id:'''     || r.id     || ''',' || 
                                'type:'''   || r.type   || ''',' || 
                                'user:'''   || r.user   || ''',' || 
                                'dt_end:''' || r.dt_end || ''',' || 
                                'state:'''  || r.state  || ''''  || 
                            '}'
                    from 
                        view_requests r
                    where 
                        r.id = $1  );
    end if; 

    return null;

end
$body$ 
	language plpgsql;

/*############################################################################*/
/*                  busca o typo de requisição de acordo com a requisição     */
/*############################################################################*/

create or replace function getTypeByRequest( id in integer ) returns setof types as
          'select t.* 
            from types t
           inner join requests r on ( t.id = r.ref_type )
           where r.id = $1;'
language sql;

/*############################################################################*/
/* insere as primeiras permissoes defaluts no banco apos criar uma nova função*/
/*############################################################################*/


create or replace function makePermissionsDefaults() returns trigger as 
$body$
declare
	sequence integer;
begin

    insert into permissions ( ref_role, ref_action_group, active  ) select new.id, a.id, 't' from actions_groups a;
              
    return new;

end;
$body$ 
	language plpgsql;

drop trigger if exists makePermissionsDefaults on roles;
create trigger makePermissionsDefaults after insert on roles for each row execute procedure makePermissionsDefaults();

