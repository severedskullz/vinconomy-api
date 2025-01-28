package com.skully.vinconomy.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.PostConstruct;

@RestController()
@RequestMapping("/api/market")
public class MarketController {

	@PostConstruct
	public void Blah() {
		System.out.println("We live!");
	}
	
	@GetMapping("/shops")
	public String getShops() {
		return "Blah";
	}
	
	
	// View Posted Trades
	// Add Posted Trade
	// Accept Posted Trade
	// Recall Posted Trade
}
