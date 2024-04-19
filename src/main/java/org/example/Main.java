/** Project: Lab 4 Systems Integration Pizza Shop
 * Purpose Details: System Integration Using Flat Files, RabbitMQ, and Web Service/JSON
 * Course: IST 242
 * Author: Felix Naroditskiy
 * Date Developed: 4/19/2024
 * Last Date Changed: 4/19/2024
 * Rev: 1.0

 */

// This is what the json will look like. We will be using CaeserCipher converter to encrypt and decrypt the json payload (which will be a customer order).
// Order of Operations: User input --> encryption of details --> json serialization --> sending message via rabbit --> json deserialization ==> decryption of details --> printing details

package org.example;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}