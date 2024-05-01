/** Project: Group 6 Team Project
 * Purpose Details: This project focuses on system integration utilizing RabbitMQ for messaging, JSON for data interchange, and AES encryption for securing sensitive information.
 * Course: IST 242
 * Author: Felix Naroditskiy, Eyan Jaffery, Lasha Kaliashvili, Michael Litka, Houde Yu
 * Date Developed: 4/19/2024
 * Last Date Changed: 4/28/2024
 * Rev: 1.1
 */
// Presentation Focus: Eyan

package org.example;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import java.util.Scanner;

public class RabbitGet {
    private static Scanner scanner = new Scanner(System.in);
    /**
     * The name of the RabbitMQ queue from which customer data messages are received.
     */
    private final static String QUEUE_NAME = "patientDetailsQueue";
    private Connection connection;
    private Channel channel;
    /**
     * Initializes and starts listening for messages on the specified queue. Upon receiving a message,
     * it deserializes the JSON into a Patient object and displays its details.
     *
     * @throws Exception If there is an issue establishing a connection to RabbitMQ, creating a channel,
     * or declaring a queue.
     */
    public void startReceiving() throws Exception {
        ConnectionFactory factory = new ConnectionFactory(); // Create a new ConnectionFactory object
        factory.setHost("localhost"); // Set the host.
        // Open the connection and create a channel
        connection = factory.newConnection();
        channel = connection.createChannel();
        // Declare the queue
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println("Patient Details ... Waiting for Messages ...");
        // Callback to handle incoming messages
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String encryptedMessage = new String(delivery.getBody(), "UTF-8");
            // Decrypt the message using the AES
            String decryptedMessage = AES.decrypt(encryptedMessage);
            // Deserialize the JSON string to Patient object
            Patient patient = Patient.fromJSON(decryptedMessage);
            // Use the displayCustomerDetails method to print Patient details
            patient.displayPatientDetails();
        };
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
    }
    /**
     * Closes the channel and the connection to RabbitMQ when no longer needed, releasing resources.
     *
     * @throws Exception If there is an issue closing the channel or connection.
     */
    public void stopReceiving() throws Exception {
        if (channel != null && channel.isOpen()) {
            channel.close();
        }
        if (connection != null && connection.isOpen()) {
            connection.close();
        }
    }
    /**
     * Displays the menu and reads the user's choice.
     *
     * @return The user's choice.
     */
    private static int menu() {
        System.out.println("Welcome to the Patient Information Receiving System!");
        while (true) {
            // Display the menu
            System.out.println("Menu: ");
            System.out.println("1. Start Receiving Messages");
            System.out.println("2. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 1 || choice == 2) {
                return choice;
            } else {
                System.out.println("Error: Invalid choice. Please try again.");
            }
        }
    }

    /**
     * The main method is the entry point of the application.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        int choice = menu(); // Display the menu and read the user's choice
        RabbitGet rabbitGet = new RabbitGet(); // Create a new RabbitGet object
        // Switch statement to handle the user's choice
        try {
            switch (choice) {
                case 1:
                    // Start receiving messages
                    rabbitGet.startReceiving();
                    System.out.println("Enter 2 to stop receiving messages."); // Prompt the user to stop receiving messages
                    while (true) {
                        int stopChoice = scanner.nextInt(); // Read the user's choice
                        scanner.nextLine(); // Consume the newline character
                        // If the user chooses to stop receiving messages, call the stopReceiving method and exit the application
                        if (stopChoice == 2) {
                            System.out.println("Stopping the Receiver Application...");
                            rabbitGet.stopReceiving(); // Stop receiving messages
                            scanner.close(); // Close the scanner
                            System.exit(0); // Exit the application
                            break;
                        } else {
                            System.out.println("Error: Invalid choice. Please try again.");
                        }
                    }
                    break;
                case 2:
                    System.out.println("Exiting the application."); // Exit the application
                    scanner.close(); // Close the scanner
                    System.exit(0);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}