# Football Manager API

## Overview
This project is a **Football Manager API**, built with **Spring Boot**, **Hibernate**, and **H2 Database**. It provides RESTful endpoints for managing **football teams and players**, as well as facilitating **player transfers** between teams.

## Features
- CRUD operations for **Teams** and **Players**.
- Player **transfer functionality** with dynamic pricing:
  - Transfer cost = `(Player's experience in months * 100,000) / Player's age in years`
  - Commission (0-10%) applied from the seller's team.
  - The buying team's **balance is reduced**, while the selling team **receives funds**.
- **Validation and error handling** for API requests.
- **Database auto-initialization** using `schema.sql` and `data.sql`.

## Technologies Used
- **Java 21**
- **Spring Boot 3.x**
- **Spring Data JPA (Hibernate)**
- **H2 Database (In-Memory)**
- **Jakarta Validation** (for DTO validation)
- **Lombok**
- **MapStruct** (for entity mapping)

## API Endpoints

### **Players**
| Method | Endpoint | Description |
|--------|---------|-------------|
| `POST` | `/api/players` | Create a new player |
| `GET` | `/api/players` | Get all players |
| `GET` | `/api/players/{id}` | Get player by ID |
| `GET` | `/api/players/name?name={name}` | Get player by name |
| `GET` | `/api/players/team-id?teamId={id}` | Get players by team ID |
| `PATCH` | `/api/players/{id}` | Update a player |
| `DELETE` | `/api/players/{id}` | Delete a player |

### **Teams**
| Method | Endpoint | Description |
|--------|---------|-------------|
| `POST` | `/api/teams` | Create a new team |
| `GET` | `/api/teams` | Get all teams |
| `GET` | `/api/teams/{id}` | Get team by ID |
| `GET` | `/api/teams/name?name={name}` | Get team by name |
| `PATCH` | `/api/teams/{id}` | Update a team |
| `DELETE` | `/api/teams/{id}` | Delete a team |

### **Transfers**
| Method | Endpoint | Description |
|--------|---------|-------------|
| `POST` | `/api/transfers/by-id?playerId={id}&teamId={id}` | Transfer player by ID |
| `POST` | `/api/transfers/by-name?playerName={name}&teamName={name}` | Transfer player by name |

## **Database Setup**
This project uses **H2 Database**, which is an in-memory database. The schema and initial data are automatically created using:
- `src/main/resources/schema.sql`
- `src/main/resources/data.sql`

## **Running the Application**
Run the application with a single command:
```sh
mvn spring-boot:run
```
Alternatively, you can build a JAR and run it:

```sh
mvn clean install
java -jar target/footballmanager-0.0.1-SNAPSHOT.jar
```

## **Postman Collection**
For testing the API, you can use the provided Postman collection: 👉 [Postman Collection](Replace with actual link)

## **Project Structure**
```bash
football-manager/
│-- src/
│   ├── main/
│   │   ├── java/com/testask/footballmanager/
│   │   │   ├── controller/    # API Controllers
│   │   │   ├── exception/     # Custom Exceptions
│   │   │   │   ├── handler/   # Global Exception Handler
│   │   │   ├── mapper/        # MapStruct Mappers
│   │   │   ├── model/         # Entity Models
│   │   │   │   ├── dto/       # DTO Models
│   │   │   ├── repository/    # Database Repositories
│   │   │   ├── service/       # Service Layer
│   │   │   │   ├── impl/      # Service Implementations
│   │   │   ├── FootballManagerApplication.java
│   ├── resources/
│   │   ├── application.yml    # Configuration file
│   │   ├── schema.sql         # Database schema auto-init
│   │   ├── data.sql           # Predefined db data
```
