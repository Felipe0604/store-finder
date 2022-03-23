# Jumbo Technical Assignment : Store Finder


## Table of Contents
- [Main Features](#main-features)
- [Requirements](#requirements)
- [Technology Stack](#technology-stack)
- [Build and Run App](#build-and-run)
- [Usage](#usage)


## Main Features

This application provides the functionality to search a number of store that are closer to a predefined coordinate. Positions are represented by geographic coordinates and the distance is calculated, using the Haversine formula (https://en.wikipedia.org/wiki/Haversine_formula).


## Requirements

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

## Build and Run

### Window

- Build the project.
```
gradlew.bat build
```

- Deploy Spring Boot Server. The app will run using the port 8080
```
gradlew.bat bootRun
```

- Run unit tests using Mockito and JUnit
```
gradlew.bat test
```

### Linux or Mac

- Build the project.
```
./gradlew build build
```

- Deploy Spring Boot Server. The app will run using the port 8080
```
./gradlew bootRun
```

- Run unit tests using Mockito and JUnit
```
./gradlew test
```

### Docker

- Create docker image
```
docker build -t store-finder-image .
```

- Create docker container and run. The app will run using the port 8080
```
docker run --name StoreFinderContainer --publish 8080:8080 store-finder-image
```

## Usage
