package com.example.restapi.entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_expenses")

public class ExpenseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	private String expenseId;
	private String name;
	private String note;
	private String category;
	private Date date;
	private BigDecimal amount;
	@CreationTimestamp
	@Column(nullable = false, updatable = false)
	private Timestamp createdAt;
	@UpdateTimestamp
	private Timestamp updatedAt;
	
	
	
	   public ExpenseEntity() {
	    }

	    // Constructor có tham số (All-Args Constructor)
	    public ExpenseEntity(Long id, String expenseId, String name, String note, String category, Date date, BigDecimal amount, Timestamp createdAt, Timestamp updatedAt) {
	        this.id = id;
	        this.expenseId = expenseId;
	        this.name = name;
	        this.note = note;
	        this.category = category;
	        this.date = date;
	        this.amount = amount;
	        this.createdAt = createdAt;
	        this.updatedAt = updatedAt;
	    }

	    // Getter và Setter cho từng thuộc tính
	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public String getExpenseId() {
	        return expenseId;
	    }

	    public void setExpenseId(String expenseId) {
	        expenseId = expenseId;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	    	name = name;
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
