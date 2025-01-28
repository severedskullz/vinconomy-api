package com.skully.vinconomy.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.skully.vinconomy.model.TradeNetwork;

@Repository
public interface TradeNetworkRepository extends CrudRepository<TradeNetwork, Long> {

	TradeNetwork findByNetworkAccessKey(String networkAccessKey);

}
