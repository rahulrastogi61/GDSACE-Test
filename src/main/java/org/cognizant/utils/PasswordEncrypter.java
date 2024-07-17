package org.cognizant.utils;

import java.util.Base64;

public class PasswordEncrypter {
    public static void main(String[] args) {
        String password = "bgPB3Aw3SomeGvtF@lk!";
        String encryptedPassword = encrypt(password);
        System.out.println("Encrypted Password: " + encryptedPassword);
        String decryptedPassword = decrypt(encryptedPassword);
        System.out.println("Decrypted Password: " + decryptedPassword);
    }

    public static String encrypt(String password) {
        String encodedValue = "";
        try {
            byte[] encodedBytes = Base64.getEncoder().encode(password.getBytes("UTF-8"));
            encodedValue = new String(encodedBytes);
        } catch (Exception e) {
            System.out.println("Password was not Encrypted.");
        }
        return encodedValue;
    }

    public static String decrypt(String passwordField) {
        String decodedString = "";
        try {
            byte[] decodedBytes = Base64.getDecoder().decode(passwordField);
            decodedString = new String(decodedBytes);
        } catch (Exception e) {
            System.out.println("Password was nor Decrypted.");
        }
        return decodedString;
    }
}
