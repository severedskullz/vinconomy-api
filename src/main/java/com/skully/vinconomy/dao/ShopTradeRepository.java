package com.skully.vinconomy.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.skully.vinconomy.enums.TradeStatus;
import com.skully.vinconomy.model.ShopId;
import com.skully.vinconomy.model.ShopTrade;

@Repository
public interface ShopTradeRepository extends CrudRepository<ShopTrade, Long> {

	List<ShopTrade> findAllByShopIdAndStatus(ShopId id, TradeStatus status);
	List<ShopTrade> findAllByOriginNodeIdAndStatus(long id, TradeStatus status);

}
