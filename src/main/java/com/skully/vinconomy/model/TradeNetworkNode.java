package com.skully.vinconomy.model;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class TradeNetworkNode {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String serverName;
	@Column(unique = true)
	private String guid;
	private String apiKey;
	private String hostname;
	private String ip;
	private String owner;
	private int udpPort;
	private Timestamp lastAccessed;
	@ManyToOne
	private TradeNetwork network;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getServerName() {
		return serverName;
	}
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	public int getUdpPort() {
		return udpPort;
	}
	public void setUdpPort(int udpPort) {
		this.udpPort = udpPort;
	}
	public Timestamp getLastAccessed() {
		return lastAccessed;
	}
	public void setLastAccessed(Timestamp lastAccessed) {
		this.lastAccessed = lastAccessed;
	}
	public String getHostname() {
		return hostname;
	}
	public void setHostname(String hostname) {
		
		if (hostname.equals("0:0:0:0:0:0:0:1")) hostname = "127.0.0.1";
		this.hostname = hostname;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		if (ip.equals("0:0:0:0:0:0:0:1")) ip = "127.0.0.1";
		this.ip = ip;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getApiKey() {
		return apiKey;
	}
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	public TradeNetwork getNetwork() {
		return network;
	}
	public void setNetwork(TradeNetwork network) {
		this.network = network;
	}
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
}
