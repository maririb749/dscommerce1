# Test Cases - DSCommerce API

## 01 - Smoke Tests

---

## TC-001 - List products successfully

**Scenario:** TS-001 - Product Catalog - Public Access  
**Priority:** High  
**Type:** Smoke / Functional / API  
**Precondition:** API is running and product data is available.

### Steps
1. Send a GET request to `/products`.

### Expected Result
- Status code should be `200 OK`.
- Response should return a paginated list of products.
- Each product summary should contain `id`, `name`, `price`, and `imgUrl`.
- Response should not be empty.

### Postman Request
`GET {{base_url}}/products`

### Validations
- HTTP status code
- JSON content type
- Response time
- Paginated response structure
- Non-empty product list
- Required product summary fields
- Product summary field data types

---

## TC-002 - List categories successfully

**Scenario:** TS-003 - Categories - Public Access  
**Priority:** High  
**Type:** Smoke / Functional / API  
**Precondition:** API is running and category data is available.

### Steps
1. Send a GET request to `/categories`.

### Expected Result
- Status code should be `200 OK`.
- Response should return a list of categories.
- Each category should contain `id` and `name`.

### Postman Request
`GET {{base_url}}/categories`

### Validations
- HTTP status code
- JSON content type
- Response time
- Array response structure
- Non-empty category list
- Required category fields
- Category field data types

---

## TC-003 - Get product details successfully

**Scenario:** TS-002 - Product Details - Public Access  
**Priority:** High  
**Type:** Smoke / Functional / API  
**Precondition:** Product with ID `1` exists in the database.

### Steps
1. Send a GET request to `/products/1`.

### Expected Result
- Status code should be `200 OK`.
- Response should return product details.
- Product ID should be `1`.
- Product detail should contain `id`, `name`, `description`, `price`, `imgUrl`, and categories when available.

### Postman Request
`GET {{base_url}}/products/1`

### Validations
- HTTP status code
- JSON content type
- Response time
- Product detail contract
- Product ID
- Required product detail fields
- Product detail field data types

---

## 02 - Product Catalog - Public Access

---

## TC-004 - Search products by name

**Scenario:** TS-001 - Product Catalog - Public Access  
**Priority:** High  
**Type:** Functional / API  
**Precondition:** API is running and product data is available.

### Steps
1. Send a GET request to `/products?name=Macbook`.

### Expected Result
- Status code should be `200 OK`.
- Response should return a paginated product list.
- Returned products should match the searched name.
- Each product should contain the expected summary fields.

### Postman Request
`GET {{base_url}}/products?name=Macbook`

### Validations
- HTTP status code
- JSON content type
- Response time
- Paginated response structure
- Search result relevance
- Required product summary fields
- Product summary field data types

---

## TC-005 - List products with pagination

**Scenario:** TS-001 - Product Catalog - Public Access  
**Priority:** High  
**Type:** Functional / API  
**Precondition:** API is running and product data is available.

### Steps
1. Send a GET request to `/products?page=0&size=5`.

### Expected Result
- Status code should be `200 OK`.
- Response should return page `0`.
- Response should use page size `5`.
- Returned products should not exceed the requested page size.

### Postman Request
`GET {{base_url}}/products?page=0&size=5`

### Validations
- HTTP status code
- JSON content type
- Response time
- Paginated response structure
- Requested page number
- Requested page size
- Maximum number of returned products
- Required product summary fields
- Product summary field data types

---

## TC-006 - Get product by invalid ID

**Scenario:** TS-009 - Negative and Boundary Cases  
**Priority:** Medium  
**Type:** Negative / API  
**Precondition:** Product with ID `999999` does not exist.

### Steps
1. Send a GET request to `/products/999999`.

### Expected Result
- Status code should be `404 Not Found`.
- Response should return a controlled error response.
- Error response should contain `timestamp`, `status`, `error`, and `path`.
- Response should not expose internal implementation details.
- Response should not return a product success payload.

### Postman Request
`GET {{base_url}}/products/999999`

### Validations
- HTTP status code
- JSON content type
- Response time
- Error response contract
- Error status
- Error message through the `error` field
- Requested path
- Absence of product success payload
- Absence of internal leakage

---

## 03 - Categories - Public Access

---

## TC-007 - List categories with full contract validation

**Scenario:** TS-003 - Categories - Public Access  
**Priority:** High  
**Type:** Functional / API  
**Precondition:** API is running and category data is available.

### Steps
1. Send a GET request to `/categories`.

### Expected Result
- Status code should be `200 OK`.
- Response should return a list of categories.
- Each category should contain `id` and `name`.
- Category IDs should be positive numbers.
- Category names should not be empty.

### Postman Request
`GET {{base_url}}/categories`

### Validations
- HTTP status code
- JSON content type
- Response time
- Array response structure
- Non-empty category list
- Required category fields
- Category field data types
- Positive category IDs
- Non-empty category names

---

## 04 - Authentication

---

## TC-008 - Authenticate client successfully

**Scenario:** TS-004 - Authentication  
**Priority:** High  
**Type:** Functional / Security / API  
**Precondition:** API is running and client user exists in the database.

### Steps
1. Send a POST request to `/oauth/token`.
2. Use Basic Auth with valid client credentials.
3. Send valid client user credentials using `x-www-form-urlencoded`.

### Expected Result
- Status code should be `200 OK`.
- Response should return a valid `access_token`.
- Token type should be `bearer`.
- Token expiration should be present and valid.
- JWT should follow the expected `header.payload.signature` format.
- Response should not expose user password, client secret, or internal implementation details.
- Access token should be stored in the Postman environment as `client_token`.

