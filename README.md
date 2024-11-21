# Instructions for local setup

The following page contains instructions for setting up the project for local development.

## Prerequisites
 - Docker has been installed
 - Java 23 has been installed

## Start up

Before startup be sure to run `docker-compose up -d` to create local containers for Postgres database and Redis (used for maintaining sessions).
To start up the server locally you will need to set profile to local (`SPRING_PROFILES_ACTIVE=local`). On start up the server will create required tables and insert sectors to database.
The server will start on port **8080**.

Swagger is available on path: /swagger-ui/index.html/

## Architecture

This project has been written in accordance to hexagonal architecture and has been separated into following modules:
- Adapter.repository: Contains only database operations. Business logic shall not be implemented in this layer. 
  Mapping to domain layer objects will be performed here.
- Adapter.web: Contains REST controllers and validation
- Application: Contains start-up application and integration tests
- Domain: Contains ports and use cases. Ports shall be used to communicate with the database, every service must implement
  an use case. These use cases will be used in the web adapter.
- Models: Contains request and response classes as well as other models used in web adapter.
