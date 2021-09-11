# TaskFromSEBGC

 Techstack:
  - backend: Java, Spingboot, Maven;
  - frontend: Angular;
  - database: MySQL;
  
  
Prerequisites:
 - created database "etoolapp";
 - created user "etoolapp" with password "etoolapp" (and granted all privillegies);
 - created schema "etoolapp";
 
 
App configured to run only on localhost:
  - backend on port 8080;
  - frontend an port 4200;
  - database on port 3306;
  
  
Launching commands:
  - backend: 
		- open project in IntellijIdes and run "ProductRecommendationToolApplication" or
		- type in terminal "mvn spring-boot:run";
		
  - frontend:
		- type in terminal "npm install" (only once);
		- type in terminal "ng serve";
  - datadase:
		- launch MySQL Workbench;
