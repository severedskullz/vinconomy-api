package com.skully.vinconomy.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity
public class Shop {

	@EmbeddedId
	private ShopId id;

	private String name;
	
	private String owner;
	
	private String description;
	
	public Shop() {} // Hibernate Default Constructor
	public Shop(ShopId id) {
		this.id = id;
	}
	
	public ShopId getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}	
	
}
