package com.github.tasks;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.assertj.core.api.Assertions.assertThat;

import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;

public class Question {
  /**
   * Get Request Specification for github api endpoints
   *
   * @return RequestSpecification
   */

  @Step("Verify that API response is {0}")
  public void responseCodeIs(int responseCode, Response lastResponse) {
    assertThat(lastResponse.statusCode()).isEqualTo(responseCode);
  }

  @Step("Verify that response is empty list")
  public void responseShouldHaveErrorMessage(Response lastResponse, String message) {
    assertThat(lastResponse.getBody().jsonPath().getString("message")).isEqualToIgnoringCase(message);
  }

  @Step("Verify response schema with {1}")
  public void verifyResponseSchema(Response lastResponse, String schemaJson) {
    lastResponse.then().body(matchesJsonSchemaInClasspath("schema/" + schemaJson));
  }
}
