package com.aurionpro.common;

import java.security.SecureRandom;

public class RandomPassportGenerator {
    
    private static final String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final SecureRandom random = new SecureRandom();

    public static String generatePassportNumber() {
        // Generate 2 letters
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 2; i++) {
            sb.append(LETTERS.charAt(random.nextInt(LETTERS.length())));
        }

        // Generate 7 digits
        for (int i = 0; i < 7; i++) {
            sb.append(random.nextInt(10));  // 0–9
        }

        return sb.toString();
    }
}
