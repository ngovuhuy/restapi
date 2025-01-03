
package com.example.restapi.controller;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.restapi.dto.ExpenseDTO;
import com.example.restapi.service.ExpenseService;

import io.ExpenseRequest;
import io.ExpenseResponse;
import jakarta.validation.Valid;
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
	@GetMapping("/expensesday")
	public Map<String, List<ExpenseResponse>> getExpensesGroupedByDate() {
	    log.info("API GET /expenses/grouped-by-date called");

	    // Gọi service để lấy danh sách tất cả chi tiêu
	    List<ExpenseDTO> list = expenseService.getAllExpense();
	    log.info("Printing the data from service{}", list);

	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	    // Chuyển đổi DTO -> Response và nhóm theo ngày dạng String
	    Map<String, List<ExpenseResponse>> groupedByDate = list.stream()
	            .map(this::mapToExpenseResponse) // Chuyển từ ExpenseDTO sang ExpenseResponse
	            .collect(Collectors.groupingBy(expenseResponse -> 
	                dateFormat.format(expenseResponse.getDate()))); // Chuyển Date -> String


	    return groupedByDate;
	}

	@GetMapping("/expenses/{expenseId}")
    public ExpenseResponse getExpenseById(@PathVariable String expenseId) {
    	
    	ExpenseDTO expenseDTO = expenseService.getExpenseByExpenseId(expenseId);
    	return mapToExpenseResponse(expenseDTO);
    	 
    }
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/expenses/{expenseId}")
	public void  deleteExpenseByExpenseId(@PathVariable String expenseId) {
		expenseService.deleteExpenseByExpenseId(expenseId);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/expenses")
	public ExpenseResponse saveExpenseDetails(@Valid @RequestBody ExpenseRequest expenseRequest) {
		ExpenseDTO expenseDTO = mapToExpenDTO(expenseRequest);
		expenseDTO = expenseService.saveExpenseDetails(expenseDTO);
		return mapToExpenseResponse(expenseDTO);
 
	}
	@PutMapping("/expenses/{expenseId}")
	public ExpenseResponse updateExpenseDetails(@Valid @RequestBody ExpenseRequest updateRequest, @PathVariable String expenseId){
		ExpenseDTO updatedExpenseDTO = mapToExpenDTO(updateRequest);
		updatedExpenseDTO = expenseService.updateExpenseDetails(updatedExpenseDTO, expenseId);
		return mapToExpenseResponse(updatedExpenseDTO);
	}
//	Mục đích: Nhận dữ liệu từ client và chuyển thành dạng phù hợp để xử lý trong tầng nghiệp vụ.
	private ExpenseDTO mapToExpenDTO(ExpenseRequest expenseRequest)
	{
		return modelMapper.map(expenseRequest, ExpenseDTO.class);
	}
	/** 
	 * mapper method for converting expense dto object to expense expense response
	 * @param expenseDTO
	 * @return expenseResponse
	 */
//	Mục đích: Lấy dữ liệu đã xử lý trong tầng nghiệp vụ và trả về cho client ở dạng đơn giản, dễ đọc.
	private ExpenseResponse mapToExpenseResponse(ExpenseDTO expenseDTO) {
		// TODO Auto-generated method stub
		return modelMapper.map(expenseDTO, ExpenseResponse.class);
	}
}
