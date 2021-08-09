package com.github.stepdefinition;

import static net.serenitybdd.rest.SerenityRest.lastResponse;

import com.github.tasks.Question;
import com.github.gist.GithubGistAction;
import com.github.gist.GithubGistQuestion;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

public class GistCommentStep {

    @Steps
    GithubGistAction gistsActions;

    @Steps
    GithubGistQuestion gistsQuestions;

    @Steps
    Question question;

    @When("{} posts a gist comment {} to {} using api")
    public void actor_posts_a_gist_comment(String actor, String message, String gistId) {
        gistsActions.postCommentsForGist(message, gistId);
        //gistsQuestions.validateCommentBody(message, lastResponse());
    }

    @When("{} posts any gist comment to {} using api")
    public void actor_posts_any_gist_comment(String actor, String gistId) {
        gistsActions.postCommentsForGist(gistId);
    }

    @Then("he should get the response body property body contains {}")
    public void he_should_get_the_response_property_body_contains(String message) {
        question.responseCodeIs(201, lastResponse());
        gistsQuestions.validateCommentBody(message, lastResponse());
    }
}
