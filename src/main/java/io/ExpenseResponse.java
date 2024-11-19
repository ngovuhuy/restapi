package io;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExpenseResponse {
	private String ExpenseId;
	private String Name;
	private String note;
	private String category;
	private Date date;
	private BigDecimal amount;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	
}
