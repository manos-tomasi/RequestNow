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
/*                  Ajusta Sequencia ao deletar os campos                     */
/*############################################################################*/

-- create or replace function adjustSequenceFields() returns trigger as 
-- $body$
-- declare
-- 	field fields%rowtype;
-- begin
--    
--     for field in select * from fields where ref_type = new.ref_type and sequence > new.sequence and state = 0 order by sequence
--     loop
--         update fields set sequence = ( field.sequence - 1 ) where field.id;
--     end loop;
--     
--     return;
-- 
-- end
-- $body$ 
-- 	language plpgsql;
-- 
-- create trigger adjustSequenceFields after delete on fields for each row execute procedure adjustSequenceFields();





CREATE OR REPLACE VIEW view_routes AS
SELECT tr.id AS id,
       tr.days AS days,
       tr.sequence AS sequence,
       u.name AS USER,
       s.name AS sector,
       t.name AS TYPE
FROM types_routes tr
LEFT JOIN users u ON (tr.ref_user = u.id)
LEFT JOIN sectors s ON (tr.ref_sector = s.id)
INNER JOIN types t ON (tr.ref_type = t.id)
WHERE tr.state = 0;


CREATE OR REPLACE VIEW view_types AS
SELECT t.id AS id,
       t.name AS name,
       c.name AS category
FROM types t
INNER JOIN categories c ON (t.ref_category = c.id)
WHERE t.state = 0;


CREATE OR REPLACE VIEW view_fields AS
SELECT f.id AS id,
       f.label AS label,
       CASE
           WHEN f.TYPE = 0 THEN 'Texto'
           WHEN f.TYPE = 1 THEN 'Lista'
           WHEN f.TYPE = 2 THEN 'Número'
           WHEN f.TYPE = 3 THEN 'Data'
           WHEN f.TYPE = 4 THEN 'Arquivo'
           ELSE 'Texto Simples'
       END AS TYPE,
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
       t.name AS TYPE,
       u.name AS USER,
       to_char(dt_end::date ,'DD/MM/YYYY') AS dt_end ,
       to_char(dt_start::date, 'DD/MM/YYYY') AS dt_start ,
       CASE
           WHEN r.state = 1 THEN 'Aprovada'
           WHEN r.state = 2 THEN 'Cancelada'
           WHEN r.state = 3 THEN 'Reprovada'
           ELSE 'Em Andamento'
       END AS STATE
FROM requests r
INNER JOIN types t ON (t.id = r.ref_type)
INNER JOIN users u ON (u.id = r.ref_user);