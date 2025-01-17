package com.banco.account.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "accounts")
public class Account {

	@Id
	private String id;
	private String type; // "savings", "current", or "fixed"
	private Double balance;
	private Double maintenanceFee;
	private Integer maxTransactions;
	private String specificDate; // Only for fixed accounts
	private String customerId;

	private List<String> owners; // List of owner IDs
	private List<String> authorizedSigners;

}
