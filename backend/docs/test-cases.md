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