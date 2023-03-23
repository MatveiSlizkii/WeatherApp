# Weather Application
## Description
The application is designed to provide the latest weather data, average weather data for a certain period of time.

## Features
- CRUD operation for Weather
   (opportunity info, avr)
- get for Weather
- UUID as an ID parameter
- custom Exception
- UnitTest (Junit 5 + Mockito)
- Docker compose


## Tech
Receipt application uses next technologies:
- Java 17
- Spring Boot
- Hibernate
- PostgreSQL
- Maven
- Gson
- Open API
- RESTFUL
- JUnit 5
- Mockito
- Docker compose


## GUIDE

- run sql "weather-service.sql" in folder "ddl" *.sql (1 bd -> 1 schema -> 1 table)
- RESTFUL info in folder "spec" *.yaml
- unitTests in folder "test"
## Run application on Spring Boot
### Normal application launch
First you need to import the sql file into PostgreSQL.
The project can be run through the development environment, or you can make and use a jar file.
To check the application in Postman, you can use the ready-made collection weather-service.postman_collection.json
### Running with docker
Create project jar file via maven package. There may be problems with connecting to the database. Connecting to PostgreSQL requires changing the host from localhost to db (the port remains the same).
Then we run docker-compose.yml. Images for PostgreSQL, pgAdmin, proxy, minio, swagger and java project will be created and containers will be raised.
To connect to pgAdmin, you need to use localhost:8082. Connection data is specified in docker-compose.yml.