### Postman Request
`POST {{base_url}}/oauth/token`

### Authentication
- Type: Basic Auth
- Username: `myclientid`
- Password: `myclientsecret`

### Body
`x-www-form-urlencoded`

| Key | Value |
|---|---|
| grant_type | password |
| username | maria@gmail.com |
| password | 123456 |

### Validations
- HTTP status code
- JSON content type
- Response time
- Authentication response contract
- Access token presence
- Token type
- Expiration value
- JWT format
- Absence of credentials or internal sensitive data
- Token stored in environment as `client_token`

---

---

## TC-009 - Authenticate admin successfully

**Scenario:** TS-004 - Authentication  
**Priority:** High  
**Type:** Functional / Security / API  
**Precondition:** API is running and admin user exists in the database.

### Steps
1. Send a POST request to `/oauth/token`.
2. Use Basic Auth with valid client credentials.
3. Send valid admin user credentials using `x-www-form-urlencoded`.

### Expected Result
- Status code should be `200 OK`.
- Response should return a valid `access_token`.
- Token type should be `bearer`.
- Token expiration should be present and valid.
- JWT should follow the expected `header.payload.signature` format.
- JWT should belong to `alex@gmail.com`.
- JWT should contain `ROLE_ADMIN`.
- Response should not expose user password, client secret, or internal implementation details.
- Access token should be stored in the Postman environment as `admin_token`.

### Postman Request
`POST {{base_url}}/oauth/token`

### Authentication
- Type: Basic Auth
- Username: `myclientid`
- Password: `myclientsecret`

### Body
`x-www-form-urlencoded`

| Key | Value |
|---|---|
| grant_type | password |
| username | alex@gmail.com |
| password | 123456 |

### Validations
- HTTP status code
- JSON content type
- Response time
- Authentication response contract
- Access token presence
- Token type
- Expiration value
- JWT format
- JWT authenticated username
- JWT admin authority
- Absence of credentials or internal sensitive data
- Token stored in environment as `admin_token`

---

## TC-010 - Reject authentication with invalid password

**Scenario:** TS-004 - Authentication  
**Priority:** High  
**Type:** Negative / Security / API  
**Precondition:** API is running and client user exists in the database.

### Steps
1. Send a POST request to `/oauth/token`.
2. Use Basic Auth with valid client credentials.
3. Send a valid username with an invalid password using `x-www-form-urlencoded`.

### Expected Result
- Status code should be `400 Bad Request`.
- Response should return a controlled authentication error.
- Error should indicate invalid credentials.
- Response should not return an `access_token`.
- Response should not return token metadata.
- Response should not expose user password, client secret, or internal implementation details.

### Postman Request
`POST {{base_url}}/oauth/token`

### Authentication
- Type: Basic Auth
- Username: `myclientid`
- Password: `myclientsecret`

### Body
`x-www-form-urlencoded`

| Key | Value |
|---|---|
| grant_type | password |
| username | maria@gmail.com |
| password | wrong_password |

### Validations
- HTTP status code
- JSON content type
- Response time
- Error response contract
- Error message
- Absence of access token
- Absence of token metadata
- Absence of credentials or internal sensitive data

---

## TC-011 - Reject authentication with invalid user

**Scenario:** TS-004 - Authentication  
**Priority:** High  
**Type:** Negative / Security / API  
**Precondition:** API is running.

### Steps
1. Send a POST request to `/oauth/token`.
2. Use Basic Auth with valid client credentials.
3. Send a non-existing username with a valid password using `x-www-form-urlencoded`.

### Expected Result
- Status code should be `400 Bad Request`.
- Response should return a controlled authentication error.
- Error should indicate invalid credentials.
- Response should not return an `access_token`.
- Response should not return token metadata.
- Response should not expose user password, client secret, or internal implementation details.

### Postman Request
`POST {{base_url}}/oauth/token`

### Authentication
- Type: Basic Auth
- Username: `myclientid`
- Password: `myclientsecret`

### Body
`x-www-form-urlencoded`

| Key | Value |
|---|---|
| grant_type | password |
| username | nonexistent.user@gmail.com |
| password | 123456 |

### Validations
- HTTP status code
- JSON content type
- Response time
- Error response contract
- Error message
- Absence of access token
- Absence of token metadata
- Absence of credentials or internal sensitive data

---

## TC-012 - Reject authentication with invalid client credentials

**Scenario:** TS-004 - Authentication  
**Priority:** High  
**Type:** Negative / Security / API  
**Precondition:** API is running and user credentials are valid.

### Steps
1. Send a POST request to `/oauth/token`.
2. Use invalid Basic Auth client credentials.
3. Send valid user credentials using `x-www-form-urlencoded`.

### Expected Result
- Status code should be `401 Unauthorized`.
- Response should return a controlled authorization error.
- Error response should contain `timestamp`, `status`, `error`, and `path`.
- Response should not return an `access_token`.
- Response should not return token metadata.
- Response should not expose user password, client secret, or internal implementation details.

### Postman Request
`POST {{base_url}}/oauth/token`

### Authentication
- Type: Basic Auth
- Username: `invalidclientid`
- Password: `invalidclientsecret`

### Body
`x-www-form-urlencoded`

| Key | Value |
|---|---|
| grant_type | password |
| username | maria@gmail.com |
| password | 123456 |

### Validations
- HTTP status code
- JSON content type
- Response time
- Error response contract
- Error status
- Error value
- Error path
- Absence of access token
- Absence of token metadata
- Absence of credentials or internal sensitive data