Feature: Post Gist Comment endpoint
  As an user
  I should be able to post a comment to existing comment

  Only authenticated users can post a gist comment via HTTP POST request
  This requires a github account generated personalized token to be
  provided as "authToken" property name.
  Please check serenity.conf under test resources for more details.

  @gist-comment-checks @functional-checks
  Rule: Gist Comment

  @schema-checks @gist-comment-schema-check
  Scenario: Github gist post comment response schema validation

  Github gist comments POST requests response should be evaluated against the specified response json schema
  [POST endpoint](https://api.github.com/gists/{gistid}/comment).

    Given Funnyguy21 can authenticate with his github account personalized token
    When Funnyguy21 posts any gist comment to 1656310e48afa42b6c7f05b068796547 using api
    Then the schema should match with the specification defined in gist_comment_schema.json

  @smoke-checks @happy-path-checks @gist-comment-happy-path-checks
  Scenario Outline: Github gist post comment response validation

  Github gist comments POST requests response should be evaluated
  using the [POST endpoint](https://api.github.com/gists/{gistid}/comment).

    Given Funnyguy21 can authenticate with his github account personalized token
    When Funnyguy21 posts a gist comment <message> to <gist-id> using api
    Then he should get the response body property body contains <message>

    Examples:
      | gist-id                          | message                                                    |
      | 1656310e48afa42b6c7f05b068796547 | commenting from serenity-cucumber-rest-assured-api-request |

  @negative-checks @gist-comment-negative-checks
  Scenario: Github gist comment 401 authorization error validation

  Github gist comments POST requests error response should be returned when user not provided authentication token
  [POST endpoint](https://api.github.com/gists/{gistid}/comment).

    Given Eran do not have an authentication token
    When Eran requests github user endpoint
    Then he should receive an error status 401 unauthorized
    And he receives an error json with message Requires authentication


  @negative-checks @gist-comment-negative-checks
  Scenario Outline: Github gist not available 404 error validation

  Github gist comments POST requests  error response should be returned when user not provided authentication token
  [POST endpoint](https://api.github.com/gists/{gistid}/comment).

    Given Funnyguy21 can authenticate with his github account personalized token
    When Funnyguy21 posts a gist comment <message> to <gist-id> using api
    Then he should receive an error status 404 not found
    And he receives an error json with message Not Found

    Examples:
      | gist-id | message                                                    |
      | 1656310 | commenting from serenity-cucumber-rest-assured-api-request |