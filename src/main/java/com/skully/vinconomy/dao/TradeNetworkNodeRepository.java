package com.skully.vinconomy.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.skully.vinconomy.model.TradeNetworkNode;

@Repository
public interface TradeNetworkNodeRepository extends CrudRepository<TradeNetworkNode, Long> {

	TradeNetworkNode findByGuid(String guid);

	TradeNetworkNode findByApiKey(String apiKey);

}
