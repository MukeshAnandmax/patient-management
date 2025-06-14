# Patient Management System

A comprehensive microservices-based patient management system with authentication, user management, patient records, and analytics capabilities.

## 🚀 Features

- **User Authentication & Authorization**
  - JWT-based authentication
  - Role-based access control
  - Secure password management

- **Patient Management**
  - Create and manage patient records
  - Track patient history
  - Search and filter patients

- **Billing & Invoicing**
  - Generate and manage invoices
  - Track payments
  - Financial reporting

- **Analytics Dashboard**
  - Patient statistics
  - Revenue reports
  - System usage metrics

## 🛠️ Tech Stack

- **Backend**: Java 17, Spring Boot, Spring Cloud
- **Database**: PostgreSQL, MongoDB
- **API Gateway**: Spring Cloud Gateway
- **Service Discovery**: Eureka
- **Authentication**: JWT, Spring Security
- **Containerization**: Docker
- **Build Tool**: Maven

## 📦 Prerequisites

- Java 17 or higher
- Maven 3.6.3 or higher
- Docker and Docker Compose
- PostgreSQL 13+
- MongoDB 5.0+

## 🚀 Getting Started

1. **Clone the repository**
   ```bash
   git clone [repository-url]
   cd patient-management
   ```

2. **Start the infrastructure**
   ```bash
   docker-compose up -d
   ```

3. **Build and run services**
   ```bash
   # Build all services
   mvn clean install
   
   # Run discovery service first
   cd discovery-service
   mvn spring-boot:run
   
   # Then run other services in separate terminals
   # api-gateway, auth-service, user-service, etc.
   ```

## 🌐 API Documentation

Once services are running, access the API documentation:
- Swagger UI: http://localhost:8080/swagger-ui.html
- API Gateway: http://localhost:8765

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## 📧 Contact

For any questions or support, please contact the development team.
