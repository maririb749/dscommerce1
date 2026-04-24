# Test Plan - DSCommerce API

## 1. Objective
Validate the functionality, security, and business rules of the DSCommerce API.

## 2. Scope

### In Scope
- Product listing and search
- Category listing
- Authentication and authorization
- Order creation and retrieval
- Admin product management

### Out of Scope
- Frontend testing
- Performance testing in this initial phase

## 3. Test Strategy

### Test Types
- Functional Testing
- API Testing
- Security Testing
- Negative Testing
- Boundary and Edge Case Testing
- Business Rules Validation

## 4. Environment

- Environment: Local
- Base URL: http://localhost:8080
- Database: H2 in-memory
- Tool: Postman
- IDE: IntelliJ IDEA

## 5. Test Data

- Client user
- Admin user
- Products and categories preloaded in the database

## 6. Risks and Considerations

- The application uses Spring Boot 2.7.3, which may require dependency review before production use.
- The H2 in-memory database resets after application restart.
- Authentication and authorization must be validated carefully for CLIENT and ADMIN roles.

## 7. Entry Criteria

- API is running successfully.
- H2 database is accessible.
- Test data is loaded.
- Postman environment is configured.

## 8. Exit Criteria

- Critical business flows are tested.
- Main positive, negative, boundary and permission scenarios are executed.
- Bugs found are documented with evidence.
- Test summary report is completed.