package com.udemy._02_18_sharebook.user;

import javax.validation.Valid;

import com.udemy._02_18_sharebook.book.BookRepository;
import com.udemy._02_18_sharebook.loan.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

	@Autowired
	UserRepository userRepository;

	/**
	 * appel d'un utilisateur
	 * on définit le userId
	 * @param userId
	 * @return
	 */
	@GetMapping(value = "/users/{userId}")
	public User getUser(@PathVariable("userId") String userId) {
		
		Optional<User> user = userRepository.findById(Integer.valueOf(userId));
		return user.get();
	}

	/**
	 * creation d'un utilisateur 
	 * on passe par le @Requestbody pour passer un utilisateur 
	 * et pas dans l'url
	 * @return
	 */
	@PostMapping(value = "/users")
	public ResponseEntity createUser(@Valid @RequestBody User user) {
		// vérifier si ultilisateur existe
		List<User> listUser = userRepository.findByEmail(user.getEmail());

		if (listUser != null && listUser.size() > 0){
			return new ResponseEntity(HttpStatus.CONFLICT);
		}
		// TODO SAVE User in DB
		userRepository.save(user);
		return new ResponseEntity(HttpStatus.CREATED);
	}
	
	/**
	 * Supprimer un utilisateur
	 * on renseigne dans l'url le userID
	 * @param userId
	 */
	@DeleteMapping(value = "/users/{userId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deleteUser(@PathVariable("userID") String userId) {
		Optional<User> user = userRepository.findById(Integer.valueOf(userId));
		userRepository.delete(user.get());
	}
}
