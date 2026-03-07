CREATE TABLE topicos (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    titulo VARCHAR(255),
    mensaje TEXT,
    fecha DATETIME,
    estado VARCHAR(50),
    usuario_id BIGINT,
    CONSTRAINT fk_topicos_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);