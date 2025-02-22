package com.skully.vinconomy.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ShopId implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Column(name="shop_id")
	private long shopId;
	
	@Column(name="node_id")
	private long networkNodeId;

	public ShopId() { //No Arg Constructor for Hibernate
		
	}
	
	public ShopId(long networkId, long shopId) {
		super();
		this.shopId = shopId;
		this.networkNodeId = networkId;
	}

	
	
	@Override
	public int hashCode() {
		return Objects.hash(networkNodeId, shopId);
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
		return Objects.equals(networkNodeId, other.networkNodeId) && Objects.equals(shopId, other.shopId);
	}

	public long getShopId() {
		return shopId;
	}

	public void setShopId(long shopId) {
		this.shopId = shopId;
	}

	public long getNetworkNodeId() {
		return networkNodeId;
	}

	public void setNetworkNodeId(long networkId) {
		this.networkNodeId = networkId;
	}
	
	
}
