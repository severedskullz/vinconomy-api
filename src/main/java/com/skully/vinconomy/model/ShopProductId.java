package com.skully.vinconomy.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;


@Embeddable
public class ShopProductId implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Column(name="node_id")
	private long nodeId;
	@Column(name="pos_x")
	private int x;
	@Column(name="pos_y")
	private int y;
	@Column(name="pos_z")
	private int z;
	@Column(name="stall_slot")
	private int stallSlot;
	@Column(name="shop_id")
	private int shopId;
	
	public ShopProductId() { //No Arg Constructor for Hibernate
		
	}
	
	
	
	public ShopProductId(long nodeId, int shopId, int x, int y, int z, int stallSlot) {
		super();
		this.nodeId = nodeId;
		this.shopId = shopId;
		this.x = x;
		this.y = y;
		this.z = z;
		this.stallSlot = stallSlot;
	}



	public long getNodeId() {
		return nodeId;
	}
	public void setNodeId(long nodeId) {
		this.nodeId = nodeId;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getZ() {
		return z;
	}
	public void setZ(int z) {
		this.z = z;
	}
	public int getStallSlot() {
		return stallSlot;
	}
	public void setStallSlot(int stallSlot) {
		this.stallSlot = stallSlot;
	}
	public long getShopId() {
		return shopId;
	}
	public void setShopId(int shopId) {
		this.shopId = shopId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nodeId, stallSlot, x, y, z);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShopProductId other = (ShopProductId) obj;
		return Objects.equals(nodeId, other.nodeId) && stallSlot == other.stallSlot
				&& x == other.x && y == other.y && z == other.z;
	}
	
	
}
