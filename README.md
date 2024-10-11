# Check-in-check-out system
This system is made with Springboot to create this project I used Java version 17 the follow steps:

## [1-SpringBoot Initializr](https://start.spring.io/)

1. **Install this Dependencies in the section "ADD DEPENDENCIES" to generate the Springboot Proyect**

* [Spring JPA](https://docs.spring.io/spring-data/jpa/reference/index.html) provides support for the [Jakarta Persistence API(JPA)](https://jakarta.ee/learn/docs/jakartaee-tutorial/current/persist/persistence-intro/persistence-intro.html)
* [Spring Web](https://docs.spring.io/spring-framework/reference/web/webmvc.html#page-title) is a web framework and the formal name is "Spring Web" or "Spring MVC"(commonly known)
* [Mysql Driver](https://dev.mysql.com/doc/connector-j/en/) this driver uses a JDBC(Java Database Connectivity) is a driver for communicating with MYSQL servers

2. We need to examine the project structure of this SpringBoot project 

 ```
 |  ├── check-in-check-out-system/
 │  ├── .gradle/
 |  ├── build/
 |  ├── gradle/
 |  ├── src/
 |   ├── main
 |    ├── java
 |     ├── com.tcs.check_in_check_out_system
 |      ├── controller
 |      ├── model
 |      ├── repository
 |      ├── service
 |      ├── CheckInCheckOutSystemApplication.java
 |    ├── resources
 |     ├── static
 |     ├── templates
 |     ├── application.properties
 ...

```
> Right now we are going to focus in what packages we made for example the controller, model ,repository and service inside of our  group "com.tcs.check_in_check_out_system" in this ocassion I configurated the $${\color{red}application.properties}$$ in this way([SpringBoot Structure](https://docs.spring.io/spring-boot/reference/using/structuring-your-code.html))

This file $${\color{red}application.properties}$$  contains different configuration which is required to run the application in a different environment

**Be sure the Database configuration link to the server or to your localhost is up to date**

 ``` java
spring.application.name=check-in-check-out-system
spring.datasource.url=jdbc:mysql://localhost:3306/check_db
spring.datasource.username=root
spring.datasource.password=password
spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dalect=org.hibernate.dialect.MySQLDialect

 ```
>   1. spring.application.name=the name of the application o 
>   3. spring.datasource.url=the connection to our Database.
>   4. spring.datasource.username=the Privilege User of our Database
>   5. spring.jpa.hiberante.ddl-auto=We turns off the DDL generation([JPA(Java Persistance API) has features of DDL(Data Definition Language)...](https://docs.spring.io/spring-boot/docs/1.1.0.M1/reference/html/howto-database-initialization.html))
>   6. spring.jpa.show-sql=To enable all te Queries in our SQL as SELECT,INSERT,UPDATE,DELETE using the [JPA](https://docs.spring.io/spring-boot/appendix/application-properties/index.html)
>   6. spring.jpa.show-sql=To enable all te Queries in our SQL as SELECT,INSERT,UPDATE,DELETE using the [JPA](https://docs.spring.io/spring-boot/appendix/application-properties/index.html)
>   7. spring.jpa.properties.hibernate.dialect.MYSQLDialect=[Configurations that tells Hibernate how to communicate with MYSQL](https://docs.jboss.org/hibernate/stable/orm/javadocs/org/hibernate/dialect/MySQLDialect.html)

Now we are going to breakdown all those packages.
We are using an structure commonly known as Controler-Service-Repository Architecture is like the conterpart as commonly known MVC(Model View Controller) Architecture

* **Controller** - A layer which contains the *application logic* mapps each request of the user using particular functions and passing the input to the **Service Layer**.

* **Service** - A layer betwen the **Controller** and the Respository which makes the *business logic* & *validation logic* then it's passed to the **Repository Layer**

* **Repository** - A layer that interacts with the Database making CRUD(Create-Reload-Update-Delete) throughout DAOs(Data Access Objects)

* **Model** - The laywes known as DAO(Interaction with databases operations) and is a POJO(Plain old Java objec) and act as the DTO(Interact with the appliation level data transfer)

### Model

We are going to start creating the Model as a good practice

```
 |  ├── src/
 |   ├── main
 |    ├── java
 |     ├── com.tcs.check_in_check_out_system
 |      ├── controller
 |      ├── model
 |        ├── PersonModel.java
 |      ├── repository
 |      ├── service
 |      ├── CheckInCheckOutSystemApplication.java
 ...
```
Inside of PersonModel.java

```java
package com.tcs.check_in_check_out_system.model;

import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;

import java.time.LocalDateTime;

@Entity
@Table(name = "person")
public class PersonModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String namePerson;

    @Column(name = "check_in_time")
    private LocalDateTime checkInTime;

    @Column(name = "check_out_time")
    private LocalDateTime checkOutTime;

    //Getter & Setters

    public Long getId(){
        return id;
    }

    public void setId(Long id){
       this.id = id;
    }

    public String getNamePerson(){
        return namePerson;
    }

    public void setNamePerson(String namePerson){
        this.namePerson = namePerson;
    }

    public LocalDateTime getCheckInTime(){
        return checkInTime;
    }

    public void setCheckInTime(LocalDateTime checkInTime){
        this.checkInTime = checkInTime;
    }

    public LocalDateTime getCheckOutTime(){
        return checkOutTime;
    }

    public void setCheckOutTime(LocalDateTime checkOutTime){
        this.checkOutTime = checkOutTime;
    }

```
> We are using Jakarta or the Java Enterprise Version known as JAVA EE libraries in Spring Boot
> 1. *@Entity*  = Indicates that is a JPA entity but in this case our table is not called PersonModel for that reason we need to specify the beneath annotation
> 2. *public class PersonModel { /* functions/methods \* }* = The name of our class could be any name you want however remember that is the same name as your file.
> 3. *@Id* = Indicates that the JPA recognizes it as an object ID
> 4. *@GeneratedValue* = Indicate that the ID should be generated automatically
> 5. *@Column(name = "name") =  Used for adding the column of the tables name of the MySQL database 
> 5. We know what are the other getter and setter variables to make encapsulation let's move one we can use Lombock and skip that step but we are not using it.

### Repository
Time to create the Repository package in this case is an Interface :
```
 |  ├── src/
 |   ├── main
 |    ├── java
 |     ├── com.tcs.check_in_check_out_system
 |      ├── controller
 |      ├── model 
 |      ├── $${\color{red}repository}$$$
 |        ├── $${\color{red}PersonRepository.java}$$
 |      ├── service
 |      ├── CheckInCheckOutSystemApplication.java
 ...
```
Using an interface allows us to controll the multiple request for that reason we are not using an Abstract Class let's see what is under the hood our Interface.

```java
package com.tcs.check_in_check_out_system.repository;

import com.tcs.check_in_check_out_system.model.PersonModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<PersonModel, Long> {

}

```

The meaning of each annotation and imports:

> 1. import com.tcs.check_in_check_out_system.model.PersonModel; = we are importing the Model in our Interface
> 2. import org.springframework.data.jpa.repository.JpaRepository;= we are using JPA to managing the datand parsing the PersonModel to be manipulated by itself. 
> 3. import org.springframework.stereotype.Repository; = to use the anotation @Reposiory and it's capabilities
> 4. @Repository = Shows that the class  is the responsible to manage the Database
> 5. public interface PersonRepository extends JpaRepository<PersonModel, Long> { } = We are seeing that the PersonRepository extends or inherits from the JpaRepository Interface parsing the values of the Class and the ID in this case the attribute

### Service

This is the structure of the project where the Service package is already created.
```
 |  ├── src/
 |   ├── main
 |    ├── java
 |     ├── com.tcs.check_in_check_out_system
 |      ├── controller
 |      ├── model 
 |      ├── service
 |        ├── PersonService.java
 |      ├── CheckInCheckOutSystemApplication.java
 ...

```


Let's check out what is in the shell of our PersonService.java file

```java
package com.tcs.check_in_check_out_system.service;

import com.tcs.check_in_check_out_system.model.PersonModel;
import com.tcs.check_in_check_out_system.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static java.time.LocalDateTime.now;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    //1) Allows users to register a check-in //Post
    //2) Retrieve check-in and check-out records. //Get
    //3) Handle updating the check-out time when a person leaves the facility //Update
    //4) Provide functionality for deleting records if needed //Delete

    //1
    public PersonModel registerCheckIn(String namePerson) {
        PersonModel personModel = new PersonModel();
        personModel.setNamePerson(namePerson);
        personModel.setCheckInTime(LocalDateTime.now());
        personModel.setCheckOutTime(LocalDateTime.now());
        return personRepository.save(personModel);
    }

    //2
    public List<PersonModel> getRecords(){
        List<PersonModel> records = new ArrayList<>();
        for(PersonModel personModel : personRepository.findAll()){
            records.add(personModel);
        }
        return records;
    }

    //3
    public PersonModel updateCheckOut(Long id) {
        PersonModel personModel = personRepository.findById(id).orElseThrow(NoSuchElementException::new);
        personModel.setCheckOutTime(now());
        PersonModel updatedPerson = personRepository.save(personModel);
        return updatedPerson;
    }

    //4
    public void deleteRecord(Long id){
        personRepository.deleteById(id);
    }

}
```

As you appreciated I have different methods in this I will describre what each method(services) are doing as well as the different annotations

> 1. import com.tcs.check_in_check_out_system.model.PersonModel; = We are importing the Model because we are going to use it later

> 2. import com.tcs.check_in_check_out_system.repository.PersonRepository; = We are importing the repository with the same purpose as I explained  as the previous statement

> 3. [import org.springframework.beans.factory.annotation.Autowired] (https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/beans/factory/annotation/Autowired.html) = Create a constructor facilitating the Spring dependency injection(Enables to turn Java classes into managed objects and to inject them into any other managed object)

> 4. import org.springframework.stereotype.Service; = Make a reference to used the @Service annotation in the framework

> 5. import java.time.LocalDateTime;

>  - import java.time.ArrayList;

>  - import java.util.List;

>  - import java.util.NoSuchElementException;

>  - import static java.util.NoSuchElementException;

> These imports are going to help us to use the different methods to complete Services or methods and their functionality

> 6. @Service = We are telling SpringBoot that  this is our Service class 

> 7. @Autowired = Makes easier indepency injection automatically.

> 8. private PersonRepository personRepository = this is a private declaration of the interface with the objective to parsing the fields later.




### Controller 

```java
package com.tcs.check_in_check_out_system.controller;

import com.tcs.check_in_check_out_system.model.PersonModel;
import com.tcs.check_in_check_out_system.service.PersonService;
import jakarta.transaction.Transactional;
import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/people")
public class PersonController {
    @Autowired
    private PersonService personService;

    @PostMapping("/checkin")
    public ResponseEntity<PersonModel> loginCheckIn(@RequestParam String namePerson){
        if (namePerson.isEmpty()) {
           return  ResponseEntity.notFound().build();
        } else {
            PersonModel personModel = personService.registerCheckIn(namePerson);
            return ResponseEntity.ok(personModel);
        }
    }

    @GetMapping("/records")
    public ResponseEntity<List <PersonModel>> getRecords(){
        List<PersonModel> records = personService.getRecords();
        return ResponseEntity.ok(records);
    }

    @PutMapping("/{id}/checkout")
    public ResponseEntity<PersonModel> updateCheckOut(@PathVariable Long id) {
        try {
            PersonModel personModel = personService.updateCheckOut(id);
            return ResponseEntity.ok(personModel);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteRecord(@PathVariable Long id) {
        personService.deleteRecord(id);
        return ResponseEntity.noContent().build();
    }
}

```



## [2-Setup Docker Container ](https://docs.docker.com/get-started/)
> Install [Docker](https://www.docker.com/)

1. After installing docker we could use it with **Docker compose**

** [Docker compose](https://docs.docker.com/compose/) is a tool inside of the Docker Utility that help us to build containers**

2. Now we need to create our compose.yaml in this scenario I stored it in a directory called  DB due to this file we help us to build our DataBase in this case [MYSQL](https://www.mysql.com/downloads/) the version 8.
> **Be aware of the version of MYSQL beacuse the Syntax is different from other versions and SQL Server is Diferent**
 - If you want to create in the same order you can do it as  the below structure
 ```
 |  ├── check-in-check-out repository/
 │  ├── check-in-check-out/
 |  ├── DB/
 |   ├── docker-compose.yaml

 ```
> You can name the **docker-compose.yaml** as you wanted but needs to be [yaml](https://datatracker.ietf.org/doc/html/rfc9512) because that's the configuration media type used in the **Docker CLI**

 - Time to see what's inside our docker-compose you can use a template or take a look at the [mysql image documentation](https://hub.docker.com/_/mysql)because there's an example there.Furthermore, you can use my **Docker compose** :

```docker
name: check_database
services:
  db:
    image: mysql:8.0
    container_name: check_db
    environment:
      MYSQL_DATABASE: check_db
      MYSQL_USER: user
      MYSQL_PASSWORD: password
      MYSQL_ROOT_PASSWORD : password
    ports:
      - '3306:3306'
    volumes:
      - my-db-data:/var/lib/mysql
volumes:
  my-db-data:

```
> Remember the indentation it's important if you want to create a good file you can create read the [documentation](https://docs.docker.com/compose/gettingstarted/)

- Now it's time to build and run our file with compose
```bash
docker compose up  -d --build .
```
> [ docker compose up = create and restart containers + --d (run containers in the background) + --build(build images before starting containers) .(all the yaml files as well we can specify if we have different yamls in the directory)] (https://docs.docker.com/reference/cli/docker/compose/up/)




# employee-api
# employee-api
