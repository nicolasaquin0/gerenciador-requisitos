CREATE TABLE usuario (
    id_usuario    SERIAL PRIMARY KEY,
    nome          VARCHAR(100) NOT NULL,
    email         VARCHAR(120) NOT NULL UNIQUE,
    senha_hash    CHAR(64)     NOT NULL,
    senha_salt    CHAR(32)     NOT NULL,
    perfil        VARCHAR(5)   NOT NULL CHECK (perfil IN ('ADMIN', 'USER')),
    ativo         BOOLEAN      NOT NULL DEFAULT TRUE,
    data_criacao  TIMESTAMP    NOT NULL DEFAULT NOW()
);

INSERT INTO usuario (nome, email, senha_hash, senha_salt, perfil)
VALUES ('Administrador', 'admin@requisitos.com',
        '9e81d60da2e65fe629def62e0da05fc78694f15164a629c924f8e91519e77e88',
        'b69254874abdc45ece3f4f77d26e7f5f', 'ADMIN');
