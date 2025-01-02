package com.example.restapi.service.impl;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.restapi.dto.ExpenseDTO;
import com.example.restapi.entity.ExpenseEntity;
import com.example.restapi.entity.ProfileEntity;
import com.example.restapi.exceptions.ResourceNotFoundException;
import com.example.restapi.repository.ExpenseRepository;
import com.example.restapi.service.AuthService;
import com.example.restapi.service.ExpenseService;

import lombok.RequiredArgsConstructor;
/**
 * service implementation for expense module
 * @author huy
 */
@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {

	private final ExpenseRepository expenseRepository;
	private final ModelMapper modelMapper;
	
	private final AuthService authService;
	
   
		/**
		 * it will fetch the expenses from database
		 * @return list
		 */
	@Override
	public List<ExpenseDTO> getAllExpense() {
		// call the repository
	  Long loggedInProfileId =	authService.getLoggedInProfile().getId();
		List<ExpenseEntity> list = expenseRepository.findByOwnerId(loggedInProfileId);
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


	@Override
	public ExpenseDTO getExpenseByExpenseId(String expenseId) {
		ExpenseEntity optionalExpense =  expenseRepository.findByExpenseId(expenseId)
				.orElseThrow(() -> new ResourceNotFoundException("Expense not found for the expenseId " +expenseId));
		
		return mapToExpenseDTO(optionalExpense); 
	}
	@Override
	public void deleteExpenseByExpenseId(String expenseId) {
		ExpenseEntity expenseEntity = getExpenseEntity(expenseId);
		expenseRepository.delete(expenseEntity);
		
	} 
	private ExpenseEntity getExpenseEntity(String expenseId) {
		Long id = authService.getLoggedInProfile().getId();
		return expenseRepository.findByOwnerIdAndExpenseId(id,expenseId)
				.orElseThrow(() -> new ResourceNotFoundException("Expense Not found for the expense id"+ expenseId));
	}


	@Override
	public ExpenseDTO saveExpenseDetails(ExpenseDTO expenseDTO) {
		 ProfileEntity profileEntity = authService.getLoggedInProfile();
		ExpenseEntity newExpenseEntity = mapToExpenseEntity(expenseDTO);
		newExpenseEntity.setExpenseId(UUID.randomUUID().toString());
		newExpenseEntity.setOwner(profileEntity);
		newExpenseEntity = expenseRepository.save(newExpenseEntity);
		return mapToExpenseDTO(newExpenseEntity);
	}
	private ExpenseDTO mapToExpenseDTO(ExpenseEntity expenseEntity) {
		// TODO Auto-generated method stub
		return modelMapper.map(expenseEntity,ExpenseDTO.class);
	
	}


	private ExpenseEntity mapToExpenseEntity(ExpenseDTO expenseDTO) {
		return modelMapper.map(expenseDTO, ExpenseEntity.class);
	}


	@Override
	public ExpenseDTO updateExpenseDetails(ExpenseDTO expenseDTO, String expenseId) {
		ExpenseEntity existingExpense = getExpenseEntity(expenseId);
		ExpenseEntity updatedExpenseEntity = mapToExpenseEntity(expenseDTO);
		updatedExpenseEntity.setId(existingExpense.getId());
		updatedExpenseEntity.setExpenseId(existingExpense.getExpenseId());
		updatedExpenseEntity.setOwner(authService.getLoggedInProfile());
		updatedExpenseEntity.setCreatedAt(existingExpense.getCreatedAt());
		updatedExpenseEntity.setUpdatedAt(existingExpense.getUpdatedAt());
		updatedExpenseEntity = expenseRepository.save(updatedExpenseEntity);
		return mapToExpenseDTO(updatedExpenseEntity);
	}
}
