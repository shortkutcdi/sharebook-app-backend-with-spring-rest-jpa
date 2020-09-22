package com.udemy._02_18_sharebook.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(min = 5, max = 25, message = "Le nom de l'utilisateur doit faire entre 5 et 25 caractères")
    private String lastName;

    @Size(min = 5, max = 25, message = "Le prénom de l'utilisateur doit faire entre 5 et 25 caractères")
    private String firstName;

    @Size(min = 5, max = 25, message = "Le mot de passe doit faire entre 5 et 25 caractères")
    private String password;

    @Size(min = 5, max = 50, message = "L'email doit faire entre 5 et 50 caractères'")
    private String email;

    // getters/setters
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
