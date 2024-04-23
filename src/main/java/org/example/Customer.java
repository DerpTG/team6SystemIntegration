/** Project: Group 6 Team Project
 * Purpose Details: System Integration Using RabbitMQ, JSON, and AES Encryption
 * Course: IST 242
 * Author: Felix Naroditskiy, Eyan Jaffery, Lasha Kaliashvili, Michael Litka, Houde Yu
 * Date Developed: 4/19/2024
 * Last Date Changed: 4/22/2024
 * Rev: 1.0
 */

package org.example;


import com.google.gson.Gson;

/**
 * Represents a customer entity.
 */
public class Customer {
    private String id;
    private String firstName;
    private String lastName;
    private String city;
    private String email;

    /**
     * Constructs a new Customer object with the specified attributes.
     * @param id The ID of the customer.
     * @param firstName The first name of the customer.
     * @param lastName The last name of the customer.
     * @param city The city of the customer.
     * @param email The email of the customer.
     */
    public Customer(String id, String firstName, String lastName, String city, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.email = email;
    }

    public void displayCustomerDetails() {
        System.out.println("Customer Details:");
        System.out.println("ID: " + id);
        System.out.println("First Name: " + firstName);
        System.out.println("Last Name: " + lastName);
        System.out.println("City: " + city);
        System.out.println("Email: " + email);
    }

    /**
     * Returns a string representation of the customer.
     * @return A string representation of the customer.
     */
    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", city='" + city + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    /**
     * Get Json string of the customer
     * @return Json string of the customer
     */
    public String toJSON(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    /**
     * Get customer object from Json string
     * @param json Json string of the customer
     */
    public static Customer fromJSON(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Customer.class);
    }

}
