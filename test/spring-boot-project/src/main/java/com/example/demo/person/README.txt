A code generator flag will be be used to EITHER generate :

In src/main/java/com/example/demo/person/
    PersonService.java or PersonServiceAsynch.java

Depending on that same flag
    PersonController and PersonControllerTestDocumentation
will refer either
    PersonService or PersonServiceAsynch
as appropriate.