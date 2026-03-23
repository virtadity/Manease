*Manease*\
A Spring Boot-based RESTful service for managing purchases and generating aggregated supplier reports.

🚀 Key Features
* RESTful API: Full CRUD for Purchases and Producers using Spring HATEOAS (HAL format).
* Reporting: Aggregated data processing to generate detailed reports (totals by producer/item).
* Database Migrations: Version-controlled schema using Liquibase.
* Containerization: Ready-to-deploy with Docker and Docker Compose.

🛠 Tech Stack:
- Java 17 & Spring Boot 3.x
- Spring Data JPA (PostgreSQL)
- Spring HATEOAS (Hypermedia-driven API)
- Testcontainers (Integration testing with real DB)
- Gradle (Build tool)
- Mockito (Mocking Library)
- MockMvc (Mocking Library for testing responses and requests)
- Docker (Containerization)

🐳 Quick Start (Docker)
To build and run the entire infrastructure (App + PostgreSQL): 

```bash
docker-compose up --build
```

The API will be available at http://localhost:8080.

🧪 Testing
Integration tests are implemented using Testcontainers to ensure environment consistency.
To run tests locally:
```
./gradlew test
```

📝 Implementation Details
* Domain-Driven Approach: Business logic is separated from infrastructure and web layers. 
* Optimized Reporting: Aggregation logic is handled via JPA/Hibernate to minimize memory footprint. 
* Resilient Infrastructure: Docker Compose uses healthcheck to ensure the application starts only after the database is ready. 
* HATEOAS Integration: Discoverable API with _links and _embedded collections for better frontend integration.
