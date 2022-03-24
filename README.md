# Jumbo Technical Assignment : Store Finder


## Table of Contents
- [Main Features](#main-features)
- [Requirements](#requirements)
- [Technology Stack](#technology-stack)
- [Build and Run App](#build-and-run)
- [Rest API](#rest-api)
- [Pending Improvements](#pending-improvements)
- [Creator](#creator)


## Main Features

This application provides the functionality to search a number of stores that are closer to a predefined coordinate. Positions are represented by geographic coordinates and the distance is calculated, using the Haversine formula (https://en.wikipedia.org/wiki/Haversine_formula). 

Both coordinates and the number of stores can be chosen in the API Rest

## Requirements

To run the app, the following programs are required:

* OpenJDk 11 (https://openjdk.java.net/)
* Docker (Optional) (https://www.docker.com/)

## Technology Stack

Following dependency needs to be downloaded by the app:

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

## Rest API

### Finder

Get the closest store from a point defined by latitude and longitude. The number of store by default is 5.

- Request 

`GET http://localhost:8080/api/v1/store/finder/longitude/5.1/latitude/52?stores=1`

`GET http://localhost:8080/api/v1/store/finder?longitude=5.1&latitude=52&stores=1`

- Response
```
    HTTP/1.1 200 OK
    Status: 200 OK
    Connection: close
    Content-Type: application/json

    [
        {
            "city": "Vianen",
            "postalCode": "4132 CB",
            "street": "Lijnbaan",
            "street2": "7",
            "street3": "",
            "addressName": "Jumbo Vianen Gerrit van Bruchem",
            "uuid": "DUcKYx4XBNsAAAFIHd4YwKxJ",
            "longitude": 5.094176,
            "latitude": 51.991481,
            "complexNumber": 33008,
            "showWarningMessage": true,
            "todayOpen": "08:00",
            "locationType": "SupermarktPuP",
            "collectionPoint": true,
            "sapStoreID": 3412,
            "todayClose": "20:00"
        }
    ]
```
### Store

Get stores by filter or all. The filter values should be exact, with only 4 decimal of error to compare values.

- Request

`GET http://localhost:8080/api/v1/store/longitude/5.094176/latitude/51.991481`

`GET http://localhost:8080/api/v1/store?longitude=5.094176&latitude=51.991481`

- Response (Ok) 
```
    HTTP/1.1 200 OK
    Status: 200 OK
    Connection: close
    Content-Type: application/json

    [
        {
            "city": "Vianen",
            "postalCode": "4132 CB",
            "street": "Lijnbaan",
            "street2": "7",
            "street3": "",
            "addressName": "Jumbo Vianen Gerrit van Bruchem",
            "uuid": "DUcKYx4XBNsAAAFIHd4YwKxJ",
            "longitude": 5.094176,
            "latitude": 51.991481,
            "complexNumber": 33008,
            "showWarningMessage": true,
            "todayOpen": "08:00",
            "locationType": "SupermarktPuP",
            "collectionPoint": true,
            "sapStoreID": 3412,
            "todayClose": "20:00"
        }
    ]
```

- Response (Not Found)
```
    HTTP/1.1 404 OK
    Status: 404 OK
    Connection: close
    Content-Type: application/json

    [
        {
            "city": "Vianen",
            "postalCode": "4132 CB",
            "street": "Lijnbaan",
            "street2": "7",
            "street3": "",
            "addressName": "Jumbo Vianen Gerrit van Bruchem",
            "uuid": "DUcKYx4XBNsAAAFIHd4YwKxJ",
            "longitude": 5.094176,
            "latitude": 51.991481,
            "complexNumber": 33008,
            "showWarningMessage": true,
            "todayOpen": "08:00",
            "locationType": "SupermarktPuP",
            "collectionPoint": true,
            "sapStoreID": 3412,
            "todayClose": "20:00"
        }
    ]
```

### Actuator

Get app health info.

- Request

`GET http://localhost:8080/actuator/health`

- Response
```
    HTTP/1.1 200 OK
    Status: 200 OK
    Connection: keep-alive
    Content-Type: application/vnd.spring-boot.actuator.v3+json

    {
        "status": "UP"
    }
```

## Pending Improvements

Some features were not implemented, but should be a priority requirement in the future.

- Security: Implement token authentication for API Rest.
- Repository: It is required to define a database or repository for the Store.
- Swagger: A robust document for APIs, including exceptions definition.

## Creator

Felipe Gonzalez Foncea

- https://www.linkedin.com/in/felipegonzalezfoncea/