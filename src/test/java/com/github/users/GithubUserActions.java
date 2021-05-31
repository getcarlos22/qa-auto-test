package com.github.users;

import com.github.commontasks.CommonRequestSpec;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class GithubUserActions {

  private static final String USER_ENDPOINT = "/user";

  @Step("Get details for user {0}")
  public Response getUserDetails(String username) {
    return userResponse(CommonRequestSpec.githubAuthReqSpec());
  }

  @Step("Get details for user {0} with authspec {1}")
  public Response getUserDetailsWith(String username, RequestSpecification authSpec) {
    return userResponse(authSpec);
  }

  private Response userResponse(RequestSpecification authSpec) {
    return SerenityRest.given().spec(authSpec)
        .basePath(USER_ENDPOINT)
        .get().then().extract().response();
  }
}
