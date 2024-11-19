package io;

import java.math.BigDecimal;
import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



public class ExpenseRequest {
	private String Name;
	private String note;
	private String category;
	private Date date;
	private BigDecimal amount;
	
	
    // Constructor mặc định (No-Args Constructor)
    public ExpenseRequest() {
    }

	
    public ExpenseRequest(String name, String note, String category, Date date, BigDecimal amount) {
        this.Name = name;
        this.note = note;
        this.category = category;
        this.date = date;
        this.amount = amount;
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
	
	
}
