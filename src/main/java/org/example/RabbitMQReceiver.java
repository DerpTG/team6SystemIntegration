package org.example;

public class RabbitMQReceiver {

    // Main method for testing the RabbitMQ message receiver
    public static void main(String[] args) {
        RabbitGet rabbitGet = new RabbitGet();
        try {
            rabbitGet.startReceiving();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
