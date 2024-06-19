package com.banco.client.model;

import lombok.Data;

@Data
public class Owner {
    private String ownerId;
    private String name;
    private String role;
    
	public Owner(String ownerId, String name, String role) {
		super();
		this.ownerId = ownerId;
		this.name = name;
		this.role = role;
	}
	public Owner() {
		super();
	}
    
    
}
