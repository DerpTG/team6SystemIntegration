/** Project: Group 6 Team Project
 * Purpose Details: This project focuses on system integration utilizing RabbitMQ for messaging, JSON for data interchange, and AES encryption for securing sensitive information.
 * Course: IST 242
 * Author: Felix Naroditskiy, Eyan Jaffery, Lasha Kaliashvili, Michael Litka, Houde Yu
 * Date Developed: 4/19/2024
 * Last Date Changed: 4/27/2024
 * Rev: 1.0
 */

package org.example;
import com.google.gson.Gson;

/**
 * Represents a customer entity.
 */
public class Patient {
    private String id;
    private String fullName;
    private String dateOfBirth;
    private String phoneNumber;
    private String emailAddress;
    private String existingMedicalConditions;
    private String allergies;
    private String healthIssuesOrSymptoms;
    private String insuranceProvider;
    private String primaryCarePhysician;

    /**
     * Constructs a new Patient object with the specified attributes.
     * @param id The ID of the customer.
     * @param fullName The full name of the customer.
     * @param dateOfBirth The date of birth of the customer.
     * @param phoneNumber The phone number of the customer.
     * @param emailAddress The email address of the customer.
     * @param existingMedicalConditions Any existing medical conditions of the customer.
     * @param allergies Any allergies of the customer.
     * @param healthIssuesOrSymptoms Any health issues or symptoms of the customer.
     * @param insuranceProvider The insurance provider of the customer.
     * @param primaryCarePhysician The primary care physician of the customer.
     */
    public Patient(String id, String fullName, String dateOfBirth, String phoneNumber, String emailAddress,
                   String existingMedicalConditions, String allergies, String healthIssuesOrSymptoms,
                   String insuranceProvider, String primaryCarePhysician) {
        this.id = id;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.existingMedicalConditions = existingMedicalConditions;
        this.allergies = allergies;
        this.healthIssuesOrSymptoms = healthIssuesOrSymptoms;
        this.insuranceProvider = insuranceProvider;
        this.primaryCarePhysician = primaryCarePhysician;
    }
    /**
     * Display Patient details.
     */
    public void displayPatientDetails() {
        System.out.println("Patient Details:");
        System.out.println("ID: " + id);
        System.out.println("Full Name: " + fullName);
        System.out.println("Date of Birth: " + dateOfBirth);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("Email Address: " + emailAddress);
        System.out.println("Existing Medical Conditions: " + existingMedicalConditions);
        System.out.println("Allergies: " + allergies);
        System.out.println("Health Issues or Symptoms: " + healthIssuesOrSymptoms);
        System.out.println("Insurance Provider: " + insuranceProvider);
        System.out.println("Primary Care Physician: " + primaryCarePhysician);
    }
    /**
     * Returns a string representation of the customer.
     * @return A string representation of the customer.
     */
    @Override
    public String toString() {
        return "Patient{" +
                "id='" + id + '\'' +
                ", fullName='" + fullName + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", existingMedicalConditions='" + existingMedicalConditions + '\'' +
                ", allergies='" + allergies + '\'' +
                ", healthIssuesOrSymptoms='" + healthIssuesOrSymptoms + '\'' +
                ", insuranceProvider='" + insuranceProvider + '\'' +
                ", primaryCarePhysician='" + primaryCarePhysician + '\'' +
                '}';
    }
    /**
     * Converts the customer object to a JSON string.
     * @return JSON string representing the customer object.
     */
    public String toJSON(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }
    /**
     * Constructs a customer object from a JSON string.
     * @param json JSON string representing the customer object.
     * @return Patient object constructed from the JSON string.
     */
    public static Patient fromJSON(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Patient.class);
    }
}