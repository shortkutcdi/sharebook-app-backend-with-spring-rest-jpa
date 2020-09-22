package com.udemy._02_18_sharebook.loan;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.udemy._02_18_sharebook.book.Book;
import com.udemy._02_18_sharebook.book.BookRepository;
import com.udemy._02_18_sharebook.user.User;
import com.udemy._02_18_sharebook.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoanController {

	@Autowired
	BookRepository bookRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	LoanRepository loanRepository;
	
	@GetMapping(value = "/users/{userId}/loans")
	public List<Loan> getLoans(@PathVariable("userId") String userId){
		List<Loan> listLoan = loanRepository.findByBorrowerId(Integer.valueOf(userId));
		return listLoan;
	}
	
	// créer un emprunt
	@PostMapping(value = "/users/{userId}/loans/{bookId}")
	@ResponseStatus(value = HttpStatus.CREATED)
	public Loan createLoan(	@PathVariable("userId") String userId,
							@PathVariable("bookId") String bookId) {
		// récupérer l'emprunteur
		Optional<User> borrower = userRepository.findById(Integer.valueOf(userId)); // emprunteur
		Optional<Book> book = bookRepository.findById(Integer.valueOf(bookId));

		if (borrower.isPresent() && book.isPresent()){
			Book book1 = book.get();
			User lender = book1.getUser(); // propriétaire du livre
			Loan loan  = new Loan();
			loan.setAskDate(new Date());
			loan.setBook(book1); // ajouter le livre à l'emprunt
			loan.setBorrower(borrower.get());
			loan.setLender(lender);
			loanRepository.save(loan); // enregistrer l'emprunt en BDD

			book1.setStatus("USED"); // définir le statut used
			bookRepository.save(book1); // enregistrer le livre

			return loan;
		}
		return null;
	}
	
	// clore un emprunt
	@DeleteMapping(value = "/loans/{loanId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deleteLoan(@PathVariable("loanId") String loanId) {

		Loan loan = loanRepository.findById(Integer.valueOf(loanId)).get();
		loan.setCloseDate(new Date());
		loanRepository.save(loan);

		// récupérer le livre emprunté et lui définir le satus FREE puis l'enregistrer
		Book book = loan.getBook();
		book.setStatus("FREE");
		bookRepository.save(book);
	}

}
