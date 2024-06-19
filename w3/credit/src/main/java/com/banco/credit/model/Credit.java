package com.banco.credit.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "credits")
public class Credit {
	
	@Id
    private String id;
    private String type; // "personal", "business", "credit card"
    private double amount;
    private double interestRate;
    private int term;
    private String customerId;

}
