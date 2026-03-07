CREATE TABLE usuarios (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    activo BOOLEAN,
    nombre VARCHAR(255),
    correo_electronico VARCHAR(255) UNIQUE,
    contrasena VARCHAR(255),
    perfil_id BIGINT,
    CONSTRAINT fk_usuarios_perfil FOREIGN KEY (perfil_id) REFERENCES perfiles(id)
);