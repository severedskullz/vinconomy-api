package com.skully.vinconomy.util;

public class GameUtils {
	
	private static final String GUID_REGEX = "[a-zA-Z0-9]{8}\\-[a-zA-Z0-9]{4}\\-[a-zA-Z0-9]{4}\\-[a-zA-Z0-9]{4}\\-[a-zA-Z0-9]{12}";

	public static boolean isInvalidGUID(String guid) {
		return guid == null || !guid.matches(GUID_REGEX);
	}
	
}
