# Jokes Demo Application

## Overview
The Jokes Demo Application is a Spring Boot application that fetches random jokes from a public jokes API and stores them in an H2 in-memory database. This application retrieves jokes in batches to avoid hitting rate limits and ensures that no duplicate jokes are stored.

## Features
- Fetches random jokes from the [Official Joke API](https://official-joke-api.appspot.com/random_joke).
- Stores jokes in an H2 in-memory database.
- Avoids duplicate jokes using a unique identifier.
- Logging for fetching and saving processes.
- Swagger UI for API documentation.

## Requirements
- Java 17 or later
- Maven

## Getting Started

### Clone the Repository
```bash
git clone https://github.com/yourusername/jokes-demo.git
cd jokes-demo
```

### Access the Application
Once the application is running, you can access the following endpoints:

- Swagger UI: http://localhost:8888/v0/list-all-api.html

- H2 Database
The application uses H2 as an in-memory database. You can access the H2 console at:

- H2 Console: http://localhost:8888/h2-console