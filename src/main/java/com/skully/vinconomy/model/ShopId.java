package com.skully.vinconomy.model;

import java.io.Serializable;
import java.util.Objects;

public class ShopId implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private long shopId;
	
	private String networkId;

	public ShopId() { //No Arg Constructor for Hibernate
		
	}
	
	public ShopId(String networkId, long shopId) {
		super();
		this.shopId = shopId;
		this.networkId = networkId;
	}

	
	
	@Override
	public int hashCode() {
		return Objects.hash(networkId, shopId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShopId other = (ShopId) obj;
		return Objects.equals(networkId, other.networkId) && Objects.equals(shopId, other.shopId);
	}

	public long getShopId() {
		return shopId;
	}

	public void setShopId(long shopId) {
		this.shopId = shopId;
	}

	public String getNetworkId() {
		return networkId;
	}

	public void setNetworkId(String networkId) {
		this.networkId = networkId;
	}
	
	
}
