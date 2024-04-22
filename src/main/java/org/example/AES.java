package org.example;

import io.github.cdimascio.dotenv.Dotenv;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
import javax.crypto.Cipher;

public class AES {
    // Method to convert Base64 string key to SecretKeySpec
    public static SecretKeySpec getKeySpec() {
        // Retrieve encryption key from the .env file
        Dotenv dotenv = Dotenv.load();
        String base64Key = dotenv.get("AES_SECRET_KEY");
        // Decode the key
        byte[] decodedKey = Base64.decodeBase64(base64Key);
        return new SecretKeySpec(decodedKey, "AES");
    }

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
