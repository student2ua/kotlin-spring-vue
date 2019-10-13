
INSERT INTO fortest.person (name) VALUES ('John'), ('Griselda'), ('Bobby');

INSERT INTO fortest.users (id, username, first_name, last_name, email, password, enabled)
VALUES (1, 'tor', 'tor', 'Admin', 'admin@example.com', '$2a$10$3MgYBk.Vzl7m1l8EokECZeLYKPUMJp5QcR/IMebCVpIRZNFyGA/.C', true);
INSERT INTO fortest.roles (name) VALUES ('ROLE_USER'), ('ROLE_ADMIN');
INSERT INTO fortest.users_roles (user_id, role_id) VALUES (1, 2);