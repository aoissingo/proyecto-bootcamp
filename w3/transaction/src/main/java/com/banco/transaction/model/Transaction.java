package com.banco.transaction.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "transactions")
public class Transaction {

	@Id
    private String id;
    private String type; // "deposit", "withdrawal", "payment", "consumption"
    private double amount;
    private LocalDate date;
    private String accountId;
    private String creditId;
}
