package com.github.gists;

import com.github.commontasks.CommonRequestSpec;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class GithubGistsActions {

    private static final String GISTS_ENDPOINT = "/gists";
    private static final Function<String, String> COMMENTS_ENDPOINT =
            (gistId) ->  "/" + gistId + "/comments";

    @Step("Post comment for gist {0}")
    public Response postCommentsForGist(String gistId) {
        return postCommentsForGist("Comment from serenity-cucumber-rest-assured-api-post-request", gistId);
    }

    @Step("Post comment message {0} for gist {1}")
    public Response postCommentsForGist(String comment, String gistId) {
        return gistResponse(comment, gistId, CommonRequestSpec.githubAuthReqSpec());
    }

    private Response gistResponse(String comment, String gistId, RequestSpecification authSpec) {
        return SerenityRest.given()
                .spec(authSpec)
                .basePath(GISTS_ENDPOINT)
                .body(Map.of("body", comment + " " + UUID.randomUUID()))
                .post(COMMENTS_ENDPOINT.apply(gistId))
                .then().extract().response();
    }
}
