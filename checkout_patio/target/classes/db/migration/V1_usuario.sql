INSERT INTO usuarios (id, username, senha, ativo)
VALUES (1, 'admin', '$2a$10$4mhF6f2nUCe2qLlt6..wmOp3Ti7Q.X7Ep7FVlZx.aD9Lby6SleHJe', true);

INSERT INTO usuario_roles (usuario_id, role) VALUES (1, 'ROLE_USER');
