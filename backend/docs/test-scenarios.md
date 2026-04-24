# Test Scenarios - DSCommerce API

## Business Rules Covered

- Public users can view products and categories.
- Authenticated clients can create and view their own orders.
- Admin users can create, update and delete products.
- Non-admin users cannot manage products.
- Protected resources require a valid access token.

## TS-001 - Product Catalog - Public Access

### Scenario
Validate that any user can access the product catalog without authentication.

### Expected Result
The API should return the product list successfully.

---

## TS-002 - Product Details - Public Access

### Scenario
Validate that any user can view product details by product ID.

### Expected Result
The API should return the selected product details.

---

## TS-003 - Categories - Public Access

### Scenario
Validate that any user can list product categories.

### Expected Result
The API should return all available categories.

---

## TS-004 - Authentication

### Scenario
Validate that a registered user can authenticate using valid credentials.

### Expected Result
The API should return a valid access token.

---

## TS-005 - User Profile

### Scenario
Validate that an authenticated user can access their own profile.

### Expected Result
The API should return the authenticated user data.

---

## TS-006 - Order Creation

### Scenario
Validate that an authenticated client can create an order with valid products and quantities.

### Expected Result
The API should create the order successfully.

---

## TS-007 - Product Management - Admin

### Scenario
Validate that an admin user can create, update and delete products.

### Expected Result
The API should allow product management operations.

---

## TS-008 - Authorization and Permissions

### Scenario
Validate that users without ADMIN role cannot manage products.

### Expected Result
The API should deny access with a proper authorization error.

---

## TS-009 - Negative and Boundary Cases

### Scenario
Validate invalid, missing, or boundary input values.

### Expected Result
The API should return proper validation or error responses.