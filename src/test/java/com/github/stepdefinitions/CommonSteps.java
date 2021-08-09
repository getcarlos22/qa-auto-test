package com.github.stepdefinitions;

import static net.serenitybdd.rest.SerenityRest.lastResponse;

import com.github.commontasks.CommonQuestions;
import com.github.commontasks.CommonRequestSpec;
import com.github.users.GithubUserActions;
import com.github.users.GithubUserQuestions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.specification.RequestSpecification;
import net.thucydides.core.annotations.Steps;

public class CommonSteps {

    private RequestSpecification requestSpecification;

    @Steps
    GithubUserActions githubUserActions;

    @Steps
    CommonQuestions commonQuestions;

    @Given("{} can authenticate with his github account personalized token")
    public void actor_can_authenticated_With_github_personalized_token(String actor) {
        requestSpecification = CommonRequestSpec.githubAuthReqSpec();
    }

    @When("{} requests user endpoint")
    public void actor_call_the_get_user_endpoint(String actor) {
        githubUserActions.getUserDetailsWith(actor, requestSpecification);
    }

    @And("the schema should match with the specification defined in {}")
    public void the_schema_should_match_with_the_specification(String spec) {
        commonQuestions.verifyResponseSchema(lastResponse(), spec);
    }

    @Then("(s)he should receive an error status {int} {}")
    public void user_should_receive_error_status(int httpStatusCode, String errorStatusMessage) {
        commonQuestions.responseCodeIs(httpStatusCode, lastResponse());
    }

    @And("(s)he receives an error json with message {}")
    public void user_should_receive_error_message(String message) {
        commonQuestions.responseShouldHaveErrorMessage(lastResponse(), message);
    }
}
