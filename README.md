### before spring
brew services start mongodb-community@5.0

### how to run the spring project
./mvnw spring-boot:run

### test postman url
http://localhost:8080/api/product

### test json file
{
  "name": "Iphone 13",
  "description": "iphone 13",
  "price": 2100
}

### stop postman from running
brew services stop mongodb-community@5.0
