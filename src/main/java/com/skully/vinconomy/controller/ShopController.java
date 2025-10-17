package com.skully.vinconomy.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.skully.vinconomy.model.Shop;
import com.skully.vinconomy.model.ShopRegistration;
import com.skully.vinconomy.model.ShopTrade;
import com.skully.vinconomy.model.dto.ShopProducts;
import com.skully.vinconomy.model.dto.ShopTradeRequest;
import com.skully.vinconomy.model.dto.ShopTradeStatusUpdate;
import com.skully.vinconomy.model.dto.TradeNetworkShop;
import com.skully.vinconomy.security.ApiKeyAuthentication;
import com.skully.vinconomy.service.ShopService;

@RestController()
@RequestMapping("/api/shop")
public class ShopController {

	@Autowired
	ShopService shopService;
	
	@GetMapping("/list")
	public String getShops() {
		return "Blah";
	}
	
	@PreAuthorize("hasAuthority('GAME_API')")
	@PutMapping("/register")
	public Shop registerShop(@RequestBody() ShopRegistration reg, ApiKeyAuthentication auth) {
		//ApiKeyAuthentication auth = (ApiKeyAuthentication) SecurityContextHolder.getContext().getAuthentication();
		if (reg.getId() <= 0) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID is required");
		}
		
		if (StringUtils.isBlank(reg.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name is required");
		}
		
		if (StringUtils.isBlank(reg.getOwner())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name is required");
		}
				
		return shopService.registerShop(reg, auth.getNode());
	}
	
	@PreAuthorize("hasAuthority('GAME_API')")
	@PatchMapping("/products/{shopId}")
	//TODO: Perhaps it should be List<ShopProducts> instead?
	public String updateShopProducts(@RequestBody() ShopProducts reg, @PathVariable("shopId") int shopId, ApiKeyAuthentication auth) {
		//ApiKeyAuthentication auth = (ApiKeyAuthentication) SecurityContextHolder.getContext().getAuthentication();
		return shopService.updateProducts(reg, auth.getNode());
	}
	
	@PreAuthorize("hasAuthority('GAME_API')")
	@DeleteMapping("/{shopId}")
	public String deleteShop(@PathVariable("shopId") int shopId, ApiKeyAuthentication auth) {
		return shopService.deleteShop(auth.getNode(), shopId);
	}
	
	/**
	 * Gets the current inventory for the given shop register
	 * @return
	 */
	@PreAuthorize("hasAuthority('GAME_API')")
	@GetMapping("/inventory/{networkId}/{shopId}")
	public TradeNetworkShop getShopInventory(@PathVariable("networkId") String networkId, @PathVariable("shopId") int shopId) {
		return shopService.getShopInventory(networkId, shopId);
	}
	
	/**
	 * Gets the current inventory for the given shop register
	 * @return
	 */
	@PreAuthorize("hasAuthority('GAME_API')")
	@GetMapping("/inventory/{networkId}/{shopId}/{x}/{y}/{z}")
	public String getShopStallInventory(@PathVariable("networkId") String networkId, @PathVariable("shopId") int shopId, @PathVariable("x") int x, @PathVariable("y") int y, @PathVariable("z") int z, ApiKeyAuthentication auth) {
		throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
	}
	
	
	/**
	 * Gets the currently pending trades for the given network and shop ID that need to be accepted
	 * @return
	 */
	@PreAuthorize("hasAuthority('GAME_API')")
	@GetMapping("/trades/{shopId}")
	public List<ShopTrade> getPendingTrades(@PathVariable("shopId") int shopId, ApiKeyAuthentication auth) {
		return shopService.getPendingTrades(auth.getNode(), shopId);
	}
	
	
	/**
	 * Adds a new purchase order for the given network and shop ID
	 * @return
	 */
	@PreAuthorize("hasAuthority('GAME_API')")
	@PutMapping("/trades/{networkId}/{shopId}")
	public String addPendingTrade(@RequestBody() ShopTradeRequest req, @PathVariable("networkId") String networkId, @PathVariable("shopId") long shopId, ApiKeyAuthentication auth) {
		shopService.addPendingTrade(networkId, shopId, req, auth.getNode());
		return "Success";
	}
	
	/**
	 * Processes a requested purchase order for the logged in network and shop ID.
	 * @return
	 */
	@PreAuthorize("hasAuthority('GAME_API')")
	@PatchMapping("/trades/{tradeId}")
	public String processPendingTrade(@RequestBody() ShopTradeStatusUpdate req, @PathVariable("tradeId") long tradeId, ApiKeyAuthentication auth) {
		try {
		shopService.updatePendingTrade(tradeId, req.getStatus(), auth.getNode());
		}
		catch (IllegalStateException | IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
		catch (AccessDeniedException e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
		}
		return "Success";
	}
	
	// View Posted Trades
	// Add Posted Trade
	// Accept Posted Trade
	// Recall Posted Trade
}
