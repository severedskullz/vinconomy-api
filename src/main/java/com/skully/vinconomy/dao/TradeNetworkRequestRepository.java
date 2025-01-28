package com.skully.vinconomy.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.skully.vinconomy.model.TradeNetworkRequest;

@Repository
public interface TradeNetworkRequestRepository extends CrudRepository<TradeNetworkRequest, Long> {

	@Query("SELECT t FROM TradeNetworkRequest t WHERE t.network.id = ?1 AND t.node.id = ?2")
	TradeNetworkRequest findByNetworkAndNode(long network, long node);
	
}
