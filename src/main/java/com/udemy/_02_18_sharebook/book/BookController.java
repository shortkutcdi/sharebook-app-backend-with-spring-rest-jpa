package com.udemy._02_18_sharebook.book;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.udemy._02_18_sharebook.loan.Loan;
import com.udemy._02_18_sharebook.loan.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.udemy._02_18_sharebook.user.User;
import com.udemy._02_18_sharebook.user.UserRepository;

@RestController
public class BookController {

	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	LoanRepository loanRepository;

	// afficher les livres disponibles
	@GetMapping(value = "/users/{userId}/books/status/{status}")
	public List<Book> getBookWithStatus(@PathVariable("userId") String userId,
			@PathVariable("status") String statusStr) {
		List<Book> lisBooks = bookRepository.findBookByStatusAndUserIdNotAndDeletedFalse(statusStr,
																						Integer.valueOf(userId));
		return lisBooks;
	}

	// get my books
	@GetMapping(value = "/users/{userId}/books")
	public List<Book> getMyBooks(@PathVariable("userId") String userId) {
		List<Book> books = bookRepository.findByUserIdAndDeletedFalse(Integer.valueOf(userId));
		return books;
	}

	// creer un livre
	@PostMapping(value = "/users/{userId}/books")
	@ResponseStatus(value = HttpStatus.CREATED)
	public Book createBookForUser(@PathVariable("userId") String userId, @Valid @RequestBody Book book) {
		Optional<User> user = userRepository.findById(Integer.valueOf(userId));
		book.setDeleted(false);
		book.setUser(user.get());
		book.setStatus("FREE");
		bookRepository.save(book);
		
		return book;
	}

	// supprimer un livre
	@DeleteMapping(value = "/books/{bookId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public ResponseEntity deleteBook(@PathVariable("bookId") String bookId) {
		Book book = this.bookRepository.findById(Integer.valueOf(bookId)).get();
		// vérifier si le livre n'est pas en cours d'emprunt
		List<Loan> loans = loanRepository.findByBookId(Integer.valueOf(bookId));
		for (Loan loan: loans) {
			if (loan.getCloseDate() == null)
				return new ResponseEntity(HttpStatus.CONFLICT);
		}
		book.setDeleted(true);
		bookRepository.save(book);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
}
