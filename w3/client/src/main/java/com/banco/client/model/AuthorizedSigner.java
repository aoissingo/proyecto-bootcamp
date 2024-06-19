package com.banco.client.model;

import lombok.Data;

@Data
public class AuthorizedSigner {
    private String signerId;
    private String name;
    private String role;
    
	public AuthorizedSigner(String signerId, String name, String role) {
		super();
		this.signerId = signerId;
		this.name = name;
		this.role = role;
	}

	public AuthorizedSigner() {
		super();
	}
    
}