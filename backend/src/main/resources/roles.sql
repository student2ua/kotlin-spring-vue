CREATE TABLE public.users
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

CREATE TABLE public.roles
(
     id serial NOT NULL,
     name character varying,
     PRIMARY KEY (id)
);

CREATE TABLE public.users_roles
(
     id serial NOT NULL,
     user_id integer,
     role_id integer,
     PRIMARY KEY (id)
);

ALTER TABLE public.users_roles
     ADD CONSTRAINT users_roles_users_fk FOREIGN KEY (user_id)
     REFERENCES public.users (id) MATCH SIMPLE
     ON UPDATE CASCADE
     ON DELETE CASCADE;

ALTER TABLE public.users_roles
     ADD CONSTRAINT users_roles_roles_fk FOREIGN KEY (role_id)
     REFERENCES public.roles (id) MATCH SIMPLE
     ON UPDATE CASCADE
     ON DELETE CASCADE;

INSERT INTO roles (name) VALUES ('ROLE_USER'), ('ROLE_ADMIN');

INSERT INTO users_roles (user_id, role_id) VALUES (1, 2);