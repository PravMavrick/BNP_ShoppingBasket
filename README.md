# BNP_ShoppingBasket
Calculate discounted price of Shopping basket


## Project Run
1. Pull the code from below repo
   https://github.com/PravMavrick/DevelopementBooks.git into IDE
2. Reload/update project with Maven And run the application.
3. All endpoints exposed to Swagger UI, you can access it from below links.
   1. http://localhost:8080/v3/api-docs
   2. http://localhost:8080/swagger-ui/index.html
4. Please use PostMan or any API client application for testing the application.

## Project description
1. Application is using H2 in memory database.
   H2 database access link
   http://localhost:8080/h2-console
2. Application has below endpoints
   1. Create all books -  http://localhost:8080/api/createAllBooks
      POST call with below Json body --> 
      [ {
      "bookName": "Clean Code (Robert Martin, 2008)",
      "bookPrice": 50.0
      },
      {
      "bookName": "The Clean Coder (Robert Martin, 2011)",
      "bookPrice": 50.0
      },
      {
      "bookName": "Clean Architecture (Robert Martin, 2017)",
      "bookPrice": 50.0
      },
      {
      "bookName": "Test Driven Development by Example (Kent Beck, 2003)",
      "bookPrice": 50.0
      },
      {
      "bookName": "Working Effectively With Legacy Code (Michael C. Feathers, 2004)",
      "bookPrice": 50.0
      }
      ]
   2. Create all discount rules - http://localhost:8080/api/createDiscountRules
      POST call with below Json Body --> [
      {
      "discDescription": "One book no discount",
      "discPercentage": 0.0
      },
      {
      "discDescription": "Two different books",
      "discPercentage": 5.0
      },
      {
      "discDescription": "Three different books",
      "discPercentage": 10.0
      },
      {
      "discDescription": "Four different books",
      "discPercentage": 20.0
      },
      {
      "discDescription": "Five different books",
      "discPercentage": 25.0
      }
      ]
   3. Get Shopping basket price for given books - http://localhost:8080/api/getPrice
      POST call with below Json Body -->
      [
      {
        "bookId" : 1,
        "bookName": "Clean Code (Robert Martin, 2008)",
        "bookQuantity": 2
      },
      {
        "bookId" : 2,
        "bookName": "The Clean Coder (Robert Martin, 2011)",
        "bookQuantity": 2
      },
      {
        "bookId" : 3,
        "bookName": "Clean Architecture (Robert Martin, 2017)",
        "bookQuantity": 2
      },
      {
        "bookId" : 4,
        "bookName": "Test Driven Development by Example (Kent Beck, 2003)",
        "bookQuantity": 1
      },
      {
        "bookId" : 5,
        "bookName": "Working Effectively With Legacy Code (Michael C. Feathers, 2004)",
        "bookQuantity": 1
      }
     ]
### Possible Exceptions
  1. If Books are not registered then you will get below exceptions.
     "Books are not registered into application."
  2. Book Quantity should not be negative and Total quantity in shopping cart should not be zero.

## Tests
  1. If Discount rule is not registered into database then total price will be calculated as Total quantity * 50.
  2. If Discount rules are registered and Books are available then you will get expected value of shopping cart price with discount.

## Note:
  1. Please check the console logs for better understanding of algorithm written to find minimum discounted price for given shopping basket items.
