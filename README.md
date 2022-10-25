# legalt-backend





## Getting started Locally

### Dependencies
-  Docker
  - Keycloak server
  - Postgres server

Our application runs using a Postgres database and Keycloak Authentication server,
therefore for running it locally we need to launch both.

For ease of use we have included a docker-compose.yaml file for Postgres and pgadmin instance,
and filled in the application.properties with default values for local development.
First we need to launch the database
1. docker-compose up -d

For the sake of simplicity we decided to run the keycloak instance directly from the image.

2. docker run -p 8083:8080 -e KEYCLOAK_USER=admin -e KEYCLOAK_PASSWORD=secret c jboss/keycloak

3. After the image have been build we need to login to the admin console on keycloak
    http://localhost:8083/auth/admin

4. create a new realm called "lagalt"

5. In the Lagalt Realm Settings => Login tab, set User registration to ON

5. In the side tab called Clients: create a new client called "client" with the root url of http://localhost:5173
    which is our local frontends port. 

6. In the newly created client, Go the roles tab and add a new role called "client-user"

7. Then in the side tab called Roles: Default roles tab => select the newly create client int the client roles selector
    Add the client-user to the client's default roles.

8. run the Spring project

## API Documentation
*The following endpoints are set in this startcode.*

[...] = headers required.

## Project Endpoints
| Method | URL                                  | Request Body (JSON)  | Response (JSON)   | 
|--------|--------------------------------------|----------------------|-------------------|
| DELETE | /api/v1/projects/{id}                |                      |                   | 
| GET    | /api/v1/projects/{id}                | Authentication (1.0) |                   | 
| GET    | /api/v1/projects                     | [x-access-token]     |                   | 
| GET    | /api/v1/search/name                  | [x-access-token]     |                   | 
| GET    | /api/v1/search/field                 |                      |                   | 
| PATCH  | /api/v1/projects/{pId}/{fId}/respond |                      | application/json  | 
| POST   | /api/v1/projects                     |                      | application/json  |
| GET    | /api/v1/projects/{pId}/{fId}         |                      | application/json  | 
| PUT    | /api/v1/projects/{id}                |                      | application/json  | 

## Freelancer Endpoints
| Method | URL              | Request Body (JSON)     | Response (JSON)          | 
|--------|------------------|-------------------------|--------------------------|
| POST   | /api/login       | Authentication (1.0)    | Authentication (1.1)     | 
| GET    | /api/ext         |                         | External Fetch (2.0)     | 
| GET    | /api/info/user   | [x-access-token]        | User Fetch  (3.0)        | 