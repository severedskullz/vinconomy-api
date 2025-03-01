package com.skully.vinconomy.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skully.vinconomy.dao.ShopRepository;
import com.skully.vinconomy.dao.ShopTradeRepository;
import com.skully.vinconomy.dao.TradeNetworkNodeRepository;
import com.skully.vinconomy.enums.TradeStatus;
import com.skully.vinconomy.model.Shop;
import com.skully.vinconomy.model.ShopId;
import com.skully.vinconomy.model.ShopTrade;
import com.skully.vinconomy.model.TradeNetworkNode;
import com.skully.vinconomy.model.dto.ShopPurchaseUpdate;
import com.skully.vinconomy.util.GameUtils;

@Service
public class MarketService {

	@Autowired
	ShopTradeRepository tradeDao;
	
	@Autowired
	ShopRepository shopDao;
	
	@Autowired
	TradeNetworkNodeRepository nodeDao;
	
	public String purchaseItems(List<ShopPurchaseUpdate> updates, TradeNetworkNode node) {
		
		for (ShopPurchaseUpdate update : updates) {
			TradeNetworkNode targetNode = nodeDao.findByGuid(update.getNodeId());
			if (targetNode == null) {
				
				continue;
			}
			
			
			ShopId shopId = new ShopId(targetNode.getId(), update.getShopId());
			Shop targetShop = GameUtils.getOptional(shopDao.findById(shopId));
			if (targetShop == null) {
				continue;
			}
			
			ShopTrade trade = new ShopTrade();
			trade.setStatus(TradeStatus.PENDING);
			trade.setRequestingNode(node);
			trade.setShop(targetShop);
			trade.setX(update.getX());
			trade.setY(update.getY());
			trade.setZ(update.getZ());
			trade.setAmount(update.getAmount());
			trade.setPlayerGuid(update.getPlayerGuid());
			trade.setName(update.getName());
			trade.setStallSlot(update.getStallSlot());
			trade.setTargetNode(targetNode);
			trade.setRequestingNode(node);
			Timestamp time = Timestamp.from(Instant.now());
			trade.setCreated(time);
			trade.setModified(time);
			tradeDao.save(trade);
		}
		return null;
	}

	public List<ShopTrade> getPurchasedItems(TradeNetworkNode node) {
		
		List<ShopTrade> trades = tradeDao.findAllByOriginNodeIdAndStatus(node.getId(), TradeStatus.PENDING);
		return trades;
	}

}
