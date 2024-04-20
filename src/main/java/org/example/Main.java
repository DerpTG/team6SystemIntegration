/** Project: Group 6 Team Project
 * Purpose Details: System Integration Using RabbitMQ, JSON, and CaeserCipher Encryption
 * Course: IST 242
 * Author: Felix Naroditskiy
 * Date Developed: 4/19/2024
 * Last Date Changed: 4/19/2024
 * Rev: 1.0

 */

// This is what the json will look like. We will be using CaeserCipher converter to encrypt and decrypt the json payload (which will be a customer order).
// Order of Operations: User input --> encryption of details --> json serialization --> sending message via rabbit --> json deserialization ==> decryption of details --> printing details

package org.example;

public class Main {
    public static void main(String[] args) {
        // Create a new customer object
        Customer customer = new Customer("1", "John", "Doe", "New York", "johndoe@psu.edu");

        // Check Serialize the customer object to JSON
        String customerJson = customer.toJSON();
        System.out.println("\nCustomer JSON: " + customerJson);
        // Check Deserialize the JSON string to a Customer object
        Customer deserializedCustomer = Customer.fromJSON(customerJson);
        System.out.println("Deserialized Customer:" + deserializedCustomer);
        // Check Encrypt the customer JSON string
        String encryptedCustomerJson = CaeserCipherConverter.encrypt(customerJson, 3);
        System.out.println("\nEncrypted Customer JSON: " + encryptedCustomerJson);
        // Check Decrypt the encrypted customer JSON string
        String decryptedCustomerJson = CaeserCipherConverter.decrypt(encryptedCustomerJson, 3);
        System.out.println("Decrypted Customer JSON: " + decryptedCustomerJson);

        // Get Customer toString from deserializedCustomer
        System.out.println("\nCustomer toString: " + deserializedCustomer.toString());

    }
}