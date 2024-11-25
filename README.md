# Tournament and Member Management System

## Overview

This project provides a RESTful API for managing tournaments and members, including creating, retrieving, and searching for tournaments and members. It uses Spring Boot and JPA for database interaction, along with MySQL as the data store.

---

## Features

### Tournament Management:
- Add new tournaments
- Retrieve tournament details by ID
- List all tournaments
- List members of a specific tournament
- Search tournaments by:
    - Location
    - Start date

### Member Management:
- Add new members
- Retrieve member details by ID
- List all members
- Update a member's membership type
- Search members by:
    - Name
    - Membership type
    - Phone number
    - Participation in tournaments starting on a specific date

---

## Supported APIs

### **Tournament APIs**

#### 1. Add a Tournament
**Endpoint**: `POST /api/tournaments`  
**Description**: Adds a new tournament.  
**Request Body**:
`{
  "startDate": "2024-06-15",
  "endDate": "2024-06-20",
  "location": "Pinewood",
  "entryFee": 50.0,
  "cashPrizeAmount": 500.0
} `

#### 2. Get Tournament by ID
**Endpoint**: `GET /api/tournaments/{id}`
**Description**: `Retrieves the details of a specific tournament.`

#### 3. List All Tournaments
   **Endpoint**: `GET /api/tournaments`
   **Description**: `Retrieves a list of all tournaments.`

#### 4. List Members of a Tournament
   **Endpoint**: `GET /api/tournaments/{id}/members`
   **Description**: `Retrieves the members participating in a specific tournament.`

#### 5. Search Tournaments
   **Endpoint**: `GET /api/tournaments/search`
   **Description**: `Searches tournaments by location, start date, or both.`
   **Parameters**:`location (optional): Partial or full location name.` 
   `startDate (optional): Date in YYYY-MM-DD format.`
   **Example**:`http://localhost:8080/api/tournaments/search?location=Pine&startDate=2024-06-15`

### **Member APIs**
#### 1. Add a Member
   **Endpoint**: `POST /api/members`
   **Description**: `Adds a new member.`
   **Request Body**: `  "id": 3,
   "name": "Janet Doe",
   "address": "323 Main Street",
   "email": "janet.doe@example.com",
   "phoneNumber": "123-456-7888",
   "startDate": "2024-02-01",
   "durationInMonths": 11,
   "membershipType": "Silver"`
#### 2. Get Member by ID
   **Endpoint**: `GET /api/members/{id}`
   **Description**: `Retrieves the details of a specific member.`

#### 3. List All Members
   **Endpoint**: `GET /api/members`
   **Description**: `Retrieves a list of all members.`

#### 4. Update Membership Type
   **Endpoint**: `PUT /api/members/{id}`
   **Description**: `Updates a member's membership type.`
   **Request Body**:`{
   "membershipType": "Platinum"
   }`

#### 5. Search Members
   **Endpoint**: `GET /api/members/search`
   **Description**: `Searches members by name, membership type, phone number, or tournament start date.`
   **Parameters**:`name (optional): Partial or full name.`
`membershipType (optional): Membership type.`
`phoneNumber (optional): Phone number.`
`tournamentStartDate (optional): Tournament start date in YYYY-MM-DD format.`

---

## Running the Project in Docker
### Prerequisites
Ensure Docker and Docker Compose are installed on your machine.
### Steps
#### Clone the Repository:

`git clone <repository-url>`
`cd <repository-directory>`
#### Build the Docker Image: 
If you're using only the Dockerfile, build the image manually:

`docker build -t tournament-management .`

### Run the Application:

#### Option 1: Using docker run directly:

`docker run -p 8080:8080 --name tournament-management-container tournament-management`
#### Option 2: Using docker-compose: 
Ensure your docker-compose.yml file matches the following structure:

- version: '3'
- services:
myapp-main:
image: `golf-club-api:latest`
- ports: - `8080:8080`
- environment:
- `spring.datasource.url=jdbc:mysql://host.docker.internal:3306/golf_club`
- `spring.datasource.username=golf_user`
- `spring.datasource.password=password`

Then run:

`docker-compose up`
#### Verify the Application:

`Access the API at http://localhost:8080/api/tournaments.`