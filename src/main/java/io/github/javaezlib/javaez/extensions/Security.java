package io.github.javaezlib.javaez.extensions;

import io.github.javaezlib.javaez.backend.ErrorSystem;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

/**
 * The JavaEZ Security extension. Allows different security-related functions.
 * @since 1.6
 */
@SuppressWarnings("deprecation")
public class Security {

    /**
     * Locks a piece of text with a password.
     * (For technical people: This function encrypts the data in the text using AES-256-CBC with a PBKDF2-based cipher generated from the password)
     * @param text The text to lock
     * @param password The password to use
     * @return The locked text
     * @since 1.6
     */
    public static String lockText(String text, String password) {
        try {
            byte[] salt = genSaltFromPassword(password);
            PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray(), salt, 65536, 256);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            SecretKey originalKey = factory.generateSecret(keySpec);
            SecretKey key = new SecretKeySpec(originalKey.getEncoded(), "AES");
            byte[] iv = new byte[16];
            new SecureRandom().nextBytes(iv);
            IvParameterSpec ivParamSpec =  new IvParameterSpec(iv);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key, ivParamSpec);
            byte[] encrypted = cipher.doFinal(text.getBytes(StandardCharsets.UTF_8));
            byte[] full = new byte[encrypted.length + iv.length];
            int index = 0;
            for(byte b : iv) {
                full[index] = b;
                index++;
            }
            for(byte b : encrypted) {
                full[index] = b;
                index++;
            }
            return Base64.getEncoder().encodeToString(full);
        } catch(Exception ex) {
            ErrorSystem.handleError("Could not lock text.");
            return null;
        }
    }

    /**
     * Unlocks some text that was locked using {@link #lockText(String, String)}.
     * (For technical people: This function decrypts the data in the text using AES-256-CBC with a PBKDF2-based cipher generated from the password)
     * @param text The locked text to unlock
     * @param password The password used to lock the text
     * @return The unlocked text
     * @since 1.6
     */
    public static String unlockText(String text, String password) {
        try {
            byte[] salt = genSaltFromPassword(password);
            PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray(), salt, 65536, 256);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            SecretKey originalKey = factory.generateSecret(keySpec);
            SecretKey key = new SecretKeySpec(originalKey.getEncoded(), "AES");
            byte[] encrypted = Base64.getDecoder().decode(text);
            byte[] iv = Arrays.copyOfRange(encrypted, 0, 16);
            byte[] encData = Arrays.copyOfRange(encrypted, 16, encrypted.length);
            IvParameterSpec ivParamSpec = new IvParameterSpec(iv);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, key, ivParamSpec);
            byte[] decrypted = cipher.doFinal(encData);
            return new String(decrypted, StandardCharsets.UTF_8);
        } catch(Exception ex) {
            ErrorSystem.handleError("Could not unlock text.");
            return null;
        }
    }

    /**
     * An internal method used for generated PBKDF2 salts
     * @param password The password to generate the salt with
     * @return The salt
     * @since 1.6
     */
    private static byte[] genSaltFromPassword(String password) {
        StringBuilder sb = new StringBuilder();
        sb.append(password);
        sb.reverse();
        String reversed = sb.toString();
        return reversed.getBytes(StandardCharsets.UTF_8);
    }

}
