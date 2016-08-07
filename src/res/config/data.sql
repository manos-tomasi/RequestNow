/*
 *	INSERT SECTORES
 */
insert into sectors ( name , state ) values ( 'NTI' , 1 );
insert into sectors ( name , state ) values ( 'RH' , 1 );
insert into sectors ( name , state ) values ( 'DIRETORES' , 1 );

/*
 *	INSERT USER
 */
insert into users ( cpf, birth_date, rg, mail, phone, gender, name, state, login, password, role, ref_sector  ) values ( '02943508020', '1995-03-15', '7383250983', 'lcstomasi@gmail.com'      , '5197068174', 1, 'LUCAS TOMASI'         , 0, 'lt',    '<2fcc1dd51cb7514a99f03debf513ca7af3b25669>', 0 , 1);
insert into users ( cpf, birth_date, rg, mail, phone, gender, name, state, login, password, role, ref_sector  ) values ( '12312414211', '1995-03-15', '4242398503', 'artur.tomasi@outlook.com' , '5195090933', 1, 'ARTUR TOMASI'         , 0, 'art',   '<2fcc1dd51cb7514a99f03debf513ca7af3b25669>', 0 , 1 );
insert into users ( cpf, birth_date, rg, mail, phone, gender, name, state, login, password, role, ref_sector  ) values ( '00000000000', '1994-10-30', '1232141280', 'admin@helpfin.br'         , '5198434239', 1, 'ADMINISTRADOR SISTEMA', 0, 'admin', '<2fcc1dd51cb7514a99f03debf513ca7af3b25669>', 0 , 1);

/*
 *      INSERT CATEGORIES
 */
insert into categories values ( default, 'Financeiro', 0, 'Categoria teste');

/*
 *      INSERT TYPES
 */
insert into types values ( default, 'Solicitação do FIES', 0, 'Requisição para receber o FIES', 1 );
insert into types values ( default, 'Aumento Salarial', 0, '', 1 );
insert into types values ( default, 'Reembolso Despesas', 0, '', 1 );

/*
 *      INSERT FIELDS
 */
insert into fields values ( default, 'Data Solicitação', 3, 1, 0, 1 );
insert into fields values ( default, 'Data Solicitação', 3, 1, 0, 2 );
insert into fields values ( default, 'Data Solicitação', 3, 1, 0, 3 );
insert into fields values ( default, 'Data Solicitação', 3, 1, 0, 1 );