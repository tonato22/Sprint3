-- Cria usuário admin
INSERT INTO usuarios (id, username, senha, ativo)
VALUES (
           1,
           'admin',
           '$2b$10$1VuyrWf1aos0JjXNOcBsMeKLSkzYvL.R5/RghFaLF.qRrRBAASVvS',
           TRUE
       );

-- Adiciona role ROLE_USER
INSERT INTO usuario_roles (usuario_id, role) VALUES (1, 'ROLE_USER');

-- Opcional: também pode adicionar ROLE_ADMIN
INSERT INTO usuario_roles (usuario_id, role) VALUES (1, 'ROLE_ADMIN');
