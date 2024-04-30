# Group 6 Team Project - System Integration

## Table of Contents
- [Project Overview](#project-overview)
- [Technologies Used](#technologies-used)
- [Setup and Installation](#setup-and-installation)
    - [Prerequisites](#prerequisites)
    - [Configuring Environment Variables](#configuring-environment-variables)
    - [Building the Project](#building-the-project)
- [Running the Application](#running-the-application)
- [Usage](#usage)
- [Features](#features)
- [Authors](#authors)
- [Course](#course)
- [License](#license)
- [Acknowledgments](#acknowledgments)

## Project Overview
This project, developed for IST 242, showcases system integration using RabbitMQ for message queuing, JSON for data serialization, and AES encryption for securing data. The system manages and processes patient details through various stages: input, encryption, serialization, queuing, deserialization, decryption, and display.

## Technologies Used
- Java
- RabbitMQ
- JSON (via Gson)
- AES Encryption
- Maven for dependency management

## Setup and Installation

### Prerequisites
- Java JDK 19
- Maven
- RabbitMQ Server

### Configuring Environment Variables
Create a `.env` file in the root directory of the project with the following content:
- AES_SECRET_KEY=8eP43EsblRaTbbEpkMPMpA
  - This key is used for AES encryption and decryption processes.

### Building the Project
Navigate to the project directory and run the following Maven command to build the project:
- mvn clean install


## Running the Application
1. Ensure RabbitMQ server is running and accessible.
2. Start the application by running the `Main` class. This will prompt for patient details and process them through the system.

## Usage
To use this system:
1. **Start the Application:** Run the `Main` class to begin.
2. **Start the Reciever:** Run the `RabbitGet` class and follow on-screen prompts.
2. **Input Patient Details:** Follow the on-screen prompts to enter patient information.
3. **View Processed Output:** After the data goes through the encrypted queuing system, view the output on the console.

## Features
- **Patient Data Handling:** Input, process, and manage patient data securely.
- **AES Encryption/Decryption:** Secure patient data using AES encryption before sending over the network.
- **RabbitMQ Integration:** Use RabbitMQ for message queuing between different stages of the system.
- **JSON Serialization/Deserialization:** Convert patient objects to/from JSON format for easy transmission and storage.

## Authors
- Felix Naroditskiy
- Eyan Jaffery
- Lasha Kaliashvili
- Michael Litka
- Houde Yu

## Course
- IST 242

## License
- This project is licensed under the MIT License - see the LICENSE file for details.

## Acknowledgments
- IST 242 faculty (Dr. Joe Oakes)
- All libraries and technologies used
