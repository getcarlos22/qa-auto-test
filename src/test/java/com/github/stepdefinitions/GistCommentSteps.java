package com.github.stepdefinitions;

import static net.serenitybdd.rest.SerenityRest.lastResponse;

import com.github.commontasks.CommonQuestions;
import com.github.gists.GithubGistsActions;
import com.github.gists.GithubGistsQuestions;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

public class GistCommentSteps {

    @Steps
    GithubGistsActions gistsActions;

    @Steps
    GithubGistsQuestions gistsQuestions;

    @Steps
    CommonQuestions commonQuestions;

    @When("{} posts a gist comment {} to {} using api")
    public void actor_posts_a_gist_comment(String actor, String message, String gistId) {
        gistsActions.postCommentsForGist(message, gistId);
    }

    @When("{} posts any gist comment to {} using api")
    public void actor_posts_any_gist_comment(String actor, String gistId) {
        gistsActions.postCommentsForGist(gistId);
    }

    @Then("he should get the response body property body contains {}")
    public void he_should_get_the_response_property_body_contains(String message) {
        commonQuestions.responseCodeIs(201, lastResponse());
        gistsQuestions.validateCommentBody(message, lastResponse());
    }
}
