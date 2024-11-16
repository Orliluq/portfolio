# 👩‍💻 Backend Portfolio (Java) 

![Java](https://img.shields.io/badge/Java-21-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.5-brightgreen)
![Build](https://img.shields.io/github/workflow/status/your-username/halloween-trivia-api/Build%20API%20Project)
![License](https://img.shields.io/badge/license-MIT-blue.svg)
![Contributions](https://img.shields.io/badge/contributions-welcome-brightgreen.svg)
![Swagger](https://img.shields.io/badge/documented%20with-Swagger-orange.svg)

Este proyecto implementa una API RESTful en Java utilizando Spring Boot y PostgreSQL para gestionar los portafolios de usuarios. La API permite a los usuarios crear, leer, actualizar y eliminar información sobre sus proyectos, habilidades, experiencia, educación y contactos. La arquitectura se basa en relaciones entre entidades para representar de forma precisa las conexiones entre los diferentes componentes del portafolio.

## 🛠️ Tecnologías Utilizadas 
- Java: Lenguaje de programación principal.
- Spring Boot: Framework Java para desarrollo de aplicaciones web.
- Spring Data JPA: Abstracción de la persistencia de datos.
- PostgreSQL: Base de datos relacional.
- Maven/Gradle: Gestor de dependencias.

## 🖥️ Estructura del Proyecto 
### Entidades:
- User: Representa a un usuario con atributos como nombre, correo electrónico y una relación con sus proyectos, experiencias, educación, habilidades y contacto.
- Project: Representa un proyecto con atributos como nombre, descripción, URL, etc.
- Experience: Representa una experiencia laboral con atributos como empresa, puesto, etc.
- Education: Representa una formación académica con atributos como institución, título, etc.
- Skill: Representa una habilidad con atributos como nombre y nivel.
- Contact: Representa la información de contacto del usuario.
- Controladores: Expone los endpoints REST para interactuar con las entidades.
- Servicios: Contiene la lógica de negocio para las operaciones CRUD.
- Repositorios: Interactúa con la base de datos a través de JPA.

### Relaciones entre Entidades:
- Uno a muchos: Un usuario puede tener muchos proyectos, experiencias, educaciones y habilidades.
- Uno a uno: Un usuario tiene un único contacto.
- Muchos a uno: Un proyecto, experiencia, educación o habilidad pertenece a un único usuario.
  
## 📋 Diagramas de entidad-relación (ERD): 
Un diagrama visual puede ayudar a comprender mejor las relaciones entre las entidades.
![erd](https://github.com/user-attachments/assets/f4fbb682-bcb5-41b6-ba7e-022bf890fcee)

## 📂 Instalación y Ejecución 
1. Clonar el repositorio:
```git clone https://github.com/Orliluq/portfolio.git```

2. Instalar dependencias:
```cd portfolio-backend```

- Para Maven: `mvn install`
- Para Gradle: `./gradlew build` 

3. Iniciar la aplicación:
- Para Maven: `mvn spring-boot:run`
- Para Gradle: `./gradlew bootRun`

## Endpoints de la API
- Usuarios:
+ `GET /users`: Obtener todos los usuarios
+ `GET /users/{id}`: Obtener un usuario por ID
+ `POST /users`: Crear un nuevo usuario
+ `PUT /users/{id}`: Actualizar un usuario
+ `DELETE /users/{id}`: Eliminar un usuario
- Proyectos, Experiencias, Educación, Habilidades, Contactos:... (similar a los usuarios)

# 📝 API Documentation
The API is documented using Swagger/OpenAPI. You can access the API documentation via:

Swagger UI URL: `http://localhost:8080/swagger-ui.html`
![swagger](https://github.com/user-attachments/assets/c5ca37a5-b13d-41bc-aa3b-370b63888854)

# 🏷️ License
This project is licensed under the MIT License. See the LICENSE file for details.
