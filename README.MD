# GreenShadow API

GreenShadow API is a RESTful backend system for managing crops, fields, equipment, vehicles, staff, and logs. Built with Spring Boot, and supports JWT authentication for secure access to resources.

## Features
- **Secure**:
    - Implements robust login and signup functionality with JWT tokens.
    - Offers role-based access control, accommodating roles such as ADMIN, MANAGER, SCIENTIST, and OTHER.

- **Resource Management**:
    - CRUD operations for crops, fields, equipment, vehicles, staff, and logs.

- **Seamless Integration with Spring Boot**:
    - Leverages Spring Boot features like dependency injection and seamless integration with Spring Security for authentication and authorization.

## Technologies Used
- **Framework**: Spring Boot (Java)
- **Security**: Spring Security with JWT
- **Database**: MySQL (configurable)
- **Build Tool**: Gradle

## Setup and Installation Guide

### Ensure you have the following installed:
1. Java 21 or later.
2. Gradle build tool.
3. MySQL database.

### Steps to Run the Project
1. Clone the repository:
   ```bash  
   https://github.com/DasunNirmal/Green-Shadow-BackEnd.git 
   ```

2. Update database configuration in `application-dev.properties`:
   ```properties  
    server.port=your port
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.datasource.url=jdbc:mysql://localhost:3306/greenShadow?createDatabaseIfNotExist=true
    spring.datasource.username=your user name
    spring.datasource.password=your mysql password  

    spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    spring.jpa.properties.hibernate.format_sql=true
   ```  

3. Build and run the project:
   ```bash  
   ./gradlew build  
   ./gradlew bootRun
   ```  

4. Access the API at:
    - Base URL: `http://localhost:8081/greenShadow/api/v1/`

## Security and Access Control
GreenShadow API employs Spring Security to ensure secure access to resources. JWT tokens, issued upon successful authentication, are mandatory for accessing protected endpoints.

### User Roles and Permissions
- **ADMIN**: Full access to all endpoints.
- **MANAGER**: Restricted access to certain CRUD operations.
- **SCIENTIST/OTHER**: Limited read/write permissions.
---
