package com.github.search;

import com.github.commontasks.CommonRequestSpec;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class GithubSearchCodeActions {

  private static final String SEARCH_CODE_ENDPOINT = "/search/code";

  @Step("Get details for user {0} with authspec {1}")
  public Response getSearchCodeResponse(String username, String queryParam) {
    return SerenityRest.given().spec(CommonRequestSpec.githubReqSpec(false))
        .basePath(SEARCH_CODE_ENDPOINT)
        .queryParam("q", queryParam)
        .get().then().extract().response();
  }
}
