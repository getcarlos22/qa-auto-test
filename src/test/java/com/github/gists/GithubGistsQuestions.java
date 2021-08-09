package com.github.gists;

import static org.assertj.core.api.Assertions.assertThat;

import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;

public class GithubGistsQuestions {

    @Step("Get userId from response")
    public String getCommentBody(Response userDetailResp) {
        return userDetailResp.getBody().jsonPath().getString("body");
    }

    @Step("Validate comment message {0} present in response")
    public void validateCommentBody(String message, Response lastResponse) {
        assertThat(getCommentBody(lastResponse)).containsIgnoringCase(message);
    }
}
