
INSERT INTO fields VALUES (1, 'Data Solicitação', 3, 1, 0, 1, 0);
INSERT INTO fields VALUES (2, 'Data Solicitação', 3, 1, 0, 2, 0);
INSERT INTO fields VALUES (3, 'Data Solicitação', 3, 1, 0, 3, 0);
INSERT INTO fields VALUES (4, 'Data Solicitação', 3, 1, 0, 1, 0);
INSERT INTO fields VALUES (5, 'Text', 0, 1, 0, 1, 0);
INSERT INTO fields VALUES (6, 'Teste', 0, 0, 0, 1, 0);
INSERT INTO fields VALUES (7, 'Ttttt', 4, 0, 0, 1, 0);
INSERT INTO fields VALUES (8, 'aaaa', 5, 0, 0, 1, 0);

SELECT pg_catalog.setval('fields_id_seq', 8, true);
SELECT pg_catalog.setval('fields_values_id_seq', 1, false);
SELECT pg_catalog.setval('locks_id_seq', 1, false);

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

INSERT INTO sectors VALUES (3, 'DIRETORES', 1);
INSERT INTO sectors VALUES (2, 'RH', 0);
INSERT INTO sectors VALUES (1, 'NTI', 0);

SELECT pg_catalog.setval('sectors_id_seq', 4, true);

INSERT INTO types VALUES (1, 'Solicitação do FIES', 0, 'Requisição para receber o FIES', 1);
INSERT INTO types VALUES (2, 'Aumento Salarial', 0, '', 1);
INSERT INTO types VALUES (3, 'Reembolso Despesas', 0, '', 1);

SELECT pg_catalog.setval('types_id_seq', 3, true);

INSERT INTO types_routes VALUES (3, 1, 2, NULL, 1, 0, 1);
INSERT INTO types_routes VALUES (4, 1, 2, 1, 2, 0, 2);

SELECT pg_catalog.setval('types_routes_id_seq', 4, true);

INSERT INTO users VALUES (1, 'LUCAS TOMASI', '02943508020', '1995-03-15', 1, 0, '<2fcc1dd51cb7514a99f03debf513ca7af3b25669>', 'lt', 'lcstomasi@gmail.com', '5197068174', 0, '7383250983', 1);
INSERT INTO users VALUES (2, 'ARTUR TOMASI', '12312414211', '1995-03-15', 1, 0, '<2fcc1dd51cb7514a99f03debf513ca7af3b25669>', 'art', 'artur.tomasi@outlook.com', '5195090933', 0, '4242398503', 1);
INSERT INTO users VALUES (3, 'ADMINISTRADOR SISTEMA', '00000000000', '1994-10-30', 1, 0, '<2fcc1dd51cb7514a99f03debf513ca7af3b25669>', 'admin', 'admin@helpfin.br', '5198434239', 0, '1232141280', 1);

SELECT pg_catalog.setval('users_id_seq', 3, true);

INSERT INTO values_requests VALUES (8, 2, 15, '2016-08-17');
INSERT INTO values_requests VALUES (9, 2, 16, '2016-08-23');
INSERT INTO values_requests VALUES (10, 2, 17, '2016-09-08');

SELECT pg_catalog.setval('values_requests_id_seq', 10, true);