package com.github.stepdefinition;

import static net.serenitybdd.rest.SerenityRest.lastResponse;

import com.github.tasks.Question;
import com.github.searchs.GithubSearchCodeAction;
import com.github.searchs.GithubSearchCodeQuestion;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;


public class SearchCodeStep {

  @Steps
  GithubSearchCodeAction githubSearchCodeAction;

  @Steps
  Question Questions;

  @Steps
  GithubSearchCodeQuestion githubSearchCodeQuestion;

  @When("{} requests search code repositories endpoint for {}")
  public void actor_requests_search_code_repo_endpoint_with_query_param(String actor, String queryParam) {
    githubSearchCodeAction.getSearchCodeResponse(actor, queryParam);
  }

  @Then("(s)he should get the response body property total_count with the value {int}")
  public void should_Get_TheResponseBody_Property_total_count(int totalCount) {
    Questions.responseCodeIs(200, lastResponse());
    githubSearchCodeQuestion.validateTotalFileCount(totalCount, lastResponse());
  }
}
