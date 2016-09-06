INSERT INTO categories VALUES ( 1 , 'Financeiro' , 0, '' );
INSERT INTO categories VALUES ( 2 , 'Academico'  , 0, 'asdasd' );

SELECT pg_catalog.setval('categories_id_seq', 2, true);

INSERT INTO types VALUES (1, 'Solicitação do FIES', 0, 'Requisição para receber o FIES', 1);
INSERT INTO types VALUES (2, 'Aumento Salarial', 0, '', 1);
INSERT INTO types VALUES (3, 'Reembolso Despesas', 0, '', 1);

SELECT pg_catalog.setval('types_id_seq', 3, true);

INSERT INTO fields VALUES (1, 'Data Solicitação', 3, 1, 0, 1, 0);
INSERT INTO fields VALUES (2, 'Data Solicitação', 3, 1, 0, 2, 0);
INSERT INTO fields VALUES (3, 'Data Solicitação', 3, 1, 0, 3, 0);
INSERT INTO fields VALUES (4, 'Data Solicitação', 3, 1, 0, 1, 1);
INSERT INTO fields VALUES (5, 'Text', 0, 1, 0, 1, 1);
INSERT INTO fields VALUES (6, 'Teste', 0, 0, 0, 1, 2);
INSERT INTO fields VALUES (7, 'Ttttt', 4, 0, 0, 1, 3);
INSERT INTO fields VALUES (8, 'aaaa', 5, 0, 0, 1,4);

SELECT pg_catalog.setval('fields_id_seq', 8, true);
SELECT pg_catalog.setval('fields_values_id_seq', 1, false);
SELECT pg_catalog.setval('locks_id_seq', 1, false);

INSERT INTO roles VALUES ( 1, 'Administrador' ,  0 );
INSERT INTO roles VALUES ( 2, 'Financeiro'    ,  0 );
SELECT pg_catalog.setval('roles_id_seq', 3, false);


INSERT INTO sectors VALUES (3, 'DIRETORES', 1);
INSERT INTO sectors VALUES (2, 'RH', 0);
INSERT INTO sectors VALUES (1, 'NTI', 0);

SELECT pg_catalog.setval('sectors_id_seq', 4, true);

INSERT INTO users VALUES (1, 'LUCAS TOMASI', '02943508020', '1995-03-15', 1, 0, '<2fcc1dd51cb7514a99f03debf513ca7af3b25669>', 'lt', 'lcstomasi@gmail.com', '5197068174', 1, '7383250983', 1);
INSERT INTO users VALUES (2, 'ARTUR TOMASI', '12312414211', '1995-03-15', 1, 0, '<2fcc1dd51cb7514a99f03debf513ca7af3b25669>', 'art', 'artur.tomasi@outlook.com', '5195090933', 1, '4242398503', 1);
INSERT INTO users VALUES (3, 'ADMINISTRADOR SISTEMA', '00000000000', '1994-10-30', 1, 0, '<2fcc1dd51cb7514a99f03debf513ca7af3b25669>', 'admin', 'admin@helpfin.br', '5198434239', 1, '1232141280', 1);

SELECT pg_catalog.setval('users_id_seq', 4, true);

INSERT INTO types_routes VALUES (3, 1, 2, NULL, 1, 0, 1);
INSERT INTO types_routes VALUES (4, 1, 2, 1, 2, 0, 2);

SELECT pg_catalog.setval('types_routes_id_seq', 4, true);

INSERT INTO requests VALUES (15, 2, 1, '2016-08-13 22:54:16.216', '2016-08-13 23:04:12.16', 1);
INSERT INTO requests VALUES (16, 2, 1, '2016-08-13 22:59:08.102', '2016-08-13 23:16:01.899', 1);
INSERT INTO requests VALUES (17, 2, 1, '2016-08-13 23:23:27.945', NULL, 4);

SELECT pg_catalog.setval('requests_id_seq', 17, true);

