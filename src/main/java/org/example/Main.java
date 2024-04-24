/**
 * Project: Group 6 Team Project
 * Purpose Details: System Integration Using RabbitMQ, JSON, and AES Encryption
 * Course: IST 242
 * Author: Felix Naroditskiy, Eyan Jaffery, Lasha Kaliashvili, Michael Litka, Houde Yu
 * Date Developed: 4/19/2024
 * Last Date Changed: 4/22/2024
 * Rev: 1.0
 */

package org.example;

import java.util.Scanner;

public class Main {

    /**
     * The main method is the entry point of the application.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Customer Creation:");
        System.out.println("Enter ID of the Customer:");
        String id = scanner.nextLine(); // ID of the customer
        System.out.println("Enter Full Name of the Customer:");
        String fullName = scanner.nextLine(); // Full name of the customer
        System.out.println("Enter Date of Birth of the Customer:");
        String dateOfBirth = scanner.nextLine(); // Date of birth of the customer
        System.out.println("Enter Phone Number of the Customer:");
        String phoneNumber = scanner.nextLine(); // Phone number of the customer
        System.out.println("Enter Email Address of the Customer:");
        String emailAddress = scanner.nextLine(); // Email address of the customer
        System.out.println("Enter Existing Medical Conditions of the Customer:");
        String existingMedicalConditions = scanner.nextLine(); // Existing medical conditions of the customer
        System.out.println("Enter Allergies of the Customer:");
        String allergies = scanner.nextLine(); // Allergies of the customer
        System.out.println("Enter Health Issues or Symptoms of the Customer:");
        String healthIssuesOrSymptoms = scanner.nextLine(); // Health issues or symptoms of the customer
        System.out.println("Enter Insurance Provider of the Customer:");
        String insuranceProvider = scanner.nextLine(); // Insurance provider of the customer
        System.out.println("Enter Primary Care Physician of the Customer:");
        String primaryCarePhysician = scanner.nextLine(); // Primary care physician of the customer

        // Create a new customer object
        Customer customer = new Customer(id, fullName, dateOfBirth, phoneNumber, emailAddress,
                existingMedicalConditions, allergies, healthIssuesOrSymptoms, insuranceProvider, primaryCarePhysician);

        // Serialize the customer object to JSON
        String customerJson = customer.toJSON();
        System.out.println("\nCustomer JSON: " + customerJson);
        // Deserialize the JSON string to a Customer object
        Customer deserializedCustomer = Customer.fromJSON(customerJson);
        System.out.println("Deserialized Customer:" + deserializedCustomer);
        // Encrypt the customer JSON string
        String encryptedCustomerJson = AES.encrypt(customerJson);
        System.out.println("\nEncrypted Customer JSON: " + encryptedCustomerJson);
        // Decrypt the encrypted customer JSON string
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
