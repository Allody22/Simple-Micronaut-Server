## Simple micronaut server

This project is a simple Micronaut-based server that provides functionality related to a gas station, including managing users and purchases (such as fuel, food, and services).

* Project Structure
* Language: Kotlin 
* Framework: Micronaut 4.4.2 
* Database: PostgreSQL (using Docker for database setup)
* ORM: Micronaut Data
* Containerization: Docker, Docker Compose 
* Important libraries: micronaut-security:4.5.0, reactor-core:3.5.0, jackson-module-kotlin

### Features User Management:
1) Users can be created and stored in the PostgreSQL database. 
2) Each user has a username and password. 
3) The username field is unique.

### Purchases:
1) Users can make purchases at a gas station. 
2) Purchases can be related to various categories like fuel, food (e.g., cafe), and services. 
3) Each purchase has details such as the product name, price, date, and user who made the purchase. 
4) Categories are stored as an enum (ECategory) and include: GASOLINE, CAFE, SERVICES, UNKNOWN_CATEGORY 
5) If the server receives a category that is not found in the database, it will automatically create a category with the "UNKNOWN_CATEGORY" type.