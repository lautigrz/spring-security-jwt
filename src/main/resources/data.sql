INSERT INTO roles (name) VALUES ('ADMIN'), ('USER'), ('INVITED');

INSERT INTO users (email, username, password) VALUES
('admin@example.com', 'admin', '$2a$10$DtRNEMxRC44YVplp.eBjR.O4PAznMd.DyMBy5ntog6FjPzFgXjP9S'),
('user@example.com', 'user', '$2a$10$DtRNEMxRC44YVplp.eBjR.O4PAznMd.DyMBy5ntog6FjPzFgXjP9S'),
('guest@example.com', 'guest', '$2a$10$DtRNEMxRC44YVplp.eBjR.O4PAznMd.DyMBy5ntog6FjPzFgXjP9S'),
('lauti@example.com', 'lautigrz', '$2a$10$DtRNEMxRC44YVplp.eBjR.O4PAznMd.DyMBy5ntog6FjPzFgXjP9S');;

INSERT INTO user_roles (user_id, role_id) VALUES
                                              (1, 1), -- admin → ADMIN
                                              (2, 2), -- user → USER
                                              (3, 3), -- guest → INVITED
                                              (4, 1); -- lautigrz → admin