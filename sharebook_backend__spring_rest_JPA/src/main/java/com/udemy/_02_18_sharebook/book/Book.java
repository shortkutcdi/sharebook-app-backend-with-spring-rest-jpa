package com.udemy._02_18_sharebook.book;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

import com.udemy._02_18_sharebook.user.User;

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
	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
