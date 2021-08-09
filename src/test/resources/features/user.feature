@Id=1.1
Feature: Get user endpoint
  As an user
  I should be able to retrieve my information from github user endpoint

  Only authenticated users can get their specific information via HTTP GET request
  This requires a github account generated personalized token to be
  provided as "authToken" property name.
  Please check serenity.conf under test resources for more details.

  @User @End2End
  Rule: User

  @schema
  Scenario: Github get user response schema validation

  Github user GET requests response should be evaluated against the specified response json schema
  [GET endpoint](https://api.github.com/user).

    Given carlos can authenticate with his github account personalized token
    When carlos requests user endpoint
    Then the schema should match with the specification defined in user_response_schema.json

  @smokeTest
  Scenario Outline: Github get user response validation

  Github user requests response properties and values should be evaluated
  using the [GET endpoint](https://api.github.com/user).

    When Funnyguy21 requests user endpoint with authSpec
    Then he should get the response body property id to <id>
    Then he should get the response body property login to <login>

    Examples:
      | id       | login      |
      | 84763518 | funnyguy21 |

  @NegativeScenario
  Scenario: Github get user response error validation

  Github user GET requests error response should be returned when user not provided authentication token
  [GET endpoint](https://api.github.com/user).

    Given Kingsley do not have an authentication token
    When Kingsley requests github user endpoint
    Then he should receive an error status 401 unauthorized
    And he receives an error json with message Requires authentication