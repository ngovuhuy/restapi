package com.example.restapi.dto;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



public class ExpenseDTO {
	private String ExpenseId;
	private String Name;
	private String note;
	private String category;
	private Date date;
	private BigDecimal amount;
	private Timestamp createdAt;
	private Timestamp updatedAt; 
	
	
	 public ExpenseDTO() {
	    }

	    // Constructor có tham số (All-Args Constructor)
	    public ExpenseDTO(String expenseId, String name, String note, String category, Date date, BigDecimal amount, Timestamp createdAt, Timestamp updatedAt) {
	        this.ExpenseId = expenseId;
	        this.Name = name;
	        this.note = note;
	        this.category = category;
	        this.date = date;
	        this.amount = amount;
	        this.createdAt = createdAt;
	        this.updatedAt = updatedAt;
	    }

	    // Getter và Setter cho từng thuộc tính
	    public String getExpenseId() {
	        return ExpenseId;
	    }

	    public void setExpenseId(String expenseId) {
	        ExpenseId = expenseId;
	    }

	    public String getName() {
	        return Name;
	    }

	    public void setName(String name) {
	        Name = name;
	    }

	    public String getNote() {
	        return note;
	    }

	    public void setNote(String note) {
	        this.note = note;
	    }

	    public String getCategory() {
	        return category;
	    }

	    public void setCategory(String category) {
	        this.category = category;
	    }

	    public Date getDate() {
	        return date;
	    }

	    public void setDate(Date date) {
	        this.date = date;
	    }

	    public BigDecimal getAmount() {
	        return amount;
	    }

	    public void setAmount(BigDecimal amount) {
	        this.amount = amount;
	    }

	    public Timestamp getCreatedAt() {
	        return createdAt;
	    }

	    public void setCreatedAt(Timestamp createdAt) {
	        this.createdAt = createdAt;
	    }

	    public Timestamp getUpdatedAt() {
	        return updatedAt;
	    }

	    public void setUpdatedAt(Timestamp updatedAt) {
	        this.updatedAt = updatedAt;
	    }
}
