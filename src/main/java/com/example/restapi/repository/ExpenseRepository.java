package com.example.restapi.repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.restapi.entity.ExpenseEntity;


@Repository
public interface ExpenseRepository extends JpaRepository<ExpenseEntity, Long>{
	Optional<ExpenseEntity> findByExpenseId(String expenseId);
 
}
