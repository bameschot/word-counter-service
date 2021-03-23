# word-counter-service
Test service for Jakarta EE / Open Liberty

# Running the server

The server can be started by using the mvn liberty:run command in the root (pom) directory of the project. This starts the server on localhost:9080. The services can be reached on: http://localhost:9080/word-counter/api/word-counter. 


# Running the unittests

The unittests can be started by either running the server in dev mode by using the mvn liberty:dev command or, alternatively the mvn verify command. 

# OpenAPI

The service exposes an OpenAPI page for the purpose of documenting and testing the three operations on http://localhost:9080/openapi/ui/

