# generator-spring-web-pojo-controller
Spring Web Scaffolding System for a Pojo based Controller

## Setup
   
Install yeoman:
   
     $ npm install -g yo
   
Install generator:
   
     $ npm install -g spring-web-pojo-controller
   
     
## Using the generator
When running the generator with the following input
     
````
$ yo spring-web-pojo-controller
? What's the name of the noun to be modelled? Person
? What's the type (if primitive, use its wrapper class) of the variable used as an identifier in the noun? String
? What is your default Java package name? (com.example.demo)
````

This generates an set of classes, with a service class that needs implemented with the business logic.

     @Service
     public class PersonService { }

The generator creates a Controller that exposes a collection resource at /persons. 
The path is derived from the uncapitalized, pluralized, simple class name of the noun class being managed. 
It also exposes an item resource for each of the items managed by the repository under the URI template /persons/{id}.
 
 
## Build and compile github project

     $ npm test
     
And then link to you can test it

     $ npm link
     
And then run it

     $ yo spring-web-pojo-controller
 
 
## References

* http://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/CrudRepository.html 
* http://docs.spring.io/spring-data/rest/docs/current/reference/html/#repository-resources 
     
