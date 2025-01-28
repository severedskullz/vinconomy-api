package com.skully.vinconomy.enums;

public enum AsyncType {
	
	/** Containers will up updated based on the push/pull requests. Meant for more "fun" than realism.
	 * For purchases, the item will always be given to the customer even if the seller does not have enough stock to cover the transaction
	 * This could lead to a possibility of desync's where the seller is over-sold or under-sold, meaning a sale was made offline, but system never informed the seller
	 */
	ASYNC, 
	
	/**
	 * Players have to store items in a sort of Depot for both currencies and products. Meant for Web-Trading
	 * Items can be added/removed from the depot by performing a handshaking mechanism to deposit/withdraw items from the database
	 * This is mostly meant for third party web-based apps for buying/selling items without having to be in game
	 */
	DEPOT, 
	
	/**
	 * Shop stalls must query the database each time a sale is requested, ensuring stock exists on the server before purchase can be made
	 * This is meant for ultra-realistic servers where every piece of stock matters - also the most expensive. Not really meant to be used unless there is a good reason.
	 * Instead of inventories being stored on the game's save data, it is stored in the database, meaning loss or corruption of the database results in lost items ingame.
	 */
	REAL_TIME 
}
