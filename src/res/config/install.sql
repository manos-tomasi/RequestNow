CREATE TABLE categories (
  id SERIAL NOT NULL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  state INT NOT NULL,
  info TEXT NULL );


CREATE TABLE types (
  id SERIAL NOT NULL PRIMARY KEY,
  name VARCHAR(255) NOT NULL UNIQUE,
  state INT NOT NULL,
  info TEXT NULL,
  ref_category INT NOT NULL REFERENCES categories (id) );


CREATE TABLE fields (
  id SERIAL NOT NULL PRIMARY KEY,
  label VARCHAR(255) NULL,
  type INT NULL,
  required INT NOT NULL,
  state INT NOT NULL,
  ref_type INT NOT NULL REFERENCES types (id),
  sequence int default 0);


CREATE TABLE sectors (
  id SERIAL NOT NULL PRIMARY KEY,
  name VARCHAR(255) NOT NULL UNIQUE,
  state INT NOT NULL );


CREATE TABLE users (
  id SERIAL NOT NULL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  cpf VARCHAR(11) NOT NULL UNIQUE,
  birth_date DATE NOT NULL,
  gender INT NOT NULL,
  state INT NOT NULL,
  password VARCHAR(255) NOT NULL,
  login VARCHAR(45) NOT NULL UNIQUE,
  mail VARCHAR(255) NOT NULL,
  phone VARCHAR(45) NOT NULL,
  role INT NOT NULL REFERENCES roles (id),
  rg VARCHAR(10) NOT NULL UNIQUE,
  ref_sector INT NOT NULL REFERENCES sectors (id) );


CREATE TABLE types_routes (
  id SERIAL NOT NULL PRIMARY KEY,
  ref_sector INT NULL  REFERENCES sectors (id),
  ref_type INT NULL  REFERENCES types (id),
  ref_user INT NULL REFERENCES users (id),
  sequence INT NOT NULL,
  state INT NOT NULL,
  days INT NOT NULL DEFAULT 0 );


CREATE TABLE requests (
  id SERIAL NOT NULL PRIMARY KEY,
  ref_type INT NOT NULL REFERENCES types (id),
  ref_user INT NOT NULL REFERENCES users (id),
  dt_start TIMESTAMP NOT NULL,
  dt_end TIMESTAMP NULL,
  state INT NOT NULL );


CREATE TABLE values_requests (
  id SERIAL NOT NULL PRIMARY KEY,
  ref_field INT NOT NULL REFERENCES fields (id),
  ref_request INT NOT NULL REFERENCES requests (id),
  value TEXT NULL );

CREATE TABLE requests_routes (
  id SERIAL NOT NULL PRIMARY KEY,
  ref_request INT NOT NULL REFERENCES requests (id),
  ref_type_route INT NOT NULL REFERENCES types_routes (id),
  date_in TIMESTAMP NULL,
  date_out TIMESTAMP NULL,
  state INT NOT NULL,
  ref_user INT REFERENCES users (id),
  info TEXT NULL,
  sequence  INT );


CREATE TABLE fields_values (
  id SERIAL NOT NULL PRIMARY KEY,
  ref_field INT NOT NULL REFERENCES fields (id),
  state INT NOT NULL,
  value VARCHAR(255) NULL );


CREATE TABLE locks (
  id SERIAL NOT NULL PRIMARY KEY,
  date TIMESTAMP NOT NULL,
  ref_request_route INT NOT NULL REFERENCES requests_routes (id),
  ref_user INT NOT NULL REFERENCES users (id) );

CREATE TABLE roles (
    id SERIAL NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    state INT NOT NULL );

CREATE TABLE groups (
    id SERIAL NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    origin VARCHAR(255) NOT NULL );

CREATE TABLE actions (
    id SERIAL NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL );

CREATE TABLE actions_groups (
    id SERIAL NOT NULL PRIMARY KEY,
    ref_action INT NOT NULL REFERENCES actions (id),
    ref_group  INT NOT NULL REFERENCES groups  (id) );

CREATE TABLE roles (
    id SERIAL NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL ,
    state int not null);

CREATE TABLE permissions (
    id SERIAL NOT NULL PRIMARY KEY,
    ref_action_group INT NOT NULL REFERENCES actions_group (id),
    ref_role  INT NOT NULL REFERENCES roles  (id) );
