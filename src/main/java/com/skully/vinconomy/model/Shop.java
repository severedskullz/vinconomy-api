package com.skully.vinconomy.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity
public class Shop {

	@EmbeddedId
	private ShopId id;

	private String name;
	
	public ShopId getId() {
		return id;
	}
	public void setId(ShopId id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	
	
}
