/**
 * Project: Group 6 Team Project
 * Purpose Details: System Integration Using RabbitMQ, JSON, and AES Encryption
 * Course: IST 242
 * Author: Felix Naroditskiy, Eyan Jaffery, Lasha Kaliashvili, Michael Litka, Houde Yu
 * Date Developed: 4/19/2024
 * Last Date Changed: 4/28/2024
 * Rev: 1.1
 */
// Presentation Focus: Felix

package org.example;
import java.util.Scanner;
public class Main {
    // Scanner object to read input from the console
    private static Scanner scanner = new Scanner(System.in);

    // Declare the variables to store the patient details
    private static String id, fullName, dateOfBirth, phoneNumber, emailAddress, existingMedicalConditions, allergies, healthIssuesOrSymptoms, insuranceProvider, primaryCarePhysician;

    // Variable to store the patient object
    private static Patient patient;

    /**
     * Input form for entering a single patient's data.
     */
    private static void inputForm(){
        System.out.println("Patient Form:"); // Patient form
        System.out.print("Enter ID of the Patient:");
        id = scanner.nextLine(); // ID of the patient
        System.out.print("Enter Full Name of the Patient:");
        fullName = scanner.nextLine(); // Full name of the patient
        System.out.print("Enter Date of Birth of the Patient:");
        dateOfBirth = scanner.nextLine(); // Date of birth of the patient
        System.out.print("Enter Phone Number of the Patient:");
        phoneNumber = scanner.nextLine(); // Phone number of the patient
        System.out.print("Enter Email Address of the Patient:");
        emailAddress = scanner.nextLine(); // Email address of the patient
        System.out.print("Enter Existing Medical Conditions of the Patient:");
        existingMedicalConditions = scanner.nextLine(); // Existing medical conditions of the patient
        System.out.print("Enter Allergies of the Patient:");
        allergies = scanner.nextLine(); // Allergies of the patient
        System.out.print("Enter Health Issues or Symptoms of the Patient:");
        healthIssuesOrSymptoms = scanner.nextLine(); // Health issues or symptoms of the patient
        System.out.print("Enter Insurance Provider of the Patient:");
        insuranceProvider = scanner.nextLine(); // Insurance provider of the patient
        System.out.print("Enter Primary Care Physician of the Patient:");
        primaryCarePhysician = scanner.nextLine(); // Primary care physician of the patient

        // Create the patient object using the entered data
        patient = new Patient(id, fullName, dateOfBirth, phoneNumber, emailAddress,
                existingMedicalConditions, allergies, healthIssuesOrSymptoms, insuranceProvider, primaryCarePhysician);
    }

    /**
     * The main method is the entry point of the application.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        // Welcome message
        System.out.println("Welcome to the Patient Information System!");
        // While loop to keep the application running
        while (true) {
            // Display the menu
            System.out.println("\nMenu:");
            System.out.println("1. Enter Patient Information");
            System.out.println("2. Exit");
            System.out.print("Enter your choice: ");
            // Read the user's choice
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
            // Switch statement to handle the user's choice
            switch (choice) {
                case 1:
                    // Call the inputForm method to input the patient details
                    inputForm();
                    // Serialize the patient object to JSON
                    String customerJson = patient.toJSON();
                    System.out.println("\nPatient JSON: " + customerJson);
                    // Encrypt the patient JSON string
                    String encryptedCustomerJson = AES.encrypt(customerJson);
                    // Send the encrypted patient JSON string to the RabbitMQ queue
                    RabbitSend rabbitSend = new RabbitSend(encryptedCustomerJson);
                    try {
                        rabbitSend.sendToQueue();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    // Exit the application
                    System.out.println("Thank you for using the Patient Information System!");
                    System.exit(0);
                    break;
                default:
                    // Display an error message for an invalid choice
                    System.out.println("Error: Invalid choice. Please try again.");
            }
        }
    }
}