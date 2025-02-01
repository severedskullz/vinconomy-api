package com.skully.vinconomy.model.dto;

import java.util.List;

public class ShopProducts {
	public long id;
	public boolean removeAll;
	public List<ShopStall> stalls;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public List<ShopStall> getStalls() {
		return stalls;
	}
	public void setStalls(List<ShopStall> stalls) {
		this.stalls = stalls;
	}
	public boolean isRemoveAll() {
		return removeAll;
	}
	public void setRemoveAll(boolean removeAll) {
		this.removeAll = removeAll;
	}
	
	
}
