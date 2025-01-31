package com.skully.vinconomy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import com.skully.vinconomy.dao.ShopProductId;
import com.skully.vinconomy.dao.ShopProductRepository;
import com.skully.vinconomy.dao.ShopRepository;
import com.skully.vinconomy.model.Shop;
import com.skully.vinconomy.model.ShopId;
import com.skully.vinconomy.model.ShopProduct;
import com.skully.vinconomy.model.ShopRegistration;
import com.skully.vinconomy.model.TradeNetworkNode;
import com.skully.vinconomy.model.dto.Product;
import com.skully.vinconomy.model.dto.ShopProducts;
import com.skully.vinconomy.model.dto.StallProduct;
import com.skully.vinconomy.util.GameUtils;

@Repository
public class ShopService {

	
	@Autowired
	ShopRepository shopDao;
	
	@Autowired
	ShopProductRepository productDao;
	
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
		
		List<StallProduct> stallList = shopProducts.getProducts();
		
		for (StallProduct stall : stallList) {
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
	
	public void updateShopProducts(Shop shop, List<ShopProduct> products) {
		
	}

}
