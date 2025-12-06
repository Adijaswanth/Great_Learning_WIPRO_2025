Traditional Programming which we were doing with Servlet based  Spring MVC

Dispatcher / front Controller , Here each request blocks a thread until the operation finishes.

Reactive (Non-Blocking) Programming : Code reacts to events/ data asynchronously which uses event loop 
model and processes thousands of concurrent requests efficiently


Non-Blocking -- Operations do not block the executing thread, allowing the server to handle a large number of 
concurrent requests with a small number of thread improves the efficiency 

Asynchronous Data Streams : Data is treated as a stream  and processing occurs as data becomes available rather than
waiting for the entire dataset( Like a movie you watch on netflix) 


Netflix  -- millions of users streaming at once , Instead of blocking threads per request , Netflix reacts
to events and streams asynchronously 

 There some concepts :
 
 Publisher : Emits the data (flux / Mono)  -- Reactive Streams  flux will emit multiple items whereas mono emits single item   
 for eg : Mono.just("Happy Teachers Day");
 Flux.just("Priya", " Sakshi" , "Shubham");
 
 Subscriber : Who will consumes the data 
 BackPressure : Subscriber controls the data flow 
 Operators : Transform/react to streams (map, filter)
 
 
 Spring WebFlux APIs :  A reactive web framework introduced in Spring 5 version which runs on Netty server by default instead of tomcat 
 It supports Reactive Streams API(Publisher , Subscriber) , works with Flux (0..N) and Mono(0..1)types
 
 It is ideal for applications with high concurrency & streaming(like APIS with high throughput , chating based)
 
 
 core java you were directly interacting with java and database
 
 frontend ---> server ---> filter (JWT )---> controller ---> service --> repository ---> (DTOs) --- > database
 
 Reactive Streams :  FLUX and MONO
 
 1) Dependencies : Starter-webflux 
 2) Reactive Controller  -- (@RequestMapping)
 3) Router Functions -- (which is an alternative to controllers)
 4) Error handling  
 5) Testing the reactive Endpoints   (WebTestClient) 
 6) Reactive Data Repositories -- (mongodb , Mysql etc...) r2dbc R2DBC stands for Reactive Relational Database Connectivity
 7) Reactive Streams Operators  ( map, filter, subscribe)etc..
 
 
 For Reactive Microservices   we can add spring webflux + spring cloud gateway and eureka(service discovery)
 but the only difference is for each service is non-blocking and communicates using WebClient instead of RestTemplate
 
 