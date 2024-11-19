
package com.example.restapi.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.restapi.dto.ExpenseDTO;
import com.example.restapi.service.ExpenseService;

import io.ExpenseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
/**
 * this is controller class for Expense module
 * @author Huy
 */
@RestController
@Slf4j
public class ExpenseController {

	@Autowired
	public ExpenseController(ExpenseService expenseService, ModelMapper modelMapper) {
		this.expenseService = expenseService;
		this.modelMapper = modelMapper;
	}

	private final ExpenseService expenseService;
	private final ModelMapper modelMapper;
   /**
    * it will fetch the expenses from database
    * @return list 
    */
	@GetMapping("/expenses")
	public List<ExpenseResponse> GetExpense() {
		log.info("API GET /expenses called");
		// call the service method
		List<ExpenseDTO> list = expenseService.getAllExpense();
		log.info("Printing the data from service{}", list);
		// convert dto to expense response
		List<ExpenseResponse> response = list.stream().map(expenseDTO -> mapToExpenseResponse(expenseDTO))
				.collect(Collectors.toList());

		return response;
	}
   
	/**
	 * mapper method for converting expense dto object to expense expense response
	 * @param expenseDTO
	 * @return expenseResponse
	 */
	private ExpenseResponse mapToExpenseResponse(ExpenseDTO expenseDTO) {
		// TODO Auto-generated method stub
		return modelMapper.map(expenseDTO, ExpenseResponse.class);
	}
}
