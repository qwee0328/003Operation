package com.operation.commons;

import java.security.MessageDigest;

public class EncryptionUtils {

	// SHA-512 해시 알고리즘
	public static String getSHA512(String input) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-512");
			digest.reset();
			digest.update(input.getBytes("UTF-8"));
			return String.format("%0128x", new java.math.BigInteger(1, digest.digest()));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
