package com.example.restapi.service;

import java.util.List;

import com.example.restapi.dto.ExpenseDTO;


/**
 * service interface for expense module
 * @author huy
 */
public interface ExpenseService {

	/**
	 * it will fetch the expenses from database
	 * @return list
	 */
	List<ExpenseDTO> getAllExpense();
	ExpenseDTO getExpenseByExpenseId(String expenseId);
	
}
