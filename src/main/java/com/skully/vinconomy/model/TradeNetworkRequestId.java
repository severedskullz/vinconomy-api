package com.skully.vinconomy.model;

import java.io.Serializable;
import java.util.Objects;

public class TradeNetworkRequestId implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private long node;
	
	private long network;

	public TradeNetworkRequestId() { //No Arg Constructor for Hibernate
		
	}
	
	public TradeNetworkRequestId(long networkId, long nodeId) {
		super();
		this.network = networkId;
		this.node = nodeId;
	}

	
	
	@Override
	public int hashCode() {
		return Objects.hash(network, node);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TradeNetworkRequestId other = (TradeNetworkRequestId) obj;
		return Objects.equals(network, other.network) && Objects.equals(node, other.node);
	}

	public long getNode() {
		return node;
	}

	public void setNode(long node) {
		this.node = node;
	}

	public long getNetwork() {
		return network;
	}

	public void setNetwork(long network) {
		this.network = network;
	}


	
	
}
