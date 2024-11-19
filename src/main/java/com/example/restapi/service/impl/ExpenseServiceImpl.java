package com.example.restapi.service.impl;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.restapi.dto.ExpenseDTO;
import com.example.restapi.entity.ExpenseEntity;
import com.example.restapi.repository.ExpenseRepository;
import com.example.restapi.service.ExpenseService;

import lombok.RequiredArgsConstructor;
/**
 * service implementation for expense module
 * @author huy
 */
@Service
public class ExpenseServiceImpl implements ExpenseService {

	private final ExpenseRepository expenseRepository;
	private final ModelMapper modelMapper;
	
	 @Autowired
	    public ExpenseServiceImpl(ExpenseRepository expenseRepository, ModelMapper modelMapper) {
	        this.expenseRepository = expenseRepository;
	        this.modelMapper = modelMapper;
	    }
	

		/**
		 * it will fetch the expenses from database
		 * @return list
		 */
	@Override
	public List<ExpenseDTO> getAllExpense() {
		// call the repository
		List<ExpenseEntity> list = expenseRepository.findAll();
		// convert the entity object to DTO
		List<ExpenseDTO> listOfExpenses = list.stream().map(expenseEntity -> mapToExpenseDTO(expenseEntity)).collect(Collectors.toList());
		// return the list
		return listOfExpenses;

	}
	/**
	 * mapper method to convert expense entity to expense DTO
	 * @param expenseEntity
	 * @return expenseDTO
	 */
	private ExpenseDTO mapToExpenseDTO(ExpenseEntity expenseEntity) {
		// TODO Auto-generated method stub
		return modelMapper.map(expenseEntity,ExpenseDTO.class);
	
	}

}
