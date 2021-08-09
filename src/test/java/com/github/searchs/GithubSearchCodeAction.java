package com.github.searchs;

import com.github.tasks.RequestSpec;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class GithubSearchCodeAction {

  private static final String SEARCH_CODE_ENDPOINT = "/searchs/code";

  @Step("Get details for user {0} with authspec {1}")

  public Response getSearchCodeResponse(String username, String queryParam) {
    return SerenityRest.given().spec(RequestSpec.githubReqSpec(false))
        .basePath(SEARCH_CODE_ENDPOINT)
        .queryParam("q", queryParam)
        .get().then().extract().response();
  }
}
