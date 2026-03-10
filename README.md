# 🌐 ForoHub API

API REST de foro de discusión desarrollada con Java y Spring Boot como parte del challenge de Oracle Next Education (ONE).

## 📋 Descripción

ForoHub permite gestionar tópicos de discusión con autenticación segura mediante JWT. Los usuarios pueden crear, listar, actualizar y eliminar tópicos organizados por cursos.

## 🛠️ Tecnologías

- Java 17
- Spring Boot 3
- Spring Security + JWT
- Spring Data JPA + Hibernate
- MySQL 8
- Flyway
- Lombok
- Swagger/OpenAPI

## 🚀 Cómo ejecutar

1. Clona el repositorio:
```bash
git clone https://github.com/tu-usuario/ForoHub.git
```

2. Crea la base de datos en MySQL:
```sql
CREATE DATABASE foro_hub;
```

3. Configura `application.yml` con tus credenciales de MySQL.

4. Ejecuta el proyecto:
```bash
mvn spring-boot:run
```

5. Accede a Swagger:
```
http://localhost:8080/swagger-ui/index.html
```

## 🔐 Autenticación

### Registrar usuario
```http
POST /usuarios
Content-Type: application/json

{
    "nombre": "Tu Nombre",
    "correoElectronico": "correo@ejemplo.com",
    "contrasena": "123456"
}
```

### Obtener token
```http
POST /login
Content-Type: application/json

{
    "correo": "correo@ejemplo.com",
    "contrasena": "123456"
}
```

Usa el token en las siguientes peticiones:
```
Authorization: Bearer {token}
```

## 📌 Endpoints

### Tópicos
| Método | URL | Descripción |
|--------|-----|-------------|
| POST | /topicos | Crear tópico |
| GET | /topicos | Listar tópicos |
| GET | /topicos/{id} | Detalle de tópico |
| PUT | /topicos/{id} | Actualizar tópico |
| DELETE | /topicos/{id} | Eliminar tópico |

### Usuarios
| Método | URL | Descripción |
|--------|-----|-------------|
| POST | /usuarios | Registrar usuario |
| GET | /usuarios | Listar usuarios |
| DELETE | /usuarios/{id} | Eliminar usuario |

## 🗄️ Base de datos

![Diagrama](https://i.imgur.com/placeholder.png)

Las migraciones Flyway crean automáticamente las tablas al iniciar la aplicación.

## 👨‍💻 Autor

**Emmanuel Medina**

## 📄 Licencia

MIT License - Copyright (c) 2026 Emmanuel Medina