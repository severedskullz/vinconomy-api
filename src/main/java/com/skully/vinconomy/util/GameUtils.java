package com.skully.vinconomy.util;

import java.util.Optional;

public class GameUtils {
	
	private static final String GUID_REGEX = "[a-zA-Z0-9]{8}\\-[a-zA-Z0-9]{4}\\-[a-zA-Z0-9]{4}\\-[a-zA-Z0-9]{4}\\-[a-zA-Z0-9]{12}";

	public static boolean isInvalidGUID(String guid) {
		return guid == null || !guid.matches(GUID_REGEX);
	}

	public static <T> T getOptional(Optional<T> byId) {
		if (byId.isPresent())
			return byId.get();
		return null;
	}
	
}
