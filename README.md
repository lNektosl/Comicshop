# Comic Shop – Spring Boot Web Application

A full-stack web application for managing an online comic book store.
Built with Spring Boot, Spring Security, Thymeleaf, JPA/Hibernate, Liquibase, PostgreSQL, and Docker.  


# Login Information:

ADMIN:
<p>Username: admin</p>
<p>Password: admin</p>


Comic Shop is a layered Spring Boot application that allows users to browse and purchase comics, while administrators manage the catalog and orders.

The project demonstrates:

- Clean layered architecture

- Database versioning with Liquibase

- Role-based security

- Server-side rendering with Thymeleaf

- Dockerized deployment

# User Features

- Registration & login

- Secure authentication (Spring Security)

- Browse:

    - Comics

    - Authors

    - Artists

    - Publishers

    - Series

- View detailed comic pages

- Add comics to cart

- Place orders

- Personal cabinet:

    - View order history

    - Manage profile

    - Follow authors/artists/series


# Admin Features

- Admin dashboard

- Full CRUD management for:

    - Comics

    - Authors

    - Artists

    - Publishers

    - Series

- Order management

- Admin registration

- Role-based access control

# Project architecture


The project follows a classic layered architecture

* Layers

- Controller – Handles HTTP requests

- Service – Business logic

- Repository – Data access (Spring Data JPA)

- Entity – Database models

- DTO + Mapper – Data transfer & conversion

- Config – Security & application configuration

# Tech Stack

## Backend

- Java

- Spring Boot

- Spring MVC

- Spring Security

- Spring Data JPA

- Hibernate

- Liquibase

## Frontend

- Thymeleaf

- HTML / CSS

- Static resources

## Database

- PostgreSQL

## DevOps

- Docker

- Docker Compose

- Maven

# Database Initialization

The database is initialized by Docker (PostgreSQL container) using SQL scripts.

In docker-compose.yml

# Running with Docker

## Build and start containers
``` 
docker-compose up --build 
```
This will:

- Start PostgreSQL

- Run Liquibase migrations

- Launch the Spring Boot application

## Open the application

```
http://localhost:8080
```

# Learning Goals Demonstrated

- Full-stack Spring Boot development

- Secure authentication & authorization

- Database version control with Liquibase

- Clean architecture & separation of concerns

- Dockerized environment

- Production-style configuration

# License

Originally built in 2023

This project is for educational and portfolio purposes.