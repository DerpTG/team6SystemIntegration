/** Project: Group 6 Team Project
 * Purpose Details: This project focuses on system integration utilizing RabbitMQ for messaging, JSON for data interchange, and AES encryption for securing sensitive information. The main goal is to create a system where user input (customer details) is encrypted, serialized to JSON, sent via RabbitMQ, then deserialized, decrypted, and processed at the receiving end.
 * Course: IST 242
 * Author: Felix Naroditskiy, Eyan Jaffery, Lasha Kaliashvili, Michael Litka, Houde Yu
 * Date Developed: 4/19/2024
 * Last Date Changed: 4/23/2024
 * Rev: 1.1
 */

// This is what the json will look like. We will be using AES converter to encrypt and decrypt the json payload (which will be a customer order).
// Order of Operations: User input --> encryption of details --> json serialization --> sending message via rabbit --> json deserialization ==> decryption of details --> printing details

package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt user for customer details
        System.out.println("Customer Creation:");
        System.out.println("Enter ID of the Customer:");
        String id = scanner.nextLine();
        System.out.println("Enter First Name of the Customer:");
        String first = scanner.nextLine();
        System.out.println("Enter Last Name of the Customer:");
        String last = scanner.nextLine();
        System.out.println("Enter City:");
        String city = scanner.nextLine();
        System.out.println("Enter Customer Email:");
        String email = scanner.nextLine();

        // Create a new customer object
        Customer customer = new Customer(id, first, last, city, email);

        // Check Serialize the customer object to JSON
        String customerJson = customer.toJSON();
        System.out.println("\nCustomer JSON: " + customerJson);
        // Check Deserialize the JSON string to a Customer object
        Customer deserializedCustomer = Customer.fromJSON(customerJson);
        System.out.println("Deserialized Customer:" + deserializedCustomer);
        // Check Encrypt the customer JSON string
        String encryptedCustomerJson = AES.encrypt(customerJson);
        System.out.println("\nEncrypted Customer JSON: " + encryptedCustomerJson);
        // Check Decrypt the encrypted customer JSON string
        String decryptedCustomerJson = AES.decrypt(encryptedCustomerJson);
        System.out.println("Decrypted Customer JSON: " + decryptedCustomerJson);

        // Get Customer toString from deserializedCustomer
        System.out.println("\nCustomer toString: " + deserializedCustomer.toString());

        // Send the encrypted customer JSON string to the RabbitMQ queue
        RabbitSend rabbitSend = new RabbitSend(encryptedCustomerJson);
        try {
            rabbitSend.sendToQueue();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
