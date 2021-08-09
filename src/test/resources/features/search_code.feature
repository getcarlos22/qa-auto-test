@Id=2.1
Feature: Get Search Code endpoint
  As an user
  I should be able to search code repositories to get specific code files information

  An unauthenticated user can search code repos via GET requests.[Search-Code-endpoint](https://api.github.com/search/code)
  This returns a list of search code repo files based on the queryParam "q" value.
  To understand more about building the query param refer to :
  1. [Search](https://docs.github.com/en/rest/reference/search)
  2. [Searching for repositories](https://docs.github.com/en/github/searching-for-information-on-github/searching-on-github/searching-for-repositories)

  This queryParam should not be url encoded: For more technical details refer to : [com.github.commontasks.CommonRequestSpecification.java#line57]

  @search @End2End
  Rule: Search-Code-Repositories

  @smokeTest
  Scenario Outline: Search Code Repositories for various repos

  Search Code repo requests can be evaluated using the [GET endpoint](https://api.github.com/search/code?q={searchQueryParam}).

    When Ama requests search code repositories endpoint for <query_param>
    Then she should get the response body property total_count with the value <total_count>

    Examples:
      | query_param                                             | total_count |
      | user:getcarlos22+language:Java+repo:getcarlos22/RestAPI | 14          |
      | user:getcarlos22+repo:getcarlos22/RestAPI               | 35          |
      | user:getcarlos22+language:json                          | 3           |


  @NegativeScenario
  Scenario: Search Code Repositories for empty response

  Search Code repo requests for a user with files with not available programming language (eg: rust)
  Using [GET endpoint](https://api.github.com/search/code?q={searchQueryParam}).

    When Kingsley requests search code repositories endpoint for user:getcarlos22+language:Java
    Then he should get the response body property total_count with the value 27