# Mapping des entités


[Spring Data JPA](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#reference)

chaque table du modèle de données correspondra à une entité JPA de Spring DATA

## Repository - interrogation de la BDD

- abstraction pour interroger la BDD (possibilité de changer de système de BDD - implémentation)
- Interface qui étend une interface de SPRING (CrudRepository)
- définitions des méthodes personnalisées qui vont générer les requêtes (SQL ou non)

## ajout des dépendances Spring data jpa et h2

Dans eclipse explorer

clic droit projet/Spring/edit starters

dans avalaible : saisir JPA
-> cocher Spring Data JPA

dans avalaible : saisir H2
-> cocher Spring H2 database

## Entité

toutes les entités (classes) doivent avoir un id

Toutés les classes Entités sont annotées avec __@Entity__

l'id est annoté __@Id__ 
et __@GeneratedValue(strategy = GenerationType.IDENTITY)__ permet genérer l'id en se basant sur la BDD

````Java
@Entity
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
````

## Jointure entre entité dans la classe(Entité) Loan et Book

````java
@Entity
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	//...

	@ManyToOne
	private User user;
````

````Java
@Entity
public class Loan {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	// jointure un seul emprunteur est associé à un prêt
	@ManyToOne
	private User borrower; // emprunteur
	
	// jointure un seul prêteur est associé à un prêt
	@ManyToOne
	private User lender; // prêteur
	
	// jointure un seul livre est associé à un prêt
	@ManyToOne
	private Book book;
````