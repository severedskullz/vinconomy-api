package com.skully.vinconomy.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.skully.vinconomy.model.TradeNetwork;
import com.skully.vinconomy.model.TradeNetworkNode;
import com.skully.vinconomy.model.dto.TradeNetworkJoinRequest;
import com.skully.vinconomy.model.dto.TradeNetworkJoinResult;
import com.skully.vinconomy.model.dto.TradeNetworkNodeRegistration;
import com.skully.vinconomy.model.dto.TradeNetworkRegistration;
import com.skully.vinconomy.security.ApiUserDetails;
import com.skully.vinconomy.service.TradeNetworkService;

import jakarta.servlet.http.HttpServletRequest;

@RestController()
@RequestMapping("/api/network")
public class TradeNetworkController {
	
	@Autowired
	TradeNetworkService service;
	
	@RequestMapping(path = "/node", method = RequestMethod.PUT)
	public TradeNetworkNode registerTradeNetworkNode(@RequestBody() TradeNetworkNodeRegistration reg, HttpServletRequest request) {
		
		if (StringUtils.isBlank(reg.getName())) 
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name cannot be blank");
		
		if (StringUtils.isBlank(reg.getGuid())) 
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "GUID cannot be blank");

		
		reg.setHost(request.getRemoteHost());
		reg.setIp(request.getRemoteAddr());
		reg.setUsername( request.getRemoteUser());
		return service.registerTradeNetworkNode(reg);
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(method = RequestMethod.PUT)
	public TradeNetwork registerTradeNetwork(@RequestBody() TradeNetworkRegistration reg, @AuthenticationPrincipal ApiUserDetails user) {
		
		if (StringUtils.isBlank(reg.getName())) 
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name cannot be blank");
	
		return service.registerTradeNetwork(reg, user.getUser());
	}
	
	/**
	 * Attempts to join a given trade network. If auto-accept is enabled for the given network, they will receive access instantly.
	 * Otherwise, a new request should be created for the other to accept or reject.
	 * If there is already a pending request, it shall return the status of the request
	 * @param networkId
	 * @return
	 */
	@PostMapping("/join")
	public TradeNetworkJoinResult joinTradeNetwork(@RequestBody() TradeNetworkJoinRequest reg) {
		reg.setUsername(null); //Prevent malicious insertion of "username" into request
		return service.requestJoinTradeNetwork(reg);
	}
	
	@PostMapping("/{networkid}/join")
	public TradeNetworkJoinResult joinTradeNetwork(@PathVariable("networkid") Long networkId, @RequestBody() TradeNetworkJoinRequest reg, @AuthenticationPrincipal User user) {
		reg.setNetworkId(networkId);
		reg.setUsername(user.getUsername());
		return service.requestJoinTradeNetwork(reg);
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/{networkid}/requests")
	public String viewJoinRequests(@PathVariable("networkid") String networkId, String requestId, @AuthenticationPrincipal User user) {
		return "Blahddd";
	}
	
	@GetMapping("/{networkid}/requests/acceptall")
	public String acceptAllJoinRequests(@PathVariable("networkid") String networkId, String requestId, @AuthenticationPrincipal User user) {
		return "Blah";
	}
	
	@GetMapping("/{networkid}/requests/rejectall")
	public String rejectAllJoinRequests(@PathVariable("networkid") String networkId, String requestId, @AuthenticationPrincipal User user) {
		return "Blah";
	}
	
	@GetMapping("/{networkid}/requests/{requestid}")
	public String viewJoinRequest(@PathVariable("networkid") String networkId, @PathVariable("requestid") String requestId, @AuthenticationPrincipal User user) {
		return "Blah";
	}
	
	@PostMapping("/{networkid}/requests/{requestid}/accept")
	public String acceptJoinRequest(@PathVariable("networkid") String networkId, @PathVariable("requestid") String requestId, @AuthenticationPrincipal User user) {
		return "Blah";
	}
	
	@PostMapping("/{networkid}/requests/{requestid}/reject")
	public String rejectJoinRequest(@PathVariable("networkid") String networkId, @PathVariable("requestid") String requestId, @AuthenticationPrincipal User user) {
		return "Blah";
	}
	
	@RequestMapping(path = "/{networkid}", method = RequestMethod.PATCH)
	public String updateTradeNetwork(@PathVariable("networkid") String networkId, @AuthenticationPrincipal User user, @RequestBody() TradeNetworkNodeRegistration reg) {
		return "";
	}
	
	@RequestMapping(path = "/{networkid}", method = RequestMethod.DELETE)
	public String deleteTradeNetwork(@PathVariable("networkid") String networkId, @AuthenticationPrincipal User user) {
		return "";
	}
	
	// View Posted Trades
	// Add Posted Trade
	// Accept Posted Trade
	// Recall Posted Trade
}
