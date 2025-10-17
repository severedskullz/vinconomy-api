package com.skully.vinconomy.model.dto;

public class SearchResult {
	String nodeId;
	String nodeName;
	long shopId;
	String shopName;
	String shopOwner;
	String description;
	
	public SearchResult() {}
	
	public SearchResult(String nodeId, String nodeName, long shopId, String shopName, String shopOwner, String description) {
		super();
		this.nodeName = nodeName;
		this.nodeId = nodeId;
		this.shopId = shopId;
		this.shopName = shopName;
		this.shopOwner = shopOwner;
		this.description = description;
	}
	public String getNodeId() {
		return nodeId;
	}
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
	public long getShopId() {
		return shopId;
	}
	public void setShopId(long shopId) {
		this.shopId = shopId;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getShopOwner() {
		return shopOwner;
	}
	public void setShopOwner(String shopOwner) {
		this.shopOwner = shopOwner;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	
	
}
