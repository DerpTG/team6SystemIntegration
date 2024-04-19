/** Project: Lab 4 Systems Integration Pizza Shop
 * Purpose Details: System Integration Using Flat Files, RabbitMQ, and Web Service/JSON
 * Course: IST 242
 * Author: Felix Naroditskiy
 * Date Developed: 2/21/2024
 * Last Date Changed: 3/4/2024
 * Rev: 1.0

 */

package org.example;

import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class RabbitGet {

    /**
     * @param QUEUE_NAME The name of the RabbitMQ queue from which pizza data messages are received.
     * @param gson Gson instance for converting JSON strings into Pizza objects.
     * @param Gson instance for converting JSON strings into Pizza objects.
     * @param channel Channel for communicating with the RabbitMQ server.
     */
    private final static String QUEUE_NAME = "pizzaQueue";
    private final Gson gson = new Gson();
    private Connection connection;
    private Channel channel;

    /**
     * Initializes and starts listening for messages on the specified queue. Upon receiving a message,
     * it deserializes the JSON into a Pizza object and displays its details.
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
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            // Deserialize the JSON string to a Pizza object
            Pizza pizza = gson.fromJson(message, Pizza.class);
            // Use the displayPizzaDetails method to print pizza details
            pizza.displayPizzaDetails();
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
}

