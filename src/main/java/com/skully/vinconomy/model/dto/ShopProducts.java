package com.skully.vinconomy.model.dto;

import java.util.List;

public class ShopProducts {
	public long id;
	public boolean removeAll;
	public List<StallProduct> products;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public List<StallProduct> getProducts() {
		return products;
	}
	public void setProducts(List<StallProduct> products) {
		this.products = products;
	}
	public boolean isRemoveAll() {
		return removeAll;
	}
	public void setRemoveAll(boolean removeAll) {
		this.removeAll = removeAll;
	}
	
	
}
