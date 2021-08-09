package com.github.users;

import static org.assertj.core.api.Assertions.assertThat;

import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;

public class GithubUserQuestions {

  @Step("Get userId from response")
  public int getUserId(Response userDetailResp) {
    return userDetailResp.getBody().jsonPath().getInt("id");
  }

  @Step("Get username from response")
  public String getUserName(Response userDetailResp) {
    return (userDetailResp.getBody().jsonPath().getString("login"));
  }

  @Step("Validate username {0} present in response")
  public void validateUserName(String userName, Response lastResponse) {
    assertThat(getUserName(lastResponse)).isEqualToIgnoringCase(userName);
  }

  @Step("Validate id {0} present in response")
  public void validateId(int id, Response lastResponse) {
    assertThat(getUserId(lastResponse)).isEqualTo(id);
  }
}
