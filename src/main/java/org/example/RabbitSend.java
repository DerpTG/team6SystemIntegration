/** Project: Lab 4 Systems Integration Pizza Shop
 * Purpose Details: System Integration Using Flat Files, RabbitMQ, and Web Service/JSON
 * Course: IST 242
 * Author: Felix Naroditskiy
 * Date Developed: 2/21/2024
 * Last Date Changed: 3/4/2024
 * Rev: 1.0

 */

package org.example;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

public class RabbitSend {

    /**
     * @param QUEUE_NAME The name of the RabbitMQ queue to which pizza data messages are sent.
     * @param pizzaJson The JSON string representation of a pizza object to be sent to the queue.
     */
    private final static String QUEUE_NAME = "pizzaQueue";
    private String pizzaJson;

    /**
     * Constructs a new PizzaRabbitSend instance with the specified pizza JSON string.
     *
     * @param pizzaJson The JSON string representation of a pizza object.
     */
    public RabbitSend(String pizzaJson) {
        this.pizzaJson = pizzaJson;
    }

    /**
     * Sends the pizza JSON string to the RabbitMQ queue.
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
            channel.basicPublish("", QUEUE_NAME, null, pizzaJson.getBytes());
            System.out.println(" [x] Sent Pizza JSON: '" + pizzaJson + "'");
        }
    }

    /**
     * Returns the current pizza JSON string.
     *
     * @return The current pizza JSON string.
     */
    public String getPizzaJson() {return pizzaJson;}

    /**
     * Updates the pizza JSON string.
     *
     * @param pizzaJson The new JSON string representation of a pizza object.
     */
    public void setPizzaJson(String pizzaJson) {this.pizzaJson = pizzaJson;}
}