INSERT INTO requests_routes VALUES (7, 15, 3, '2016-08-13 22:54:16.286', NULL, 0, 1, NULL, 1);
INSERT INTO requests_routes VALUES (8, 15, 4, NULL, NULL, 0, 1, NULL, 2);
INSERT INTO requests_routes VALUES (9, 16, 3, '2016-08-13 22:59:08.147', '2016-08-13 23:16:01.87', 0, 1, NULL, 1);
INSERT INTO requests_routes VALUES (10, 16, 4, NULL, '2016-08-13 23:16:01.888', 0, 1, NULL, 2);
INSERT INTO requests_routes VALUES (11, 17, 3, '2016-08-13 23:23:27.988', NULL, 4, NULL, NULL, 1);
INSERT INTO requests_routes VALUES (12, 17, 4, NULL, NULL, 3, NULL, NULL, 2);

SELECT pg_catalog.setval('requests_routes_id_seq', 12, true);



INSERT INTO values_requests VALUES (8, 2, 15, '2016-08-17');
INSERT INTO values_requests VALUES (9, 2, 16, '2016-08-23');
INSERT INTO values_requests VALUES (10, 2, 17, '2016-09-08');

SELECT pg_catalog.setval('values_requests_id_seq', 10, true);



INSERT INTO actions VALUES ( 1 , 'VISUALIZAR');
INSERT INTO actions VALUES ( 2 , 'EDITAR'    );
INSERT INTO actions VALUES ( 3 , 'NOVO'      );
INSERT INTO actions VALUES ( 4 , 'EXCLUIR'   );
INSERT INTO actions VALUES ( 5 , 'DESPACHAR' );
INSERT INTO actions VALUES ( 6 , 'CANCELAR'  );
INSERT INTO actions VALUES ( 7 , 'IMPRIMIR'  );


INSERT INTO groups VALUES ( 1, 'Usuários'         , 'com.paa.requestnow.control.UserController'       ); 
INSERT INTO groups VALUES ( 2, 'Categorias'       , 'com.paa.requestnow.control.CategoryController'   ); 
INSERT INTO groups VALUES ( 3, 'Campos'           , 'com.paa.requestnow.control.FieldController'      ); 
INSERT INTO groups VALUES ( 4, 'Permissão'        , 'com.paa.requestnow.control.PermissionController' ); 
INSERT INTO groups VALUES ( 5, 'Setor'            , 'com.paa.requestnow.control.SectorController'     ); 
INSERT INTO groups VALUES ( 6, 'Tipos Requisição' , 'com.paa.requestnow.control.TypeController'       ); 
INSERT INTO groups VALUES ( 7, 'Rotas'            , 'com.paa.requestnow.control.TypeRouteController'  ); 
INSERT INTO groups VALUES ( 8, 'Despachos'        , 'com.paa.requestnow.control.RequestController'            ); 
INSERT INTO groups VALUES ( 9, 'Requisições'      , 'com.paa.requestnow.control.RequestRouteController'       ); 

INSERT INTO actions_groups VALUES ( 1,  1 , 9 );
INSERT INTO actions_groups VALUES ( 2,  3 , 9 );
INSERT INTO actions_groups VALUES ( 3,  6 , 9 );
INSERT INTO actions_groups VALUES ( 4,  5 , 8 );
INSERT INTO actions_groups VALUES ( 5,  1 , 1 );
INSERT INTO actions_groups VALUES ( 6,  2 , 1 );
INSERT INTO actions_groups VALUES ( 7,  3 , 1 );
INSERT INTO actions_groups VALUES ( 8,  4 , 1 );
INSERT INTO actions_groups VALUES ( 9,  1 , 2 );
INSERT INTO actions_groups VALUES (10,  2 , 2 );
INSERT INTO actions_groups VALUES (11,  3 , 2 );
INSERT INTO actions_groups VALUES (12,  4 , 2 );
INSERT INTO actions_groups VALUES (13,  1 , 3 );
INSERT INTO actions_groups VALUES (14,  2 , 3 );
INSERT INTO actions_groups VALUES (15,  3 , 3 );
INSERT INTO actions_groups VALUES (16,  4 , 3 );
INSERT INTO actions_groups VALUES (17,  1 , 4 );
INSERT INTO actions_groups VALUES (18,  2 , 4 );
INSERT INTO actions_groups VALUES (19,  3 , 4 );
INSERT INTO actions_groups VALUES (20,  4 , 4 );
INSERT INTO actions_groups VALUES (21,  1 , 5 );
INSERT INTO actions_groups VALUES (22,  2 , 5 );
INSERT INTO actions_groups VALUES (23,  3 , 5 );
INSERT INTO actions_groups VALUES (24,  4 , 5 );
INSERT INTO actions_groups VALUES (25,  1 , 6 );
INSERT INTO actions_groups VALUES (26,  2 , 6 );
INSERT INTO actions_groups VALUES (27,  3 , 6 );
INSERT INTO actions_groups VALUES (28,  4 , 6 );
INSERT INTO actions_groups VALUES (29,  1 , 7 );
INSERT INTO actions_groups VALUES (30,  2 , 7 );
INSERT INTO actions_groups VALUES (31,  3 , 7 );
INSERT INTO actions_groups VALUES (32,  4 , 7 );

