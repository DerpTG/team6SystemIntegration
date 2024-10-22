/** Project: Group 6 Team Project
 * Purpose Details: This project focuses on system integration utilizing RabbitMQ for messaging, JSON for data interchange, and AES encryption for securing sensitive information.
 * Course: IST 242
 * Author: Felix Naroditskiy, Eyan Jaffery, Lasha Kaliashvili, Michael Litka, Houde Yu
 * Date Developed: 4/19/2024
 * Last Date Changed: 4/29/2024
 * Rev: 1.1
 */
// Presentation Focus: Lasha

package org.example;

import io.github.cdimascio.dotenv.Dotenv;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
import javax.crypto.Cipher;

public class AES {
    /**
     * Method to retrieve key from the .env file and convert it to SecretKeySpec object
     * @return  Encryption key as a SecretKeySpec object
     */
    public static SecretKeySpec getKeySpec() {
        // Retrieve encryption key from the .env file
        Dotenv dotenv = Dotenv.load();
        String base64Key = dotenv.get("AES_SECRET_KEY");
        // Decode the key
        byte[] decodedKey = Base64.decodeBase64(base64Key);
        return new SecretKeySpec(decodedKey, "AES");
    }

    /**
     * Encrypts a string using AES encryption.
     *
     * @param strToEncrypt The string to be encrypted.
     * @return The encrypted string.
     */
    public static String encrypt(String strToEncrypt) {
        try {
            // Get the encryption key
            SecretKeySpec secretKey = getKeySpec();
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.encodeBase64String(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } catch (Exception e) {
            System.err.println("Error during encryption: " + e.getMessage());
            return null;
        }
    }

    /**
     * Decrypts an AES encrypted string.
     *
     * @param strToDecrypt The string to be decrypted.
     * @return The decrypted string.
     */
    public static String decrypt(String strToDecrypt) {
        try {
            // Get the encryption key
            SecretKeySpec secretKey = getKeySpec();
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.decodeBase64(strToDecrypt)));
        } catch (Exception e) {
            System.err.println("Error during decryption: " + e.getMessage());
            return null;
        }
    }
}
