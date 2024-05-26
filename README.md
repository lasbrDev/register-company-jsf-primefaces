# Project: Company Registration JSF

## Project Overview

The Company Registration JSF project is a Java web application designed for managing company registration data. It utilizes JavaServer Faces (JSF) as the web framework and follows a structured architecture, encompassing model, repository, service, and utility layers. The project also includes example classes for testing server responses and demonstrating report functionalities.

Note: This project is still under development.

## Project Structure

### br.com.lasbr.erp.model

Contains model classes, such as `Company`, `CompanyType`, and `FieldActivity`.

### br.com.lasbr.erp.repository

Responsible for data access. Contains classes like `Companies` and `FieldActivities` that provide methods for retrieving, saving, and removing data from the database.

### br.com.lasbr.erp.service

Provides services related to business logic. `RegistrationCompanyService` is a service for saving and removing companies.

### br.com.lasbr.erp.util

Contains utility classes. `EntityManagerProducer` is responsible for creating and closing `EntityManager` instances for interacting with the database. `TransactionalInterceptor` is an interceptor that manages transactions.

### br.com.lasbr.id

Additional package with example classes (`Main`, `Orders`, `ReportService`, `ReportServlet`) to test the server response at localhost:8080/company-registration-jsf/relatorio.

## Main Methods

### `Company` (br.com.lasbr.erp.model.Company)

- **`save` and `remove` (in `Companies`):** Methods for saving and removing companies in the database.

### `FieldActivity` (br.com.lasbr.erp.model.FieldActivity)

- **`search` (in `FieldActivities`):** Performs a search for field activities in the database based on a provided description.

### `PersistenceLayer` (br.com.lasbr.erp.repository.PersistenceLayer)

- **`main`:** Example of using the `Companies` and `FieldActivities` classes to interact with the database. Performs operations like searching, creating, and removing companies.

### `EntityManagerProducer` (br.com.lasbr.erp.util.EntityManagerProducer)

- **`createEntityManager` and `closeEntityManager`:** Methods for creating and closing `EntityManager` instances.

### `TransactionalInterceptor` (br.com.lasbr.erp.util.TransactionalInterceptor)

- **`invoke`:** Interceptor that manages transactions around methods annotated with `@Transactional`.

### `ReportService` (br.com.lasbr.id.ReportService) and `ReportServlet` (br.com.lasbr.id.ReportServlet)

- **`totalOrdersCurrentMonth` and `doGet`:** Examples of classes demonstrating how to retrieve and display report information.


## Configuration of the Environment

Make sure to have installed on your machine:

- Java 17
- Apache Tomcat 8.5.99 (installed locally)
- Maven
- Eclipse IDE

### Database

#### Option 1: Local MySQL

1. Install MySQL locally.
2. Execute the `initial-data.sql` script located in `src/main/resources/sql` to create the necessary tables in MySQL.

#### Option 2: MySQL via Docker

1. Ensure Docker is installed.
2. Execute the following command to start a MySQL container (replace `YOUR_USER` and `YOUR_PASSWORD` with the desired values):

    ```bash
    docker run -d --name mysql_dev -p 33306:3306 -e MYSQL_ROOT_PASSWORD=YOUR_PASSWORD -e MYSQL_DATABASE=company_registration -v mysql-database:/var/lib/mysql --network REDEMSQL mysql:latest
    ```

    - `YOUR_USER`: User created to access MySQL.
    - `YOUR_PASSWORD`: Password associated with the created user.

3. Wait a few seconds for MySQL to start.
4. Execute the `initial-data.sql` script located in `src/main/resources/sql` to create the necessary tables in MySQL.

## Development Environment Setup

1. Import the project into Eclipse IDE.
2. Make sure to configure the `persistence.xml` file with the correct information for your database.

## Running the Project

1. Start the Tomcat 8.5.99 server locally.
2. Execute the project in your IDE or compile and deploy the generated WAR file on Tomcat.

### Running with Docker

If you prefer, you can use the locally installed MySQL or the Docker instance.

## Testing the Application

You can use the [http://localhost:8080/company-registration-jsf/relatorio](http://localhost:8080/company-registration-jsf/relatorio) endpoint to test the server response.

## Contribution

Feel free to contribute with improvements, bug fixes, or new features.

## Authors

- [@lasbrDev](https://github.com/lasbrDev)

