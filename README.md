# generator-spring-web-pojo-controller
Spring Web Scaffolding System for a Pojo based Controller

## Build and compile

     $ npm test
     
And then link to you can test it

     $ npm link
     
And then run it

     $ yo generator-spring-web-pojo-controller
     
     
# Using the generator
When running the generator with the following input
     
````
$ yo spring-web-pojo-controller
? What's the name of the noun to be modelled? Order
? What's the type (if primitive, use its wrapper class) of the variable used as an identifier in the noun? Long
? What is your default Java package name? (com.acme.controller)
````

This generates an set of classes, with a primary abstract class that needs extended with the business logic.

     public abstract class AbstractOrderController extends CrudController<Order, Long> { }

The generator creates a Controller that exposes a collection resource at /orders. 
The path is derived from the uncapitalized, pluralized, simple class name of the noun class being managed. 
It also exposes an item resource for each of the items managed by the repository under the URI template /orders/{id}.
     
**When extending the Abstract Class you need to include the @RestController annotation in your implementation for it be
 wired properly by Spring.**
 
 
## References

* http://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/CrudRepository.html 
* http://docs.spring.io/spring-data/rest/docs/current/reference/html/#repository-resources 
     
