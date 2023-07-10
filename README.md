# MetricService
A Java application exposing a REST API. The purpose of this application will be to record numerical metric values. and demonstrates my ability of creating a Spring application from the ground up.


## Prerequisites

Before running this application, ensure that you have the following prerequisites installed:

- Java Development Kit (JDK) 8 or later
- MySQL database(I have used version 8.0.33)

## Getting Started

To get the project up and running, follow these steps:

1. Clone the repository to your local machine: `git clone https://github.com/nahidDeveloper/MetricService`
2. Set up the MySQL database:
- Create a MySQL database named `metrics`.
- Create an application.properties file in `src/main/resources` and copy the configurtion from `src/main/resources/application.properties.test`
- Update the database credentials in the `src/main/resources/application.properties` file with your actual MySQL database username and password.

3. Build the project using Maven: `mvn clean package`

4. Run the application:

The application will start running on `http://localhost:8080`.

## API Documentation

The application provides the following endpoints:

- `GET /metrics`: Retrieves a list of metrics.
- `GET /metrics/{id}`: Retrieves a single metric by ID.
- `POST /metrics`: Creates a new metric.
- `PUT /metrics/{id}`: Updates an existing metric.
- `GET /metricsummary`: Retrieves a metric summary.(Not working as intended as you cannot yet create a Metric Summary)

For detailed information about the API endpoints and request/response formats, refer to the API documentation.

## Logging

This application uses Log4j for logging. The log configuration can be found in the `src/main/resources/log4j2.xml` file. The logs are written to the console and can be customized as per your requirements.

## Updating
I found this project very fun to make and will be trying to add more features such as API Rate limiting with various different types of algorithms as well us unit and Integration Testing
