package com.github.stepdefinitions;

import static net.serenitybdd.rest.SerenityRest.lastResponse;

import com.github.commontasks.CommonQuestions;
import com.github.commontasks.CommonRequestSpec;
import com.github.search.GithubSearchCodeActions;
import com.github.search.GithubSearchCodeQuestions;
import com.github.users.GithubUserActions;
import com.github.users.GithubUserQuestions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.specification.RequestSpecification;
import net.thucydides.core.annotations.Steps;


public class SearchCodeSteps {

  @Steps
  GithubSearchCodeActions githubSearchCodeActions;

  @Steps
  CommonQuestions commonQuestions;

  @Steps
  GithubSearchCodeQuestions githubSearchCodeQuestions;

  @When("{} requests search code repositories endpoint for {}")
  public void actor_requests_search_code_repo_endpoint_with_query_param(String actor, String queryParam) {
    githubSearchCodeActions.getSearchCodeResponse(actor, queryParam);
  }

  @Then("(s)he should get the response body property total_count with the value {int}")
  public void should_Get_TheResponseBody_Property_total_count(int totalCount) {
    commonQuestions.responseCodeIs(200, lastResponse());
    githubSearchCodeQuestions.validateTotalFileCount(totalCount, lastResponse());
  }
}
