CREATE Schema "FORTEST" ;
--if not exists "fortest"
--CREATE Schema if not exists "universityaudit";

DROP TABLE IF EXISTS fortest.person;

CREATE TABLE fortest.person
(
    id serial NOT NULL,
    name character varying,
    PRIMARY KEY (id)
);


DROP TABLE IF EXISTS fortest.users;
CREATE TABLE fortest.users
(
    id serial NOT NULL,
    username character varying,
    first_name character varying,
    last_name character varying,
    email character varying,
    password character varying,
    enabled boolean,
    PRIMARY KEY (id)
);
DROP TABLE IF EXISTS fortest.roles;
CREATE TABLE fortest.roles
(
    id serial NOT NULL,
    name character varying,
    PRIMARY KEY (id)
);
DROP TABLE IF EXISTS fortest.users_roles;
CREATE TABLE fortest.users_roles
(
    id serial NOT NULL,
    user_id integer,
    role_id integer,
    PRIMARY KEY (id)
);

ALTER TABLE fortest.users_roles
    ADD CONSTRAINT fortest.users_roles_users_fk FOREIGN KEY (user_id)
        REFERENCES fortest.users (id)
        ON UPDATE CASCADE
        ON DELETE CASCADE;

ALTER TABLE fortest.users_roles
    ADD CONSTRAINT fortest.users_roles_roles_fk FOREIGN KEY (role_id)
        REFERENCES fortest.roles (id)
        ON UPDATE CASCADE
        ON DELETE CASCADE;