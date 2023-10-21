package com.stays.auth;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;

public class PasswordHasher {
    private static final int SALT_LENGTH = 16;
    private static final int ITERATIONS = 65536;
    private static final int KEY_LENGTH = 128;

    public static boolean verifyPassword(String providedPassword, String storedPassword, String storedSalt) {
        byte[] salt = Base64.getDecoder().decode(storedSalt);
        byte[] storedHashedPassword = Base64.getDecoder().decode(storedPassword);
        byte[] hashedPassword = PasswordHasher.hashPassword(providedPassword, salt);

        return Arrays.equals(storedHashedPassword, hashedPassword);
    }

    public static byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_LENGTH];
        random.nextBytes(salt);

        return salt;
    }

    public static byte[] hashPassword(String password, byte[] salt) {
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, ITERATIONS, KEY_LENGTH);
        SecretKeyFactory factory = null;
        byte[] hashedPasswordByteArray = new byte[0];

        try {
            factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            hashedPasswordByteArray = factory.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }

        return hashedPasswordByteArray;
    }
}
