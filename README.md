
# RestAssured Practice

This project is an API automation practice using RestAssured and Cucumber. It includes the implementation of 4 test cases for an API in a development environment. The project is built with Java and managed using Maven.

## Technologies Used
- **Java**: The programming language used for test automation.
- **Maven**: The project management and build tool.
- **RestAssured**: A Java library for testing and validating REST services.
- **POJO**: Plain Old Java Objects are used for data serialization and deserialization.
- **Cucumber**: A behavior-driven development (BDD) testing framework for writing test scenarios in plain text.

## Getting Started
Follow these instructions to set up and run the project on your local machine.

### Prerequisites
- Java JDK (version 8 or higher)
- Maven
- IDE (e.g., IntelliJ IDEA, Eclipse)

### Clone the Repository
Clone this repository to your local machine using your preferred Git client or by downloading the ZIP file from the GitHub page.
```bash
git clone https://github.com/slopezmazo/assuredApi.git
```


### Build the Project
Open a terminal or command prompt, navigate to the project directory, and run the following command to build the project:
```bash
cd <project-directory>
mvn clean install
```

## Project Structure

- `src/test/java`: Contains Java step definitions classes.
- `src/test/resources/features`: Contains Cucumber feature files with test scenarios.
- `pom.xml`: Maven project configuration file.

## Test Execution
- The test scenarios are written in Gherkin format in Cucumber feature files located in `src/test/resources/features`.
- Step definitions for the scenarios are implemented in `src/test/java/com/testing/api/stepdefinitions/`.

## Reporting
After running the tests, you can find the test logs in the `target/logs` directory.
