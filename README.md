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

2. docker run -p 8083:8080 -e KEYCLOAK_USER=admin -e KEYCLOAK_PASSWORD=secret jboss/keycloak

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

The Spring Project will be available at http://localhost:8080 
with every routes located after /api/v1/


## API Documentation
*The following endpoints are set in this startcode.*

[...] = headers required.

## Project Endpoints
| Method | URL                                  | Request Body (JSON)                 | Response (JSON)     | 
|--------|--------------------------------------|-------------------------------------|---------------------|
| DELETE | /api/v1/projects/{id}                | [Bearer token]                      |                     | 
| GET    | /api/v1/projects/{id}                |                                     | Project             | 
| GET    | /api/v1/projects                     |                                     | Collection(Project) | 
| GET    | /api/v1/search/name                  |                                     | Collection(Project) | 
| GET    | /api/v1/search/field                 |                                     | Collection(Project) | 
| PATCH  | /api/v1/projects/{pId}/{fId}/respond | (Boolean) accepted                  |                     | 
| POST   | /api/v1/projects                     | (Project) project                   |                     |
| POST   | /api/v1/projects/{pId}/{fId}         | (String) motivation, [Bearer token] |                     | 
| PUT    | /api/v1/projects/{id}                | (ProjectDTO) ProjectDTO             |                     | 

* Project: Should exclude relations and can exclude images(String[]),

## Freelancer Endpoints
| Method | URL                              | Request Body (JSON)                              | Response (JSON)        | 
|--------|----------------------------------|--------------------------------------------------|------------------------|
| GET    | /api/v1/freelancers              |                                                  | Collection<Freelancer> | 
| GET    | /api/v1/freelancers/profile/{id} |                                                  | Freelancer             | 
| GET    | /api/v1/freelancers/search       | @Param (String) username                         | Collection<Freelancer> | 
| POST   | /api/v1/freelancers              | [Bearer token], (String) email,(String) username |                        |
| PUT    | /api/v1/freelancers/profile/{id} | (FreelancerDTO) freelancerDTO, [Bearer token]    |                        |
| DELETE | /api/v1/freelancers/{id}         | [Bearer token]                                   |                        |

## Chat Endpoints
| Method | URL                    | Request Body (JSON) | Response (JSON)     | 
|--------|------------------------|---------------------|---------------------|
| POST   | /api/v1/messages       | (Message) message   |                     |
| PUT    | /api/v1/messages/{id}  | (Message) message   |                     |
| DELETE | /api/v1/messages/{id}  |                     |                     |

## Message Endpoints
| Method | URL                | Request Body (JSON) | Response (JSON)  | 
|--------|--------------------|---------------------|------------------|
| GET    | /api/v1/chats      |                     | Collection<Chat> | 
| GET    | /api/v1/chats/{id} |                     | Chat             | 
| POST   | /api/v1/chats      | (Chat) chat         |                  |
| PUT    | /api/v1/chats/{id} | (Chat) chat         |                  |
| DELETE | /api/v1/chats/{id} |                     |                  |

### Missing or not Completed

** Controllers **

Chat+Message:
Chat is currently very basic.
- A proper chat system, utilizing Sockets
- Proper Auth and control of each creating user.
- Need to be part of project to see chat.

Freelancer:
- retrieve need to respect hidden users.

Freelancer_history:
- Routes/methods for creating, updating and retrieving history of freelancer
  - along with proper authorization

Project:
- Update: check for permission, could be owner or member roles
- respond to application: check for authorization (owner or member roles)
- get application status
- Check for member in get (so private information is not public)


DTO's and mappers fully implemented and utilized.

Swagger:
- Proper API responses
- Body, Header requirements
