package com.github.search;

import static org.assertj.core.api.Assertions.assertThat;

import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;

public class GithubSearchCodeQuestions {

  @Step("Get userId from response")
  public int getFileCount(Response userDetailResp) {
    return userDetailResp.getBody().jsonPath().getInt("total_count");
  }

  @Step("Validate id {0} present in response")
  public void validateTotalFileCount(int id, Response lastResponse) {
    assertThat(getFileCount(lastResponse)).isEqualTo(id);
  }
}
