package com.skully.vinconomy.model.dto;

import com.skully.vinconomy.enums.AsyncType;

public class TradeNetworkRegistration {
	
	private String name;
	private String description;
	private boolean autoAcceptRequests;
	private boolean moddedItemsAllowed;
	private AsyncType asyncType;
	private boolean visible;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isAutoAcceptRequests() {
		return autoAcceptRequests;
	}

	public void setAutoAcceptRequests(boolean autoAcceptRequests) {
		this.autoAcceptRequests = autoAcceptRequests;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public boolean isModdedItemsAllowed() {
		return moddedItemsAllowed;
	}

	public void setModdedItemsAllowed(boolean moddedItemsAllowed) {
		this.moddedItemsAllowed = moddedItemsAllowed;
	}

	public AsyncType getAsyncType() {
		return asyncType;
	}

	public void setAsyncType(AsyncType asyncType) {
		this.asyncType = asyncType;
	}
	
	
}
