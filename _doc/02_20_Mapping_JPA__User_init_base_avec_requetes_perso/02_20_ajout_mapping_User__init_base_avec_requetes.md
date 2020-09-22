# Mapping User


[Spring Data JPA](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#reference)

````java
@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
````

## ajout d'un fichier data.sql comportant des requêtes SQL

dans src/main/ressources création d'un fichier data.sql

data.sql

````sql
insert into book (id, name) values (1, 'test');
````

## ajout configuration dans application.properties

permet de logger dans la console toutes les requêtes sql

````
spring.jpa.show-sql=true
spring.h2.console.enabled=true
````

## address console h2

````
http://localhost:8080/h2-console
````

[se connecter à la base de données h2 pendant dev Spring boot](https://medium.com/@harittweets/how-to-connect-to-h2-database-during-development-testing-using-spring-boot-44bbb287570)