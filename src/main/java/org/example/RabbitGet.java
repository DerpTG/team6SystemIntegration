/** Project: Group 6 Team Project
 * Purpose Details: This project focuses on system integration utilizing RabbitMQ for messaging, JSON for data interchange, and AES encryption for securing sensitive information.
 * Course: IST 242
 * Author: Felix Naroditskiy, Eyan Jaffery, Lasha Kaliashvili, Michael Litka, Houde Yu
 * Date Developed: 4/19/2024
 * Last Date Changed: 4/28/2024
 * Rev: 1.1
 */

package org.example;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class RabbitGet {

    /**
     * The name of the RabbitMQ queue from which customer data messages are received.
     */
    private final static String QUEUE_NAME = "customerDetailsQueue";
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
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        // Open the connection and create a channel
        connection = factory.newConnection();
        channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println("Patient Details ... Waiting for Messages ...");

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
     * Main method to start receiving messages from RabbitMQ.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        RabbitGet rabbitGet = new RabbitGet();
        try {
            rabbitGet.startReceiving();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
