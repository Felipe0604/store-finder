# Jumbo Technical Assignment : Store Finder

This application provides the functionality to search a number of store that are closer to a predefined coordinate. Positions are represented by geographic coordinates and the distance is calculated, using the Haversine formula (https://en.wikipedia.org/wiki/Haversine_formula).

## Before to Start

The application requires the following programs:

* OpenJDk 11 (https://openjdk.java.net/)
* Docker (Optional) (https://www.docker.com/)

## Technology Stack

The application requires the following gradle dependencies:

* OpenJDk 11 (https://openjdk.java.net/)
* Spring Boot (https://spring.io/)
* Actuator (https://spring.io/guides/gs/actuator-service/)
* Gradle (https://gradle.org/)
* Lombok (https://projectlombok.org/)
* Mockito (https://site.mockito.org/)

## Build and Run App (Windows)

### - Build 

Run `gradlew.bat build` to build the project.

### - Run Server

Run `gradlew.bat bootRun` to deploy Spring Boot Server. The app will run using the port 8080.

### - Run Test

Run `gradlew.bat test` to execute the unit tests using Mockito and JUnit.

## Build and Run App (Linux or Mac)

### - Build

Run `./gradlew build` to build the project.

### - Run Server

Run `./gradlew bootRun` to deploy Spring Boot Server The app will run using the port 8080.

### - Running unit tests

Run `./gradlew test` to execute the unit tests using Mockito and JUnit.

## Build and Run App (Docker)

### - Build Image

Run `./docker build -t store-finder-image .` to create docker image.

### - Run Container

Run `docker run –name StoreFinderContainer –publish 8080:8080 store-finder-image` to create docker container and run. The app will run using the port 8080.