INSERT INTO permissions ( ref_action_group, ref_role , active ) VALUES (  1 , 1, 't' );
INSERT INTO permissions ( ref_action_group, ref_role , active ) VALUES (  2 , 1, 't' );
INSERT INTO permissions ( ref_action_group, ref_role , active ) VALUES (  3 , 1, 't' );
INSERT INTO permissions ( ref_action_group, ref_role , active ) VALUES (  4 , 1, 't' );
INSERT INTO permissions ( ref_action_group, ref_role , active ) VALUES (  5 , 1, 't' );
INSERT INTO permissions ( ref_action_group, ref_role , active ) VALUES (  6 , 1, 't' );
INSERT INTO permissions ( ref_action_group, ref_role , active ) VALUES (  7 , 1, 't' );
INSERT INTO permissions ( ref_action_group, ref_role , active ) VALUES (  8 , 1, 't' );
INSERT INTO permissions ( ref_action_group, ref_role , active ) VALUES (  9 , 1, 't' );
INSERT INTO permissions ( ref_action_group, ref_role , active ) VALUES ( 10 , 1, 't' );
INSERT INTO permissions ( ref_action_group, ref_role , active ) VALUES ( 11 , 1, 't' );
INSERT INTO permissions ( ref_action_group, ref_role , active ) VALUES ( 12 , 1, 't' );
INSERT INTO permissions ( ref_action_group, ref_role , active ) VALUES ( 13 , 1, 't' );
INSERT INTO permissions ( ref_action_group, ref_role , active ) VALUES ( 14 , 1, 't' );
INSERT INTO permissions ( ref_action_group, ref_role , active ) VALUES ( 15 , 1, 't' );
INSERT INTO permissions ( ref_action_group, ref_role , active ) VALUES ( 16 , 1, 't' );
INSERT INTO permissions ( ref_action_group, ref_role , active ) VALUES ( 17 , 1, 't' );
INSERT INTO permissions ( ref_action_group, ref_role , active ) VALUES ( 18 , 1, 't' );
INSERT INTO permissions ( ref_action_group, ref_role , active ) VALUES ( 19 , 1, 't' );
INSERT INTO permissions ( ref_action_group, ref_role , active ) VALUES ( 20 , 1, 't' );
INSERT INTO permissions ( ref_action_group, ref_role , active ) VALUES ( 21 , 1, 't' );
INSERT INTO permissions ( ref_action_group, ref_role , active ) VALUES ( 22 , 1, 't' );
INSERT INTO permissions ( ref_action_group, ref_role , active ) VALUES ( 23 , 1, 't' );
INSERT INTO permissions ( ref_action_group, ref_role , active ) VALUES ( 24 , 1, 't' );
INSERT INTO permissions ( ref_action_group, ref_role , active ) VALUES ( 25 , 1, 't' );
INSERT INTO permissions ( ref_action_group, ref_role , active ) VALUES ( 26 , 1, 't' );
INSERT INTO permissions ( ref_action_group, ref_role , active ) VALUES ( 27 , 1, 't' );
INSERT INTO permissions ( ref_action_group, ref_role , active ) VALUES ( 28 , 1, 't' );
INSERT INTO permissions ( ref_action_group, ref_role , active ) VALUES ( 29 , 1, 't' );
INSERT INTO permissions ( ref_action_group, ref_role , active ) VALUES ( 30 , 1, 't' );
INSERT INTO permissions ( ref_action_group, ref_role , active ) VALUES ( 31 , 1, 't' );
INSERT INTO permissions ( ref_action_group, ref_role , active ) VALUES ( 32 , 1, 't' );
INSERT INTO permissions ( ref_action_group, ref_role , active ) VALUES (  1 , 2, 't' );
INSERT INTO permissions ( ref_action_group, ref_role , active ) VALUES (  2 , 2, 't' );
INSERT INTO permissions ( ref_action_group, ref_role , active ) VALUES (  3 , 2, 't' );
INSERT INTO permissions ( ref_action_group, ref_role , active ) VALUES (  4 , 2, 'f' );
INSERT INTO permissions ( ref_action_group, ref_role , active ) VALUES (  5 , 2, 'f' );
INSERT INTO permissions ( ref_action_group, ref_role , active ) VALUES (  6 , 2, 'f' );
INSERT INTO permissions ( ref_action_group, ref_role , active ) VALUES (  7 , 2, 'f' );
INSERT INTO permissions ( ref_action_group, ref_role , active ) VALUES (  8 , 2, 'f' );
INSERT INTO permissions ( ref_action_group, ref_role , active ) VALUES (  9 , 2, 'f' );
INSERT INTO permissions ( ref_action_group, ref_role , active ) VALUES ( 10 , 2, 'f' );
INSERT INTO permissions ( ref_action_group, ref_role , active ) VALUES ( 11 , 2, 'f' );
INSERT INTO permissions ( ref_action_group, ref_role , active ) VALUES ( 12 , 2, 'f' );
INSERT INTO permissions ( ref_action_group, ref_role , active ) VALUES ( 13 , 2, 'f' );
INSERT INTO permissions ( ref_action_group, ref_role , active ) VALUES ( 14 , 2, 'f' );
INSERT INTO permissions ( ref_action_group, ref_role , active ) VALUES ( 15 , 2, 'f' );
INSERT INTO permissions ( ref_action_group, ref_role , active ) VALUES ( 16 , 2, 'f' );
INSERT INTO permissions ( ref_action_group, ref_role , active ) VALUES ( 17 , 2, 'f' );
INSERT INTO permissions ( ref_action_group, ref_role , active ) VALUES ( 18 , 2, 'f' );
INSERT INTO permissions ( ref_action_group, ref_role , active ) VALUES ( 19 , 2, 'f' );
INSERT INTO permissions ( ref_action_group, ref_role , active ) VALUES ( 20 , 2, 'f' );
INSERT INTO permissions ( ref_action_group, ref_role , active ) VALUES ( 21 , 2, 'f' );
INSERT INTO permissions ( ref_action_group, ref_role , active ) VALUES ( 22 , 2, 'f' );
INSERT INTO permissions ( ref_action_group, ref_role , active ) VALUES ( 23 , 2, 'f' );
INSERT INTO permissions ( ref_action_group, ref_role , active ) VALUES ( 24 , 2, 'f' );
INSERT INTO permissions ( ref_action_group, ref_role , active ) VALUES ( 25 , 2, 'f' );
INSERT INTO permissions ( ref_action_group, ref_role , active ) VALUES ( 26 , 2, 'f' );
INSERT INTO permissions ( ref_action_group, ref_role , active ) VALUES ( 27 , 2, 'f' );
INSERT INTO permissions ( ref_action_group, ref_role , active ) VALUES ( 28 , 2, 'f' );
INSERT INTO permissions ( ref_action_group, ref_role , active ) VALUES ( 29 , 2, 'f' );
INSERT INTO permissions ( ref_action_group, ref_role , active ) VALUES ( 30 , 2, 'f' );
INSERT INTO permissions ( ref_action_group, ref_role , active ) VALUES ( 31 , 2, 'f' );
INSERT INTO permissions ( ref_action_group, ref_role , active ) VALUES ( 32 , 2, 'f' );