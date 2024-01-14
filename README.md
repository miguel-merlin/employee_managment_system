# Employee Management System
Spring based employee management system. Basic implementation of a CRUD service that creates, reads,updates and deletes
employees. The application will be used to manage employees at Gamma Computing. The application runs on a 
docker-container that will initialize an instance of a PostgreSQL database.

## Running the application
Initialize the docker container.
```
docker-compose up -d
```
Initialize the application
```
./gradlew bootRun
```