package com.emmerseweb.util;

import java.security.SecureRandom;
import java.util.Random;

public class GenerateTestDataUtil {
    private static final String DOMAIN = "@example.com";
    private static final String CHARACTERS =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final String SPECIAL_CHARACTERS = "!@#$%^&*()-_+=<>?";
    private static final Random RANDOM = new SecureRandom();

    // Generate a random email
    public static String generateEmail() {
        String prefix = "user" + System.currentTimeMillis(); // Unique prefix
        return prefix + DOMAIN;
    }

    // Generate a random Sri Lankan phone number (07X-XXXXXXX)
    public static String generateSriLankanPhoneNumber() {
        String[] validPrefixes = {
                "070", "071", "072", "074", "075", "076", "077", "078"
        }; // SL Mobile prefixes
        String prefix = validPrefixes[RANDOM.nextInt(validPrefixes.length)];
        StringBuilder phoneNumber = new StringBuilder(prefix);

        for (int i = 0; i < 7; i++) { // Generate last 7 digits
            phoneNumber.append(RANDOM.nextInt(10));
        }

        return phoneNumber.toString();
    }

    // Generate a strong password (8-12 characters, includes uppercase, lowercase, numbers, special
    // chars)
    public static String generateStrongPassword() {
        int length = RANDOM.nextInt(5) + 8; // Random length between 8-12

        StringBuilder password = new StringBuilder();
        password.append(getRandomCharacter("ABCDEFGHIJKLMNOPQRSTUVWXYZ")); // 1 uppercase
        password.append(getRandomCharacter("abcdefghijklmnopqrstuvwxyz")); // 1 lowercase
        password.append(getRandomCharacter("0123456789")); // 1 number
        password.append(getRandomCharacter(SPECIAL_CHARACTERS)); // 1 special char

        // Fill the rest of the password
        for (int i = 4; i < length; i++) {
            password.append(getRandomCharacter(CHARACTERS + SPECIAL_CHARACTERS));
        }

        // Shuffle password to randomize character order
        return shuffleString(password.toString());
    }

    private static char getRandomCharacter(String source) {
        return source.charAt(RANDOM.nextInt(source.length()));
    }

    private static String shuffleString(String input) {
        char[] array = input.toCharArray();
        for (int i = array.length - 1; i > 0; i--) {
            int index = RANDOM.nextInt(i + 1);
            char temp = array[i];
            array[i] = array[index];
            array[index] = temp;
        }
        return new String(array);
    }
}
