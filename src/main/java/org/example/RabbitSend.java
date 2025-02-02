/** Project: Group 6 Team Project
 * Purpose Details: System Integration Using RabbitMQ, JSON, and AES Encryption
 * Course: IST 242
 * Author: Felix Naroditskiy, Eyan Jaffery, Lasha Kaliashvili, Michael Litka, Houde Yu
 * Date Developed: 4/19/2024
 * Last Date Changed: 4/28/2024
 * Rev: 1.1
 */
// Presentation Focus: Houde Yu

package org.example;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

public class RabbitSend {
    /**
     * The name of the RabbitMQ queue to which customer data messages are sent.
     */
    private final static String QUEUE_NAME = "patientDetailsQueue";
    private String customerJson;

    /**
     * Constructs a new RabbitSend instance with the specified Patient JSON string.
     *
     * @param customerJson The JSON string representation of a customer object.
     */
    public RabbitSend(String customerJson) {
        this.customerJson = customerJson;
    }

    /**
     * Sends the Patient JSON string to the RabbitMQ queue.
     *
     * @throws Exception If there is an issue establishing a connection to RabbitMQ,
     *                   creating a channel, declaring a queue, or publishing a message.
     */
    public void sendToQueue() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost"); // Set the host. Adjust as necessary.
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            channel.basicPublish("", QUEUE_NAME, null, customerJson.getBytes());
            System.out.println(" [x] Sent Patient JSON: '" + customerJson + "'");
        }
    }
}
