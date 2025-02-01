package com.skully.vinconomy.service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import com.skully.vinconomy.dao.ShopProductRepository;
import com.skully.vinconomy.dao.ShopRepository;
import com.skully.vinconomy.dao.ShopTradeRequestRepository;
import com.skully.vinconomy.enums.TradeStatus;
import com.skully.vinconomy.model.Shop;
import com.skully.vinconomy.model.ShopId;
import com.skully.vinconomy.model.ShopProduct;
import com.skully.vinconomy.model.ShopProductId;
import com.skully.vinconomy.model.ShopRegistration;
import com.skully.vinconomy.model.ShopTrade;
import com.skully.vinconomy.model.TradeNetworkNode;
import com.skully.vinconomy.model.dto.Product;
import com.skully.vinconomy.model.dto.ShopProducts;
import com.skully.vinconomy.model.dto.ShopStall;
import com.skully.vinconomy.model.dto.ShopTradeRequest;
import com.skully.vinconomy.util.GameUtils;

@Repository
public class ShopService {

	
	@Autowired
	ShopRepository shopDao;
	
	@Autowired
	ShopProductRepository productDao;
	
	@Autowired
	ShopTradeRequestRepository tradeRequestDao;
	
	public Shop registerShop(ShopRegistration reg, TradeNetworkNode node) {
		ShopId id = new ShopId(node.getGuid(), reg.getId());
		
		Optional<Shop> existing = shopDao.findById(id);
		if (existing.isPresent())
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Shop already exists");
		
		Shop shop = new Shop();
		shop.setId(id);
		shop.setName(reg.getName());
		shop.setOwner(reg.getOwner());
		shop.setDescription(reg.getDescription());
		
		
		shop = shopDao.save(shop);
		return shop;
	}
	
	public String updateProducts(ShopProducts shopProducts, TradeNetworkNode node) {
		long serverId = node.getId();
		long shopId = shopProducts.getId();
		
		if (shopProducts.isRemoveAll()) {
			productDao.deleteByShopId(serverId, shopId);
		}
		
		List<ShopStall> stallList = shopProducts.getStalls();
		
		for (ShopStall stall : stallList) {
			if (stall.isRemoveAll()) {
				productDao.deleteByStall(serverId, shopId, stall.getX(), stall.getY(), stall.getZ());
			} else {
				List<Product> products = stall.getProducts();
				for (Product product : products) {
					ShopProductId productId = new ShopProductId(serverId, shopId, stall.getX(), stall.getY(), stall.getZ(), product.getStallSlot());
					ShopProduct prod = GameUtils.getOptional(productDao.findById(productId));
					if (prod == null) {
						prod = new ShopProduct();
						prod.setId(productId);
					}
					
					prod.setProductName(product.getProductName());
					prod.setProductCode(product.getProductCode());
					prod.setProductAttributes(product.getProductAttributes());
					prod.setProductQuantity(product.getProductQuantity());
					
					prod.setCurrencyName(product.getCurrencyName());
					prod.setCurrencyCode(product.getCurrencyCode());
					prod.setCurrencyAttributes(product.getCurrencyAttributes());
					prod.setCurrencyQuantity(product.getCurrencyQuantity());
					
					productDao.save(prod);
				}
				
			}
		}
		
		return "Updated!";
		
	}
	
	public String deleteShop(TradeNetworkNode node, long shopId) {
		productDao.deleteByShopId(node.getId(), shopId);
		ShopId id = new ShopId(node.getGuid(), shopId);
		shopDao.deleteById(id);
		return null;
	}

	public List<ShopTrade> getPendingTrades(TradeNetworkNode node, int shopId) {
		return tradeRequestDao.findAllByShopIdAndStatus(new ShopId(node.getGuid(), shopId), TradeStatus.PENDING);
	}

	public ShopTrade addPendingTrade(String networkId, long shopId, ShopTradeRequest req, TradeNetworkNode node) {

		Shop shop = GameUtils.getOptional(shopDao.findById(new ShopId(networkId, shopId)));
		if (shop == null) 
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Shop ID not found");
		
		ShopTrade trade = new ShopTrade();
		trade.setRequestingNode(node);
		
		trade.setShop(shop);
		trade.setX(req.getX());
		trade.setY(req.getY());
		trade.setZ(req.getZ());
		trade.setStallSlot(req.getStallSlot());
		
		trade.setProductCode(req.getProductCode());
		trade.setProductAttributes(req.getProductAttributes());
		trade.setProductQuantity(req.getProductQuantity());
		
		trade.setCurrencyCode(req.getCurrencyCode());
		trade.setCurrencyAttributes(req.getCurrencyAttributes());
		trade.setCurrencyQuantity(req.getCurrencyQuantity());
		
		Timestamp instant = Timestamp.from(Calendar.getInstance().toInstant());
		trade.setCreated(instant);
		trade.setModified(instant);
		
		return tradeRequestDao.save(trade);
	}

}
