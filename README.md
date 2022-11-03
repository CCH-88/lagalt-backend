# Lagalt Backend (In development)
![Lagalt_-_A_Web_App_for_project_sharing](https://user-images.githubusercontent.com/86660568/199735050-219c01ad-b980-4be2-8e56-d1f2609f1748.png)

## Description
A Web application that allows people to create, share and join projects with other like minded people.... A freelancer heaven

Features:

- Login function: It is possible to login a create a profile for other people to inspect. Alternatively you can se your profile to hidden
- Search function: Allows users to search for projects and other people
- Creating projects: Create a project within different categories i.e. Film making, web-development
- Portfolio overview: Users can add projects to their portfolio which can be seen by others
- Chat function: In a project people can chat with each other

## Technology
<p align="left"> <a href="https://www.w3.org/html/" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/html5/html5-original-wordmark.svg" alt="html5" width="40" height="40"/> <a href="https://www.w3schools.com/css/" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/css3/css3-original-wordmark.svg" alt="css3" width="40" height="40"/> </a> </a> <a href="https://tailwindcss.com/" target="_blank" rel="noreferrer"> <img src="https://www.vectorlogo.zone/logos/tailwindcss/tailwindcss-icon.svg" alt="tailwind" width="40" height="40"/> </a> </a> <a href="https://reactjs.org/" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/react/react-original-wordmark.svg" alt="react" width="40" height="40"/> </a> <a href="https://developer.mozilla.org/en-US/docs/Web/JavaScript" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/javascript/javascript-original.svg" alt="javascript" width="40" height="40"/> </a> <a href="https://spring.io/" target="_blank" rel="noreferrer"> <img src="https://www.vectorlogo.zone/logos/springio/springio-icon.svg" alt="spring" width="40" height="40"/> </a> <a href="https://www.postgresql.org" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/postgresql/postgresql-original-wordmark.svg" alt="postgresql" width="40" height="40"/> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/docker/docker-original-wordmark.svg" alt="docker" width="40" height="40"/> </a> <a href="https://www.docker.com/" target="_blank" rel="noreferrer"> <a href="https://www.w3.org/html/" target="_blank" rel="noreferrer"> </p>

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


## Endpoint overview
https://lagalt.onrender.com/swagger-ui/index.html#/

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
