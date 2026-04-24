# Test Cases - DSCommerce API

## TC-001 - List products successfully

**Scenario:** TS-001 - Product Catalog - Public Access  
**Priority:** High  
**Type:** Functional / API  
**Precondition:** API is running.

### Steps
1. Send a GET request to `/products`.

### Expected Result
- Status code should be `200 OK`.
- Response should return a paginated list of products.
- Each product should contain basic information such as id, name, description, price and image URL.

### Postman Request
`GET {{base_url}}/products`

---

## TC-002 - Get product by valid ID

**Scenario:** TS-002 - Product Details - Public Access  
**Priority:** High  
**Type:** Functional / API  
**Precondition:** Product with ID `1` exists in the database.

### Steps
1. Send a GET request to `/products/1`.

### Expected Result
- Status code should be `200 OK`.
- Response should return the product details.
- Product ID should be `1`.

### Postman Request
`GET {{base_url}}/products/1`

---

## TC-003 - Get product by invalid ID

**Scenario:** TS-009 - Negative and Boundary Cases  
**Priority:** Medium  
**Type:** Negative / API  
**Precondition:** Product with ID `999999` does not exist.

### Steps
1. Send a GET request to `/products/999999`.

### Expected Result
- Status code should be `404 Not Found`.
- Response should contain an error message.

### Postman Request
`GET {{base_url}}/products/999999`

---

## TC-004 - List categories successfully

**Scenario:** TS-003 - Categories - Public Access  
**Priority:** High  
**Type:** Functional / API  
**Precondition:** API is running.

### Steps
1. Send a GET request to `/categories`.

### Expected Result
- Status code should be `200 OK`.
- Response should return the list of categories.

### Postman Request
`GET {{base_url}}/categories`

---

## TC-005 - Access protected user profile without token

**Scenario:** TS-008 - Authorization and Permissions  
**Priority:** High  
**Type:** Security / Negative  
**Precondition:** No authentication token is provided.

### Steps
1. Send a GET request to `/users/me` without Bearer Token.

### Expected Result
- Status code should be `401 Unauthorized`.

### Postman Request
`GET {{base_url}}/users/me`

---

## 6. Risks and Considerations

- The application uses Spring Boot 2.7.3, which may require dependency review for long-term maintenance, compatibility, and security considerations in production environments.

- The use of an in-memory H2 database causes all data to reset after each application restart, which may impact test reproducibility and persistence-related validations.

- Authentication and authorization flows must be carefully validated to ensure proper access control between CLIENT and ADMIN roles, preventing unauthorized actions.

- API error handling must be validated to ensure consistent responses, proper HTTP status codes, and absence of internal system exposure (e.g., stack traces or sensitive details).

### Steps
1. Authenticate with valid user credentials.
2. Send a GET request to `/users/me` using Bearer Token.

### Expected Result
- Status code should be `200 OK`.
- Response should return authenticated user data.

### Postman Request
`GET {{base_url}}/users/me`

---

## TC-007 - Create product without authentication

**Scenario:** TS-008 - Authorization and Permissions  
**Priority:** High  
**Type:** Security / Negative  
**Precondition:** No authentication token is provided.

### Steps
1. Send a POST request to `/products` without Bearer Token.
2. Provide a valid product payload.

### Expected Result
- Status code should be `401 Unauthorized`.

### Postman Request
`POST {{base_url}}/products`

---

## TC-008 - Create product as non-admin user

**Scenario:** TS-008 - Authorization and Permissions  
**Priority:** High  
**Type:** Security / Negative  
**Precondition:** User is authenticated with CLIENT role.

### Steps
1. Authenticate as CLIENT.
2. Send a POST request to `/products`.

### Expected Result
- Status code should be `403 Forbidden`.

### Postman Request
`POST {{base_url}}/products`

---

## TC-009 - Create product as admin

**Scenario:** TS-007 - Product Management - Admin  
**Priority:** High  
**Type:** Functional / Security  
**Precondition:** User is authenticated with ADMIN role.

### Steps
1. Authenticate as ADMIN.
2. Send a POST request to `/products` with valid product data.

### Expected Result
- Status code should be `201 Created`.
- Product should be created successfully.

### Postman Request
`POST {{base_url}}/products`

---

## TC-010 - Create order with valid authenticated user

**Scenario:** TS-006 - Order Creation  
**Priority:** High  
**Type:** Functional / Business Rule  
**Precondition:** User is authenticated and product exists.

### Steps
1. Authenticate as CLIENT.
2. Send a POST request to `/orders` with valid product and quantity.

### Expected Result
- Status code should be `201 Created`.
- Order should be created successfully.

### Postman Request
`POST {{base_url}}/orders`