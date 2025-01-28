package com.skully.vinconomy.model;

import java.sql.Timestamp;

import com.skully.vinconomy.enums.RequestStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
@IdClass(TradeNetworkRequestId.class)
public class TradeNetworkRequest {

	
	@Id
	@ManyToOne
	private TradeNetwork network;
	
	@Id
	@OneToOne
	private TradeNetworkNode node;
	
	@Enumerated(EnumType.STRING)
	private RequestStatus status;
	
	private String message;
	
	private Timestamp createdAt;
	private Timestamp lastModified;
	
	public TradeNetwork getNetwork() {
		return network;
	}
	public void setNetwork(TradeNetwork network) {
		this.network = network;
	}
	public TradeNetworkNode getNode() {
		return node;
	}
	public void setNode(TradeNetworkNode node) {
		this.node = node;
	}
	public RequestStatus getStatus() {
		return status;
	}
	public void setStatus(RequestStatus status) {
		this.status = status;
	}
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	public Timestamp getLastModified() {
		return lastModified;
	}
	public void setLastModified(Timestamp lastModified) {
		this.lastModified = lastModified;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
