INSERT INTO user (username, password, enabled, role) values ('user1', 'password', true, 'ROLE_ADMIN');
INSERT INTO user (username, password, enabled, role) values ('user2', 'password', true, 'ROLE_USER');
INSERT INTO user (username, password, enabled, role) values ('firasbk', '12345', true, 'ROLE_ADMIN');
INSERT INTO user (username, password, enabled, role) values ('professor', 'secrett', true, 'ROLE_USER');
INSERT INTO user (username, password, enabled, role) values ('teacher', 'secret', true, 'ROLE_USER');
INSERT INTO user (username, password, enabled, role) values ('user', '123456', true, 'ROLE_USER');

INSERT INTO course (id, user_id, title, description, credits, published, created_at, updated_at) values (1, 1, 'course1', 'description1', '5', 1, CURRENT_TIMESTAMP(),  CURRENT_TIMESTAMP());
INSERT INTO course (id, user_id, title, description, credits, published, created_at, updated_at) values (2, 2, 'course2', 'description2', '4', 0, CURRENT_TIMESTAMP(),  CURRENT_TIMESTAMP());
INSERT INTO course (id, user_id, title, description, credits, published, created_at, updated_at) values (3, 2, 'course3', 'description3', '3',  1, CURRENT_TIMESTAMP(),  CURRENT_TIMESTAMP());
INSERT INTO course (id, user_id, title, description, credits, published, created_at, updated_at) values (4, 3, 'course4', 'description4', '6', 0, CURRENT_TIMESTAMP(),  CURRENT_TIMESTAMP());
INSERT INTO course (id, user_id, title, description, credits, published, created_at, updated_at) values (5, 4, 'course5', 'description4', '6', 0, CURRENT_TIMESTAMP(),  CURRENT_TIMESTAMP());
INSERT INTO course (id, user_id, title, description, credits, published, created_at, updated_at) values (6, 4, 'course6', 'description4', '6', 0, CURRENT_TIMESTAMP(),  CURRENT_TIMESTAMP());
