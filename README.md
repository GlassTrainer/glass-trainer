angular_bootstrap_spring
========================

Angular JS with Bootstrap and Spring 4 and Spring Security

This example is an angular js single page application (SPA) with bootstrap for the widgets and styling.

Spring 4: 
	Used to create RESTful controller interfaces which in turn gets called through ajax requests.
	
Spring Security 3:
	Uses a simple setup with a http basic entry point configured that will always return http status unauthorised (401),
	this will result in angular js intercepting the response and if a 401 is detected an login event will be fired.
	A basic authentication will be triggered and the user credentials will be validated against the user service that
	is specified in the spring security config. Spring Security session management is being used.

Login Details as per spring security configuration:    
	Username = user    
	Password = password    

Testing
====================
Simply run on the parent pom     
   mvn clean install    
   
This will execute Java and Jasmine tests that will test both java classes and angular js files.
