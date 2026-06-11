# REST Assured Framework - Quick Start Guide

## 🎯 Overview
A complete REST API automation framework using **Java 11**, **Maven**, **REST Assured**, and **TestNG**. The framework automates testing of the **JSONPlaceholder API** with comprehensive test cases for Posts, Users, and Comments endpoints.

## ✅ What's Included

### 21 Automated Test Cases
- **7 Posts API Tests**: Get all, Get by ID, Get by User, Create, Update, Delete, Invalid ID handling
- **7 Users API Tests**: Get all, Get by ID, Email validation, Address structure, Company info, Create, Headers validation
- **7 Comments API Tests**: Get all, Get by ID, Get by Post ID, Email validation, Create, Update, Delete

### Framework Components

1. **Configuration Management** (`APIConfig.java`)
   - Centralized API configuration
   - Base URL: https://jsonplaceholder.typicode.com
   - Reusable endpoints and timeout settings

2. **Utility Layer** (`APIUtils.java`)
   - Wrapper methods for all HTTP operations
   - GET, POST, PUT, DELETE with parameters
   - Built-in logging and request/response handling

3. **Base Test Class** (`BaseTest.java`)
   - Common setup for all tests
   - Base URI initialization
   - Logger configuration

4. **Test Classes**
   - `PostsAPITest.java` - Tests for /posts endpoint
   - `UsersAPITest.java` - Tests for /users endpoint
   - `CommentsAPITest.java` - Tests for /comments endpoint

## 🚀 Getting Started

### Prerequisites
- Java 11+
- Maven 3.6+
- Internet connection (for JSONPlaceholder API)

### Installation
```bash
cd d:\RestAssured
mvn clean install
```

### Run All Tests
```bash
mvn test
```

### Expected Output
```
Tests run: 21, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

## 📊 Project Structure
```
d:\RestAssured/
├── pom.xml                                  # Maven configuration
├── testng.xml                               # TestNG configuration
├── README.md                                # Full documentation
├── QUICKSTART.md                            # This file
└── src/test/java/com/restassured/
    ├── config/
    │   └── APIConfig.java                   # API configuration
    ├── base/
    │   └── BaseTest.java                    # Base test setup
    ├── utils/
    │   └── APIUtils.java                    # Reusable methods
    └── tests/
        ├── PostsAPITest.java                # Posts tests (7 tests)
        ├── UsersAPITest.java                # Users tests (7 tests)
        └── CommentsAPITest.java             # Comments tests (7 tests)
```

## 🧪 Test Execution Examples

### Run specific test class
```bash
mvn test -Dtest=PostsAPITest
```

### Run specific test method
```bash
mvn test -Dtest=PostsAPITest#testGetAllPosts
```

### Run with detailed output
```bash
mvn test -X
```

## 📝 Key Features

✅ **CRUD Operations** - Complete Create, Read, Update, Delete examples
✅ **Parameter Handling** - Path parameters and query parameters
✅ **Validation** - Comprehensive assertions using Hamcrest matchers
✅ **Logging** - SLF4J integration for detailed logs
✅ **Reusable Utilities** - DRY principle with utility methods
✅ **Clean Code** - Well-organized, modular architecture
✅ **Parallel Execution** - TestNG supports parallel test runs
✅ **JSON Validation** - JSON path expressions for response validation

## 🔧 Dependencies

| Dependency | Version | Purpose |
|-----------|---------|---------|
| REST Assured | 5.3.1 | API testing library |
| TestNG | 7.7.0 | Test framework |
| Hamcrest | 2.2 | Assertion matchers |
| GSON | 2.10.1 | JSON processing |
| SLF4J | 2.0.7 | Logging |

## 💡 Example Test

```java
@Test(description = "Test GET all posts")
public void testGetAllPosts() {
    logger.info("Test: Getting all posts");
    Response response = APIUtils.getRequest(APIConfig.POSTS_ENDPOINT);

    response.then()
            .statusCode(200)
            .body("size()", greaterThan(0))
            .body("[0]", hasKey("userId"))
            .body("[0]", hasKey("id"));

    logger.info("✓ All posts retrieved successfully");
}
```

## 🎓 How to Extend

### Add New Test
1. Create a new test class extending `BaseTest`
2. Use `APIUtils` methods for API calls
3. Add assertions using Hamcrest matchers
4. Mark method with `@Test` annotation

### Add New Endpoint
1. Update `APIConfig.java` with new endpoint
2. Create new test class for that endpoint
3. Implement test methods using existing utilities

## 📚 API Documentation
- **Base URL**: https://jsonplaceholder.typicode.com
- **Resources**: Posts, Users, Comments, Albums, Photos, Todos
- **Full Docs**: https://jsonplaceholder.typicode.com

## 🐛 Troubleshooting

**Issue**: Connection errors
**Solution**: Check internet connection and API availability

**Issue**: Maven build fails
**Solution**: Run `mvn clean install -U` to update dependencies

**Issue**: Tests timeout
**Solution**: Increase timeout in APIConfig.java

## ✨ Test Results Summary

**Framework Status**: ✅ **ACTIVE & WORKING**

**Test Execution**: 
- Total Tests: 21
- Passed: 21 ✅
- Failed: 0 ✅
- Skipped: 0
- Success Rate: 100% ✅

**Endpoints Covered**:
- Posts API ✅
- Users API ✅
- Comments API ✅

**Operations Tested**:
- GET all resources ✅
- GET specific resource ✅
- GET with filters ✅
- POST (Create) ✅
- PUT (Update) ✅
- DELETE ✅
- Data validation ✅
- Error handling ✅

## 📞 Support

For detailed information, refer to:
- `README.md` - Full documentation
- Individual test classes - See test implementations
- `APIConfig.java` - Configuration settings

---

**Ready to use!** Start testing your APIs with REST Assured! 🚀
