package com.banco.client.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Data
@Document(collection = "clients")
public class Client {
	@Id
	@Field("_id")
    private String id;
    private String name;
    private String type; // "personal" or "business"
    private String address;
    private String phone;
    private String email;

    // Only for business customers
    private List<Owner> owners;
    private List<AuthorizedSigner> authorizedSigners;
    
	public Client(String id, String name, String type, String address, String phone, String email, List<Owner> owners,
			List<AuthorizedSigner> authorizedSigners) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.owners = owners;
		this.authorizedSigners = authorizedSigners;
	}
	public Client() {
		super();
	}  
    
    

}
