package com.skully.vinconomy.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.apache.commons.lang3.RandomStringUtils;

public class PasswordUtils {

    public static String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    public static String hashPassword(String password, String salt) 
            throws NoSuchAlgorithmException, InvalidKeySpecException {

        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), Base64.getDecoder().decode(salt), 65536, 256);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        byte[] hash = factory.generateSecret(spec).getEncoded();
        return Base64.getEncoder().encodeToString(hash);
    }

    public static boolean verifyPassword(String password, String storedHash, String storedSalt) 
            throws NoSuchAlgorithmException, InvalidKeySpecException {

        String newHash = hashPassword(password, storedSalt);
        return Arrays.equals(Base64.getDecoder().decode(storedHash), Base64.getDecoder().decode(newHash));
    }
    
	 public static String generatePassword() {
		    String upperCaseLetters = RandomStringUtils.secure().next(16, 65, 90, true, true);
		    String lowerCaseLetters = RandomStringUtils.secure().next(24, 97, 122, true, true);
		    String numbers = RandomStringUtils.secure().nextNumeric(12);
		    
		    String specialCharList = "!@#$%^&*()";
		    String specialChar = RandomStringUtils.secure().next(8, specialCharList);
		    String totalChars = RandomStringUtils.secure().nextAlphanumeric(20);
		    String combinedChars = upperCaseLetters.concat(lowerCaseLetters)
		      .concat(numbers)
		      .concat(specialChar)
		      .concat(totalChars);
		    List<Character> pwdChars = combinedChars.chars()
		      .mapToObj(c -> (char) c)
		      .collect(Collectors.toList());
		    Collections.shuffle(pwdChars);
		    String password = pwdChars.stream()
		      .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
		      .toString();
		    return password;
		}
}