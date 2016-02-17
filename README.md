# generator-spring-web-pojo-controller
Spring Web Scaffolding System for a Collection Pojo based Controller

## Setup
   
Install node:

     $ brew install node 
     
Install yeoman:
   
     $ sudo npm install -g yo
   
Install generator:
   
     $ sudo npm install -g spring-web-pojo-controller
   
     
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
The path is derived from the uncapitalized, pluralized, simple class name of the noun class being managed. For example
If the Noun is Person the Collection will be people, if the Noun is Ethernet the Collection will be ethernet.

It also exposes an item resource for each of the items managed by the repository under the URI template /people/{id}.
Below is the interface operations that represent the CRUD operations that gets created by the tool.

````
T create(T noun);
ResponseEntity<T> findOne(ID id);
List<T> findAll();
List<T> findPaginated(int page, int size);
T update(T noun);
void delete(ID id);
void deleteAll();
````

And optionally 
 
```` 
List<T> findFiltered(@RequestParam("filter") T' filter)
````

## Build and compile github project

Setup the dependencies

     $ npm install
     
Build and run the tests

     $ npm test
     
And then link to you can test it

     $ npm link
     
And then run it

     $ yo spring-web-pojo-controller
 
 
## References

     
