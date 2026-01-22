# Securin - Recipe Data Collection and API Development

Securin Recipe API & Dashboard
Submitted by, Sarguru N
Intern role : Security Analyst 
Mail id: 814722104141@trp.srmtrichy.edu.in

Project Overview
A Full-Stack Secure API built for the Securin Inc Labs assessment. This project parses a JSON dataset of recipes, stores them in an in-memory H2 database, and exposes them via a RESTful API with advanced filtering and security best practices.

Tech Stack:

Backend: Java Spring Boot (v3.5.9)
Database: H2 In-Memory Database (SQL)
Frontend: HTML5, Bootstrap 5, Vanilla JavaScript
Security: Spring Data JPA (SQL Injection Prevention), Strict Type Validation

Key Features:

Secure Data Parsing: Automated seeding from US_recipes_null.json.
Search & Filtering: Dynamic Specification logic for searching by Title, Cuisine, and Rating.
Performance: Implemented Pagination (Pageable) to prevent DoS via data overload.
Architecture: REST Controller -> Service Layer -> JPA Repository -> Database.

How to Run:

Clone the repository.
Open the folder in IntelliJ or VS Code.
Run RecipeApiApplication.java.
Open Recipe_Frontend/index.html in any browser.
Access H2 Console at http://localhost:8080/h2-console (JDBC URL: jdbc:h2:mem:recipedb).
