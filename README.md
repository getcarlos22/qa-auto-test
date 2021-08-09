# qa-test-leaseplan


## Introduction

This is a Rest API test solution for few Github endpoints available in https://api.github.com/. 
The published APIs represent a github application where user can get his information , 
search through all public code repositories and post a comment to an existing user gists.

## Dependencies Versions <br>
Tests are written using a combination of Java, SerenityBDD, RestAssured, Cucumber, Junit & Maven. <br>
Java - JDK11 <br>
Maven - 3.x or higher <br>
SerenityBDD - 2.4.24 <br>
RestAssured - 4.3.3 <br>
Cucumber - 6.10.4 <br>
Junit - 4.12 <br>

## Framework & Design Considerations
- Serenity BDD is a library that makes it easier to write high quality automated acceptance tests, with powerful reporting and living documentation features. It has strong support for both web testing with Selenium, and API testing using RestAssured.
- API calls & validations are made using RestAssured and SerenityRest which is a wrapper on top of RestAssured. 
- Tests are written in BDD Gherkin format in Cucumber feature files and it is represented as a living documentation in the test report. 
- This solution is designed in an Action-Question pattern with the code base categorized into domain model packages based on user actions and questions to understand/validate results. 

## Executing the tests
Run `mvn clean verify` from the command line.

## Serenity Reports
The test results will be recorded in the `target/site/serenity/index` directory.

## Allure Reports
To be able to view the reports for the Testing on your PC, install Allure Report first on the Machine (https://docs.qameta.io/allure/). Afterwards proceed to the Project directory on your PC, navigate to target/allure-results folder. Run the command `allure serve ./` 

To run specific checks use cucumber tags feature 
```bash
## to run all scenarios
mvn clean verify -Dcucumber.filter.tags="@End2End" # 9 tests will be executed

## to run individual feature based scenarios
mvn clean verify -Dcucumber.filter.tags="@Id=1.1" # all users functional scenarios  - 3 tests will be executed
```
