package com.udemy._02_18_sharebook.loan;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.udemy._02_18_sharebook.book.Book;
import com.udemy._02_18_sharebook.user.User;

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
	
	private Date askDate;
	private Date closeDate;

	// getters/setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getBorrower() {
		return borrower;
	}

	public void setBorrower(User borrower) {
		this.borrower = borrower;
	}

	public User getLender() {
		return lender;
	}

	public void setLender(User lender) {
		this.lender = lender;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Date getAskDate() {
		return askDate;
	}

	public void setAskDate(Date askDate) {
		this.askDate = askDate;
	}

	public Date getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}

}
