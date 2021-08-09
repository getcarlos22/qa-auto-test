package com.github.stepdefinitions;

import static net.serenitybdd.rest.SerenityRest.lastResponse;

import com.github.commontasks.CommonQuestions;
import com.github.commontasks.CommonRequestSpec;
import com.github.users.GithubUserActions;
import com.github.users.GithubUserQuestions;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.specification.RequestSpecification;
import net.thucydides.core.annotations.Steps;


public class GetUserSteps {

  private RequestSpecification requestSpecification;

  @Steps
  GithubUserActions githubUserActions;

  @Steps
  CommonQuestions commonQuestions;

  @Steps
  GithubUserQuestions githubUserQuestions;

  @When("{} requests user endpoint with authSpec")
  public void actor_call_the_get_user_endpoint_with_authSpec(String actor) {
    githubUserActions.getUserDetails(actor);
  }

  @Then("(s)he should get the response body property id to {int}")
  public void user_details_should_be_retrieved(int id) {
    commonQuestions.responseCodeIs(200, lastResponse());
    githubUserQuestions.validateId(id, lastResponse());
  }

  @Then("(s)he should get the response body property login to {}")
  public void username_should_be(String userName) {
    githubUserQuestions.validateUserName(userName, lastResponse());
  }

  @Given("Kingsley do not have an authentication token")
  public void Kingsley_do_not_have_an_authentication_token() {
    requestSpecification = CommonRequestSpec.githubReqSpec();
  }

  @When("{} requests github user endpoint")
  public void actor_call_the_get_user_endpoint(String actor) {
    githubUserActions.getUserDetailsWith(actor, requestSpecification);
  }
}
