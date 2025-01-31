package com.skully.vinconomy.model.dto;

public class Product {
	
	private long id;
	private int x;
	private int y;
	private int z;
	private int stallSlot;
	private String productName;
	private String productCode;
	private int productQuantity;
	private String productAttributes;
	
	private String currencyName;
	private String currencyCode;
	private int currencyQuantity;
	private String currencyAttributes;
	
	private int totalStock;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public int getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}

	public String getProductAttributes() {
		return productAttributes;
	}

	public void setProductAttributes(String productAttributes) {
		this.productAttributes = productAttributes;
	}

	public String getCurrencyName() {
		return currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public int getCurrencyQuantity() {
		return currencyQuantity;
	}

	public void setCurrencyQuantity(int currencyQuantity) {
		this.currencyQuantity = currencyQuantity;
	}

	public String getCurrencyAttributes() {
		return currencyAttributes;
	}

	public void setCurrencyAttributes(String currencyAttributes) {
		this.currencyAttributes = currencyAttributes;
	}

	public int getTotalStock() {
		return totalStock;
	}

	public void setTotalStock(int totalStock) {
		this.totalStock = totalStock;
	}
	
	
}
