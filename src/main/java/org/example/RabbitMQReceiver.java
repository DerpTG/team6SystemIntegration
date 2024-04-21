/** Project: Group 6 Team Project
 * Purpose Details: System Integration Using RabbitMQ, JSON, and CaeserCipher Encryption
 * Course: IST 242
 * Author: Felix Naroditskiy, Eyan Jaffery, Lasha Kaliashvili, Michael Litka, Houde Yu
 * Date Developed: 4/19/2024
 * Last Date Changed: 4/20/2024
 * Rev: 1.0
 */

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
