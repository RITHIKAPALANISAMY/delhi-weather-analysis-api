# Delhi Weather Data Processing & Analysis API  
### Assessment – 2  
Developed by Rithika P

---

## 1. Project Description

This project is developed as part of Assessment – 2.  

The objective of this project is to process nearly 20 years of Delhi weather forecast data from a CSV file, store it in a structured format inside MongoDB, and build REST APIs to retrieve and analyze weather details efficiently.

The system allows users to:

- Retrieve weather details for a specific date
- Retrieve weather details for a specific month
- Extract minimum, maximum, and median temperatures
- Get monthly temperature statistics for an entire year

The project is built using Spring Boot and MongoDB with a clean modular architecture.

---

## 2. Technologies Used

- Java 17
- Spring Boot
- Spring Data MongoDB
- MongoDB
- Maven
- Eclipse IDE

---

## 3. Data Processing and Storage

### CSV Processing

- The provided CSV file containing Delhi weather data is placed inside the `resources` folder.
- On application startup, the CSV file is automatically read using a custom `CsvLoader` component.
- Each row is transformed into a structured `Weather` Java object.
- Date values are parsed and converted properly into `LocalDate` format.
- Missing numeric values are handled safely to avoid runtime errors.
- Duplicate loading is prevented by checking existing records before insertion.

### Data Storage

- The processed data is stored in MongoDB.
- Database Name: `weatherdb`
- Collection Name: `weather`
- MongoDB runs on `localhost:27017`

The data is stored in a structured format for efficient querying and analysis.

---

## 4. Project Architecture (Modular Design)

The project follows a layered architecture:

### Model Layer
Contains the `Weather` entity class representing the MongoDB document.

### Repository Layer
Handles database interaction using Spring Data MongoDB.

### Service Layer
Contains business logic such as:
- Filtering by date
- Filtering by month
- Calculating min, max, and median temperature
- Generating yearly statistics

### Controller Layer
Exposes REST API endpoints for client interaction.

### DTO Layer
Used for returning structured statistics responses.

### Utility Layer
Handles CSV reading and data transformation.

This modular approach ensures clean separation of concerns and maintainable code.

---

## 5. API Endpoints

Base URL:

http://localhost:8080/api/weather

---

### 1. Get All Weather Records

Returns all stored weather records.

Endpoint:
GET /api/weather

---

### 2. Get Weather Details by Exact Date

Returns weather condition, temperature, humidity, and pressure for a specific date.

Endpoint:
GET /api/weather/by-date?date=YYYY-MM-DD

Example:
GET /api/weather/by-date?date=1996-11-01

---

### 3. Get Weather Details by Month

Returns all weather records for a selected month of a given year.

Endpoint:
GET /api/weather/by-month?year=YYYY&month=MM

Example:
GET /api/weather/by-month?year=1996&month=11

---

### 4. Get Monthly Temperature Statistics

Returns minimum, median, and maximum temperature for a selected month.

Endpoint:
GET /api/weather/monthly-stats?year=YYYY&month=MM

Example Response:

{
  "min": 15.0,
  "median": 21.0,
  "max": 31.0
}

---

### 5. Get Yearly Monthly Temperature Statistics

Returns high (max), median, and minimum temperature details for each month of a given year.

Endpoint:
GET /api/weather/yearly-stats?year=YYYY

Example Response:

[
  {
    "month": 11,
    "maxTemp": 31.0,
    "minTemp": 15.0,
    "medianTemp": 21.0
  }
]

---

## 6. How to Run the Project

1. Start MongoDB server.
2. Open the project in Eclipse.
3. Run `WeatherServiceApplication`.
4. Open browser or Postman.
5. Test the API endpoints using the URLs mentioned above.

---

## 7. Assessment Requirements Coverage

This project satisfies all given requirements:

✔ CSV file transformed into structured format  
✔ Data stored efficiently in MongoDB  
✔ Modular architecture implementation  
✔ API for filtering by date and month  
✔ Extraction of high, median, and minimum temperature  
✔ Monthly statistics for any selected year  

---

## 8. Conclusion

This project demonstrates how large historical weather data can be processed, stored, and analyzed efficiently using Spring Boot and MongoDB.

The application provides flexible and well-structured REST APIs to retrieve weather details and generate temperature statistics over a 20-year dataset.

---

Developed by  
Rithika P
