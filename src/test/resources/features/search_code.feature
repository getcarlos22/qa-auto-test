Feature: Get Search Code endpoint
  As an user
  I should be able to search code repositories to get specific code files information

  An unauthenticated user can search code repos via GET requests.[Search-Code-endpoint](https://api.github.com/search/code)
  This returns a list of search code repo files based on the queryParam "q" value.
  To understand more about building the query param refer to :
  1. [Search](https://docs.github.com/en/rest/reference/search)
  2. [Searching for repositories](https://docs.github.com/en/github/searching-for-information-on-github/searching-on-github/searching-for-repositories)

  This queryParam should not be url encoded: For more technical details refer to : [com.github.commontasks.CommonRequestSpecification.java#line57]

  @search-code-checks @functional-checks
  Rule: Search-Code-Repositories

  @smoke-checks @happy-path-checks @search-code-happy-path-checks
  Scenario Outline: Search Code Repositories for various repos

  Search Code repo requests can be evaluated using the [GET endpoint](https://api.github.com/search/code?q={searchQueryParam}).

    When Una requests search code repositories endpoint for <query_param>
    Then she should get the response body property total_count with the value <total_count>

    Examples:
      | query_param                                                                     | total_count |
      | user:vamsidarbhamulla+language:Java+repo:vamsidarbhamulla/testng-gradle-example | 8           |
      | user:vamsidarbhamulla+repo:vamsidarbhamulla/serenity-bdd-maven-junit-example    | 15          |
      | user:vamsidarbhamulla+language:json                                             | 2           |


  @negative-checks @search-code-empty-response-checks
  Scenario: Search Code Repositories for empty response

  Search Code repo requests for a user with files with not available programming language (eg: rust)
  Using [GET endpoint](https://api.github.com/search/code?q={searchQueryParam}).

    When Eran requests search code repositories endpoint for user:vamsidarbhamulla+language:rust
    Then he should get the response body property total_count with the value 0