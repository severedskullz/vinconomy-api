package com.skully.vinconomy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skully.vinconomy.dao.ShopRepository;
import com.skully.vinconomy.model.Shop;
import com.skully.vinconomy.model.ShopId;

import jakarta.websocket.server.PathParam;

@RestController()
@RequestMapping("/api/shop")
public class ShopController {

	@Autowired
	ShopRepository dao;
	
	@GetMapping("/list")
	public String getShops() {
		Shop shop = new Shop();
		shop.setName("Test");
		shop.setId( new ShopId("TestNetworkId", 1234));
		dao.save(shop);
		return "Blah";
	}
	
	@PostMapping("/{networkId}/register")
	public String registerShop(@PathParam("networkId") String networkId) {
		return "Blah";
	}
	
	@PostMapping("/{networkId}/{shopId}/remove")
	public String deleteShop() {
		return "Blah";
	}
	
	/**
	 * Gets the currently pending trades for the given network and shop ID that need to be accepted
	 * @return
	 */
	@GetMapping("/{networkId}/{shopId}/trades")
	public String getPendingTrades() {
		return "Blah";
	}
	
	
	/**
	 * Adds a new purchase order for the given network and shop ID
	 * @return
	 */
	@PostMapping("/{networkId}/{shopId}/trades")
	public String addPendingTrade() {
		return "Blah";
	}
	
	// View Posted Trades
	// Add Posted Trade
	// Accept Posted Trade
	// Recall Posted Trade
}
