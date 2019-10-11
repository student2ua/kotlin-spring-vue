DROP TABLE IF EXISTS fortest.person;

CREATE TABLE fortest.person
(
    id serial NOT NULL,
    name character varying,
    PRIMARY KEY (id)
);
INSERT INTO fortest.person (name) VALUES ('John'), ('Griselda'), ('Bobby');

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
    ADD CONSTRAINT users_roles_users_fk FOREIGN KEY (user_id)
        REFERENCES fortest.users (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE;

ALTER TABLE fortest.users_roles
    ADD CONSTRAINT users_roles_roles_fk FOREIGN KEY (role_id)
        REFERENCES fortest.roles (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE;
INSERT INTO fortest.users (id, username, first_name, last_name, email, password, enabled)
VALUES (1, 'tor', 'tor', 'Admin', 'admin@example.com', '$2a$10$3MgYBk.Vzl7m1l8EokECZeLYKPUMJp5QcR/IMebCVpIRZNFyGA/.C', true);
INSERT INTO fortest.roles (name) VALUES ('ROLE_USER'), ('ROLE_ADMIN');
INSERT INTO fortest.users_roles (user_id, role_id) VALUES (1, 2);