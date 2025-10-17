package com.skully.vinconomy.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.skully.vinconomy.dao.ShopRepository;
import com.skully.vinconomy.dao.ShopTradeRepository;
import com.skully.vinconomy.dao.TradeNetworkNodeRepository;
import com.skully.vinconomy.enums.TradeStatus;
import com.skully.vinconomy.model.Shop;
import com.skully.vinconomy.model.ShopId;
import com.skully.vinconomy.model.ShopTrade;
import com.skully.vinconomy.model.TradeNetworkNode;
import com.skully.vinconomy.model.dto.SearchOptions;
import com.skully.vinconomy.model.dto.SearchResult;
import com.skully.vinconomy.model.dto.ShopPurchaseUpdate;
import com.skully.vinconomy.model.dto.ShopTradeUpdate;
import com.skully.vinconomy.util.GameUtils;

@Service
public class MarketService {

	@Autowired
	ShopTradeRepository tradeDao;
	
	@Autowired
	ShopRepository shopDao;
	
	@Autowired
	ShopService shopService;
	
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
			trade.setOriginNode(targetNode);
			trade.setRequestingNode(node);
			Timestamp time = Timestamp.from(Instant.now());
			trade.setCreated(time);
			trade.setModified(time);
			tradeDao.save(trade);
		}
		return null;
	}

	public List<ShopTradeUpdate> getPurchasedItems(TradeNetworkNode node) {
		
		List<ShopTrade> trades = tradeDao.findAllByOriginNodeIdAndStatus(node.getId(), TradeStatus.PENDING);
		List<ShopTradeUpdate> updates = new LinkedList<>();
		for	(ShopTrade trade : trades) {
			ShopTradeUpdate update = new ShopTradeUpdate();
			update.setId(trade.getId());
			update.setStatus(trade.getStatus());
			update.setRequestingNode(node.getGuid());
			update.setShopId(trade.getShop().getId().getShopId());
			update.setX(trade.getX());
			update.setY(trade.getY());
			update.setZ(trade.getZ());
			update.setAmount(trade.getAmount());
			update.setPlayerGuid(trade.getPlayerGuid());
			update.setName(trade.getName());
			update.setStallSlot(trade.getStallSlot());
			update.setOriginNode(trade.getRequestingNode().getGuid());
			update.setCreated(trade.getCreated());
			update.setModified(trade.getModified());
			updates.add(update);
		}
		return updates;
	}

	public String updatePurchaseItems(List<ShopTradeUpdate> updates, TradeNetworkNode node, boolean isBuyer) {
		
		for (ShopTradeUpdate update : updates) {
			//Optional<ShopTrade> tradeOpt = tradeDao.findById(update.getId());
			//if (tradeOpt.isPresent()) {
				//ShopTrade trade = tradeOpt.get();		
				try {
					shopService.updatePendingTrade(update.getId(), update.getStatus(), node);
				} catch (Exception e) {
					// Log error
				}
				
			//}
		}
		
		return "SUCCESS";
	}

	public List<SearchResult> searchItems(SearchOptions searchOptions, TradeNetworkNode node) {
		if (node.getNetwork() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This node does not belong to a network");
		}
		return shopDao.searchShopsFor(node.getNetwork().getId(), searchOptions.getOwner(), searchOptions.getShopName(), searchOptions.getProductName(), searchOptions.getCurrencyName());
	}

}
