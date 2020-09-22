package com.udemy._02_18_sharebook.loan;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends CrudRepository<Loan, Integer>{

	List<Loan> findByBorrowerId(Integer borrowerId);
	
	List<Loan> findByLenderId(Integer lenderId);
	
	List<Loan> findByBookId(Integer bookId);
}
