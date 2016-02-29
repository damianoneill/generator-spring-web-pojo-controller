## Sonarqube
To run the code against sonarqube do the following:

    $ docker-compose up -d 
    
This kicks off the mysql and sonarqube containers, then run the maven command to analyse the source code

    $ mvn -Dsonar.host.url=http://192.168.99.100:9000 clean verify sonar:sonar
    
When this completes the output can be viewed here

    http://192.168.99.100:9000/ 
    
    