# Coding Challenge
## What's Provided
A simple [Spring Boot](https://projects.spring.io/spring-boot/) web application has been created and bootstrapped 
with data. The application contains information about all employees at a company. On application start-up, an in-memory 
Mongo database is bootstrapped with a serialized snapshot of the database. While the application runs, the data may be
accessed and mutated in the database without impacting the snapshot.

### How to Run
The application may be executed by running `gradle bootRun`.

### How to Use
The following endpoints are available to use:
```
* CREATE
    * HTTP Method: POST 
    * URL: localhost:8080/employee
    * PAYLOAD: Employee
    * RESPONSE: Employee
* READ
    * HTTP Method: GET 
    * URL: localhost:8080/employee/{id}
    * RESPONSE: Employee
* UPDATE
    * HTTP Method: PUT 
    * URL: localhost:8080/employee/{id}
    * PAYLOAD: Employee
    * RESPONSE: Employee
```
The Employee has a JSON schema of:
```json
{
  "type":"Employee",
  "properties": {
    "employeeId": {
      "type": "string"
    },
    "firstName": {
      "type": "string"
    },
    "lastName": {
          "type": "string"
    },
    "position": {
          "type": "string"
    },
    "department": {
          "type": "string"
    },
    "directReports": {
      "type": "array",
      "items" : "string"
    }
  }
}
```
For all endpoints that require an "id" in the URL, this is the "employeeId" field.

## What to Implement
Clone or download the repository, do not fork it.

### Task 1
Create a new type, ReportingStructure, that has two properties: employee and numberOfReports.

For the field "numberOfReports", this should equal the total number of reports under a given employee. The number of 
reports is determined to be the number of directReports for an employee and all of their distinct reports. For example, 
given the following employee structure:
```
                    John Lennon
                /               \
         Paul McCartney         Ringo Starr
                               /        \
                          Pete Best     George Harrison
```
The numberOfReports for employee John Lennon (employeeId: 16a596ae-edd3-4847-99fe-c4518e82c86f) would be equal to 4. 

This new type should have a new REST endpoint created for it. This new endpoint should accept an employeeId and return 
the fully filled out ReportingStructure for the specified employeeId. The values should be computed on the fly and will 
not be persisted.

### Task 2
Create a new type, Compensation. A Compensation has the following fields: employee, salary, and effectiveDate. Create 
two new Compensation REST endpoints. One to create and one to read by employeeId. These should persist and query the 
Compensation from the persistence layer.

## API Documentation

https://documenter.getpostman.com/view/11044408/Szf6WTd9?version=latest#ad85db45-c74f-4f1a-b9c7-8553e09dd3cb

### Solution for Task 1
```
* READ
    * HTTP Method: GET 
    * URL: localhost:8080/reportingStructure/{id}
    * RESPONSE: ReportingStructure
```    
endpoint sample : http://localhost:8080/reportingStructure/16a596ae-edd3-4847-99fe-c4518e82c86f

Output JSON : 
```json
{
  "employee": {
    "employeeId": "16a596ae-edd3-4847-99fe-c4518e82c86f",
    "firstName": "John",
    "lastName": "Lennon",
    "position": "Development Manager",
    "department": "Engineering",
    "directReports": [
      {
        "employeeId": "b7839309-3348-463b-a7e3-5de1c168beb3",
        "firstName": "Paul",
        "lastName": "McCartney",
        "position": "Developer I",
        "department": "Engineering",
        "directReports": null
      },
      {
        "employeeId": "03aa1462-ffa9-4978-901b-7c001562cf6f",
        "firstName": "Ringo",
        "lastName": "Starr",
        "position": "Developer V",
        "department": "Engineering",
        "directReports": [
          {
            "employeeId": "62c1084e-6e34-4630-93fd-9153afb65309",
            "firstName": "Pete",
            "lastName": "Best",
            "position": "Developer II",
            "department": "Engineering",
            "directReports": null
          },
          {
            "employeeId": "c0c2293d-16bd-4603-8e08-638a9d18b22c",
            "firstName": "George",
            "lastName": "Harrison",
            "position": "Developer III",
            "department": "Engineering",
            "directReports": null
          }
        ]
      }
    ]
  },
  "numberOfReports": 4
}
```


### Solution for Task 2
```
* CREATE
    * HTTP Method: POST 
    * URL: localhost:8080/compensation
    * PAYLOAD: Compensation
    * RESPONSE: Compensation
```    
Output JSON
```json
{
  "employee": {
    "employeeId": "16a596ae-edd3-4847-99fe-c4518e82c86f",
    "firstName": "John",
    "lastName": "Lennon",
    "position": "Development Manager",
    "department": "Engineering",
    "directReports": [
      {
        "employeeId": "b7839309-3348-463b-a7e3-5de1c168beb3",
        "firstName": null,
        "lastName": null,
        "position": null,
        "department": null,
        "directReports": null
      },
      {
        "employeeId": "03aa1462-ffa9-4978-901b-7c001562cf6f",
        "firstName": null,
        "lastName": null,
        "position": null,
        "department": null,
        "directReports": null
      }
    ]
  },
  "salary": 7000,
  "effectiveDate": "2020-22-17-13:22"
}
```
```
* READ
    * HTTP Method: GET 
    * URL: localhost:8080/compensation/employee/{id}
    * RESPONSE: Compensation
```
Sample endpoint : http://localhost:8080/compensation/employee/16a596ae-edd3-4847-99fe-c4518e82c86f    
Output JSON
```json
{
  "employee": {
    "employeeId": "16a596ae-edd3-4847-99fe-c4518e82c86f",
    "firstName": "John",
    "lastName": "Lennon",
    "position": "Development Manager",
    "department": "Engineering",
    "directReports": [
      {
        "employeeId": "b7839309-3348-463b-a7e3-5de1c168beb3",
        "firstName": null,
        "lastName": null,
        "position": null,
        "department": null,
        "directReports": null
      },
      {
        "employeeId": "03aa1462-ffa9-4978-901b-7c001562cf6f",
        "firstName": null,
        "lastName": null,
        "position": null,
        "department": null,
        "directReports": null
      }
    ]
  },
  "salary": 7000,
  "effectiveDate": "2020-22-17-13:22"
}
```
## Delivery
Please upload your results to a publicly accessible Git repo. Free ones are provided by Github and Bitbucket.
