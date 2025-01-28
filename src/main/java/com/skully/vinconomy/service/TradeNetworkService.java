package com.skully.vinconomy.service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.skully.vinconomy.dao.TradeNetworkNodeRepository;
import com.skully.vinconomy.dao.TradeNetworkRepository;
import com.skully.vinconomy.dao.TradeNetworkRequestRepository;
import com.skully.vinconomy.enums.RequestStatus;
import com.skully.vinconomy.model.ApiUser;
import com.skully.vinconomy.model.TradeNetwork;
import com.skully.vinconomy.model.TradeNetworkNode;
import com.skully.vinconomy.model.TradeNetworkRequest;
import com.skully.vinconomy.model.dto.TradeNetworkJoinRequest;
import com.skully.vinconomy.model.dto.TradeNetworkJoinResult;
import com.skully.vinconomy.model.dto.TradeNetworkNodeRegistration;
import com.skully.vinconomy.model.dto.TradeNetworkRegistration;
import com.skully.vinconomy.util.GameUtils;
import com.skully.vinconomy.util.PasswordUtils;

@Service
public class TradeNetworkService {

	Logger logger = LoggerFactory.getLogger(TradeNetworkService.class);
	
	@Autowired
	TradeNetworkRepository tradeNetworkRepo;
	
	@Autowired
	TradeNetworkNodeRepository tradeNetworkNodeRepo;
	
	@Autowired
	TradeNetworkRequestRepository tradeNetworkRequestRepo;
	
	
	public TradeNetworkNode registerTradeNetworkNode(TradeNetworkNodeRegistration reg) {
		
		if (GameUtils.isInvalidGUID(reg.getGuid()))
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "GUID is invalid");
		
		TradeNetworkNode node = new TradeNetworkNode();
		node.setServerName(reg.getName());
		node.setGuid(reg.getGuid());
		node.setLastAccessed(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
		node.setHostname(reg.getHost());
		node.setIp(reg.getIp());
		node.setOwner(reg.getUsername());
		node.setApiKey(PasswordUtils.generatePassword());
		
		//0509d2e4-88a3-4c0e-8fbb-be998f4af4b6
		return tradeNetworkNodeRepo.save(node);
	}
	
	public TradeNetwork registerTradeNetwork(TradeNetworkRegistration reg, ApiUser user) {
		return registerTradeNetwork(reg, user, null);
	}
	
	public TradeNetwork registerTradeNetwork(TradeNetworkRegistration reg, ApiUser user, String accessKey) {
		TradeNetwork network = new TradeNetwork();
		network.setName(reg.getName());
		network.setOwner(user);
		network.setDescription(reg.getDescription());
		network.setAutoAcceptRequests(reg.isAutoAcceptRequests());
		network.setVisible(reg.isVisible());
		network.setAsyncType(reg.getAsyncType());
		if (StringUtils.isBlank(accessKey))
			network.setNetworkAccessKey(PasswordUtils.generatePassword());
		else {
			network.setNetworkAccessKey(accessKey);
		}
		
		//0509d2e4-88a3-4c0e-8fbb-be998f4af4b6
		return tradeNetworkRepo.save(network);
	}

	public TradeNetworkJoinResult requestJoinTradeNetwork(TradeNetworkJoinRequest reg) {
		
		TradeNetwork network = null;
		TradeNetworkNode node = null;
		TradeNetworkRequest request = null;
		
		// Validation
		if (GameUtils.isInvalidGUID(reg.getGuid()))
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "GUID is invalid");
		
		// Check if the node already exists, and if so, if it is owned by a different user
		node = tradeNetworkNodeRepo.findByGuid(reg.getGuid());
		if (node != null && !node.getOwner().equals("ANONYMOUS") && !node.getOwner().equals(reg.getUsername()))
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "GUID is claimed by a different account");
		
		// Check if network they are trying to join is valid
		
		if (StringUtils.isNotBlank(reg.getNetworkAccessKey())) {
			network = tradeNetworkRepo.findByNetworkAccessKey(reg.getNetworkAccessKey());
		} else if (reg.getNetworkId() != null){
			network = getOptional(tradeNetworkRepo.findById(reg.getNetworkId()));
		}
		
		if (network == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Network is not valid");
		}
		// End Validation
			
		Timestamp instant = Timestamp.from(Calendar.getInstance().toInstant());
		// Handle creation and updates of the node itself
		if (node == null) {
			node = new TradeNetworkNode();
			node.setGuid(reg.getGuid());
			node.setServerName(reg.getServerName());
			if (StringUtils.isBlank(reg.getUsername())) {
				node.setOwner("ANONYMOUS");
			} else {
				node.setOwner(reg.getUsername());
			}
			
			String apiKey = PasswordUtils.generatePassword();
			node.setApiKey(apiKey);
			node.setLastAccessed(instant);
			node = tradeNetworkNodeRepo.save(node);
		}
		
		// Lookup any existing Network Join requests for this node. If one exists, make sure the guid was not banned
		// If it was rejected or removed (due to inactivity) then re-request access
		request = tradeNetworkRequestRepo.findByNetworkAndNode(network.getId(), node.getId());
		if (request == null) { 
			request = new TradeNetworkRequest();
			request.setNetwork(network);
			request.setNode(node);
			request.setCreatedAt(instant);
			request.setStatus(RequestStatus.PENDING);
		} 
		
		switch(request.getStatus()) {
			//case null: // If it wasn't set for some reason (E.g. a new request);
			case REJECTED: // If our application was previously rejected
			case REMOVED: // If we joined and were removed at some point
			case PENDING: // If its already pending, see if auto-join was turned on after we originally requested
				if (network.isAutoAcceptRequests()) {
					node.setNetwork(network);
					request.setStatus(RequestStatus.ACCEPTED);
					node = tradeNetworkNodeRepo.save(node);
				}
				request.setLastModified(instant);
				request = tradeNetworkRequestRepo.save(request);
				break;
			case BANNED: // Banned users cannot re-request access;
			case ACCEPTED: // Don't revert Accepted back to Pending
			default:
				//Do nothing. Either we shouldn't go back to PENDING status, or can't (Banned).
				break;
		}
				
		
		TradeNetworkJoinResult result = new TradeNetworkJoinResult();
		result.setApiKey(node.getApiKey());
		result.setStatus(request.getStatus());
		result.setMessage(request.getMessage());
		return result;
	}
	


	private static <T> T getOptional(Optional<T> optional) {
		if (optional.isEmpty()) {
			return null;
		}
		return optional.get();
	}

	public TradeNetwork findByNetworkAccessKey(String key) {
		return tradeNetworkRepo.findByNetworkAccessKey(key);
	}
	

	
}
