# Smart Library Book Generator

**Smart Library Book Generator** is a **Spring Boot microservice** that uses the **Smart Library REST API** to generate and manage books.  
It provides endpoints for creating, retrieving, and managing book data dynamically.

## Features
- Built with **Spring Boot** and **Java 17+**
- Integration with **Smart Library REST API**
- RESTful endpoints for book generation and management
- Lightweight and easy to deploy as a microservice

## Getting Started

### Prerequisites
- Java 17+
- Maven
- Running instance of **Smart Library API**

### Build and Run
1. Clone the repository:
- git clone https://github.com/ikraseninnikov/smart-library-book-generator.git
- cd smart-library-book-generator

### Build the project:
- mvn clean install
- Run the microservice:
mvn spring-boot:run


### Plans / Future Improvements

- Export generated books to JSON or CSV
- Add more book attributes (genre, number of pages)
- Improve API with filtering and pagination