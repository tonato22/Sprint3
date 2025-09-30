CREATE TABLE usuarios (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          username VARCHAR(255) NOT NULL UNIQUE,
                          senha VARCHAR(255) NOT NULL,
                          ativo BOOLEAN DEFAULT TRUE
);

CREATE TABLE usuario_roles (
                               usuario_id BIGINT NOT NULL,
                               role VARCHAR(255) NOT NULL,
                               FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE
);
