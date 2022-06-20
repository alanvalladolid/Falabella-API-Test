# Falabella API Test Automation


### Table of contents
* [About](#About)
* [Project structure](#project-structure)
* [Suites](#suites)
* [Run tests](#run-tests)
* [Reports](#reports)

## About
This project is implemented using
[TestNG](https://testng.org/), [Maven](https://maven.apache.org/) and [RestAssured](https://rest-assured.io/).

## Project structure
* `APITest` Class contains test scripts.
* `DataTest` Class contains data generator.

## Suites
* test classes that will be included in the test run
```xml
<test verbose="2" preserve-order="true" name="APITestFalabella">
    <classes>
        <class name="APITests"/>
    </classes>
</test>
```

## Run tests:
To run tests:
* Execute the testng.xml file.