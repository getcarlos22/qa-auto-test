# Java-Serenity2-RestAssured4-Cucumber6-JUnit4-Maven Github API Automation Solution


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
- Each domain package consist of an Action class where API actions are defined and another Question class where user questions/assertions are written.
- These domain models are called from a step-definitions class which are in-turn called from BDD tests.
- A test scenario to validate API response schema has been included for each endpoint in the respective feature file. The API spec for schema comparison is placed inside "schema" folder in test resources. The specs are generated from https://www.liquid-technologies.com/online-json-to-schema-converter.
- An auth token is needed to fetch the user information and post a comment on an existing gist. 
- A personalized github account token has been created and encrypted based on different encryption password for each test environment and placed them in serenity.conf file.
- All the encrypted properties are dynamically identified and decrypted at run time based on the encrypted password provided in serenity.conf file for each test environment.
- Created a dummy github account named funnyguy21 to enable the user and gists comments using encrypted personalized auth token. 
- Configurations for different environments are set in the `test/resources/serenity.conf` file.
- Added a debug mode param for each test environment to provide more context/information on console while building tests or running them using IDE (intellij).
- Default and Dev environments are enabled with isDebug=true in serenty.conf however staging environment is disabled for debugging.

### The project directory structure

```Gherkin
src
  + test
    + java                          Test runners and supporting code
      + commontasks                 Package for all common actions and questions
          CommonQuestions           All common questions/validations across all the domain models
          CommonRequestSpec         Common Request Spec for the API calls
      + commonutilies               Common utility methods
        + functional                Jdk 8 or above functional programming specific util methods 
            Memoize                 A concurrent hashmap based in-memory lazy loading jdk8 supplier
        PropertyDecrypter           Jasypt based Property Decrypter to decrypt the encrypted auth token property values
        TestEnv                     A wrapper on top of serenity implementation to get hold of all properties and environment variables based on test environment that can dynamically decrypt the encrypted properties
      + gists                       Domain model package consisting of all actions/questions on github gist comments feature/functionality
          GithubGistsActions        API calls/User actions on Github Gists post comments APIs
          GithubGistsQuestions      User questions/validations on Github Gists post API response
      + search                      Domain model package consisting of all actions/questions on github search code feature/functionality
      + stepdefinitions             Step definitions for the BDD feature
        CommonSteps                 Common steps that comprise multiple feature file gherkin based cucumber expressions handler
        GetUserSteps                User feature specific steps
        GistCommentSteps            Gist Comments feature specific steps 
        SearchCodeSteps             Search Code feature specific steps
      + user                        Domain model package consisting of all actions/questions on github user feature/functionality
    + resources
      + features                    Feature files directory
          gist_comments.feature     Feature containing BDD scenarios
      + schema                      Folder containing json schema for API schema validation
      Serenity.conf                 Configurations file

```
## Executing the tests
Run `mvn clean verify` from the command line.

The test results will be recorded here `target/site/serenity/index.html`.
Please run the below command (in MAC) from root directory to open the result after execution.
```bash
open target/site/serenity/index.html 
```

```bash
mvn jetty:run ## by default report runs at http://localhost:2222

## to change the report listening port 
mvn -Djetty.port=7777 jetty:run ## then open http://localhost:7777
```

### Additional configurations

Additional command line parameters can be passed for switching the application environment.
```bash
mvn clean verify -Denvironment=dev
```

To run specific checks use cucumber tags feature 
```bash
## to run all scenarios
mvn clean verify -Dcucumber.filter.tags="@functional-checks" # 9 tests will be executed

## to run individual feature based scenarios
mvn clean verify -Dcucumber.filter.tags="@user-checks" # all user functional scenarios  - 3 tests will be executed
mvn clean verify -Dcucumber.filter.tags="@search-code-checks" # all search code functional scenarios - 2 tests will be executed
mvn clean verify -Dcucumber.filter.tags="@gist-comment-checks" # all gist comments functional scenarios - 4 tests will be executed

## to run all features smoke checks
mvn clean verify -Dcucumber.filter.tags="@smoke-checks" #  5 tests will be executed

## to run all features happy path checks 
mvn clean verify -Dcucumber.filter.tags="@happy-path-checks"  # 5 tests will be executed

## to run all features negative scenario checks 
mvn clean verify -Dcucumber.filter.tags="@negative-checks"  # 4 tests will be executed

## to run all features api response schema checks
mvn clean verify -Dcucumber.filter.tags="@schema-checks" # 2 tests will be executed

## to run only user and search code features checks 
mvn clean verify -Dcucumber.filter.tags="@user-checks or @search-code-checks" # 7 tests will be executed
```
