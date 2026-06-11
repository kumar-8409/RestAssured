# REST Assured Framework - API Automation

A comprehensive REST API automation framework using **Java** and **REST Assured** library. This framework demonstrates best practices for API testing with proper project structure, configuration management, and reusable utilities.

## 📋 Project Structure

```
RestAssured/
├── pom.xml                          # Maven configuration
├── testng.xml                       # TestNG test suite configuration
└── src/test/java/com/restassured/
    ├── config/
    │   └── APIConfig.java           # API configuration (base URL, endpoints)
    ├── base/
    │   └── BaseTest.java            # Base test class with setup
    ├── utils/
    │   └── APIUtils.java            # Reusable API utility methods
    └── tests/
        ├── PostsAPITest.java        # Tests for Posts API
        ├── UsersAPITest.java        # Tests for Users API
        └── CommentsAPITest.java     # Tests for Comments API
```

## 🚀 Getting Started

### Prerequisites
- Java 11 or higher
- Maven 3.6 or higher
- Git

### Installation

1. **Clone the repository:**
```bash
git clone <repository-url>
cd RestAssured
```

2. **Install dependencies:**
```bash
mvn clean install
```

## 🧪 Test Execution

### Run all tests:
```bash
mvn test
```

### Run specific test class:
```bash
mvn test -Dtest=PostsAPITest
```

### Run specific test method:
```bash
mvn test -Dtest=PostsAPITest#testGetAllPosts
```

### Run with TestNG:
```bash
mvn test -DsuiteXmlFile=testng.xml
```

## 📦 Dependencies

| Dependency | Version | Purpose |
|-----------|---------|---------|
| REST Assured | 5.3.1 | API automation testing |
| TestNG | 7.8.1 | Test framework |
| JSON Path | 5.3.1 | JSON response parsing |
| Hamcrest | 2.2 | Assertion matchers |
| GSON | 2.10.1 | JSON processing |
| SLF4J | 2.0.7 | Logging |

## 🔧 Framework Architecture

### 1. **APIConfig Class** (Configuration Management)
- Centralized configuration for base URL and endpoints
- Timeout and retry settings
- Easy to switch between environments

### 2. **APIUtils Class** (Reusable Methods)
Provides wrapper methods for common operations:
- `getRequest()` - GET requests
- `getRequestWithPathParam()` - GET with path parameters
- `getRequestWithQueryParam()` - GET with query parameters
- `postRequest()` - POST requests
- `putRequest()` - PUT requests
- `deleteRequest()` - DELETE requests

### 3. **BaseTest Class** (Test Setup)
- Common setup for all test classes
- Base URI configuration
- Logger initialization

### 4. **Test Classes** (Test Implementation)
- **PostsAPITest**: CRUD operations on posts
- **UsersAPITest**: User data and validation
- **CommentsAPITest**: Comment management

## 📝 Test Cases

### Posts API Tests (7 tests)
✅ Get all posts
✅ Get post by ID
✅ Get posts by user ID
✅ Create new post
✅ Update post
✅ Delete post
✅ Handle invalid ID

### Users API Tests (7 tests)
✅ Get all users
✅ Get user by ID
✅ Validate email format
✅ Validate address structure
✅ Validate company information
✅ Create new user
✅ Validate response headers

### Comments API Tests (7 tests)
✅ Get all comments
✅ Get comment by ID
✅ Get comments by post ID
✅ Validate email format
✅ Create new comment
✅ Update comment
✅ Delete comment

## 🧬 API Used: JSONPlaceholder

This framework uses **JSONPlaceholder**, a free fake online REST API for testing and prototyping.

- **Base URL**: https://jsonplaceholder.typicode.com
- **Resources**: Posts, Users, Comments, Albums, Photos, Todos
- **Documentation**: https://jsonplaceholder.typicode.com

## 📊 Test Assertions

The framework uses **Hamcrest matchers** for powerful assertions:

```java
response.then()
    .statusCode(200)
    .body("size()", greaterThan(0))
    .body("[0]", hasKey("id"))
    .body("email", matchesPattern("^[A-Za-z0-9+_.-]+@(.+)$"))
    .body("name", notNullValue())
    .header("Content-Type", containsString("application/json"));
```

## 🔍 Key Features

✅ **Modular Design**: Easy to maintain and extend
✅ **Reusable Utilities**: DRY principle applied
✅ **Comprehensive Logging**: SLF4J integration for detailed logs
✅ **Proper Configuration**: Centralized API configuration
✅ **CRUD Operations**: Examples of Create, Read, Update, Delete operations
✅ **Validation**: Data validation and response structure checks
✅ **Parallel Execution**: TestNG parallel test execution support

## 📈 Adding New Tests

To add new API tests:

1. Create a new test class extending `BaseTest`
2. Use `APIUtils` methods for API calls
3. Use Hamcrest matchers for assertions
4. Add test method with `@Test` annotation
5. Register in `testng.xml`

**Example:**
```java
public class NewAPITest extends BaseTest {
    
    @Test(description = "Test description")
    public void testNewEndpoint() {
        Response response = APIUtils.getRequest("/new-endpoint");
        
        response.then()
            .statusCode(200)
            .body("field", equalTo("value"));
    }
}
```

## 🛠️ Troubleshooting

### Issue: Tests fail with connection error
**Solution**: Ensure internet connection and JSONPlaceholder API is accessible

### Issue: JSON parsing errors
**Solution**: Check JSON response format and update path expressions

### Issue: Port conflicts
**Solution**: Change timeout values in APIConfig if needed

## 📚 Best Practices Implemented

1. **Separation of Concerns**: Config, Utils, Tests clearly separated
2. **DRY Principle**: Reusable utility methods
3. **Logging**: Comprehensive logging for debugging
4. **Naming Conventions**: Clear, descriptive method names
5. **Configuration Management**: Centralized configuration
6. **Test Organization**: Grouped by resource (Posts, Users, Comments)

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Run tests to ensure nothing breaks
5. Submit a pull request

## 📄 License

This project is open source and available under the MIT License.

## 👨‍💻 Author

Created as a comprehensive REST Assured framework example for API automation testing.

---

## Quick Reference

```bash
# Clean and install dependencies
mvn clean install

# Run all tests
mvn test

# Generate test report
mvn clean test site

# Run with specific configurations
mvn test -Dtest=PostsAPITest -DsuiteXmlFile=testng.xml
```

For more details, refer to individual test files and utility classes.
