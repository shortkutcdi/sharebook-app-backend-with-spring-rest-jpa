# Ajout repository pour Livre

[Spring Data JPA](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#reference)

Instructions de l'exercice

Implémenter deux méthodes dans le repository pour chacun des cas suivants :

- "liste des livres disponibles" : Rechercher en base de données les livres avec 
  un statut passé en paramètre, et qui ne soit pas l'ID utilisateur passé en paramètre 
  également (pour ne pas récupérer les livres de l'utilisateur connecté)

- "mes livres" : Rechercher en base de données les livres avec l'ID utilisateur passé 
  en paramètre, et qui ont le flag "deleted" à false

Conseil : n'hésitez pas à vous référer à la doc spring DATA dans les ressources attachées pour cet exo!

Book.java

````java
@Entity
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String status;

	@Size(min = 5, max = 25, message = "Le nom du livre doit faire entre 5 et 25 carcatères")
	private String name;

	@Size(min = 5, max = 25, message = "La catégorie du livre doit faire entre 5 et 25 carcatères")
	private String category;
	
    private Boolean deleted;


	// jointure
	@ManyToOne
	private User user;

	public Book() {
	}

	public Book(String name) {
		this.name = name;
	}

	// getters/setters
````


[jpa - query creation](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation)

BookRepository.java

````java
@Repository
public interface BookRepository extends CrudRepository<Book, Integer>{

	List<Book> findBookByStatusAndUserIdNotAndDeletedFalse(String status, Integer userId);
	
	List<Book> findByUserIdAndDeletedFalse(Integer userId);
}
````