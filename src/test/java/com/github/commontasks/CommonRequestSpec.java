package com.github.commontasks;

import com.github.commonutilities.TestEnv;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;

public class CommonRequestSpec {
  /**
   * Get Request Specification for github api endpoints
   *
   * @return RequestSpecification
   */

  public static RequestSpecification githubReqSpec() {
    return githubReqSpecBuilder().build();
  }

  public static RequestSpecification githubReqSpec(Boolean urlEncoding) {
    return githubReqSpecBuilder(urlEncoding).build();
  }

  public static RequestSpecification githubAuthReqSpec() {
    RequestSpecBuilder specBuilder = githubReqSpecBuilder();

    String authToken = TestEnv.getProp("authToken");

    return specBuilder.addHeader(
            "Authorization", "Basic " + authToken
    ).build();
  }

  private static RequestSpecBuilder githubReqSpecBuilder() {
    return githubReqSpecBuilder(true);
  }

  private static RequestSpecBuilder githubReqSpecBuilder(boolean urlEncoding) {
    final String baseUrl = TestEnv.getProp("baseUrl");

    RequestSpecBuilder specBuilder = new RequestSpecBuilder()
            .setBaseUri(baseUrl)
            .setContentType("application/json");

    final String isDebug = TestEnv.getProp("isDebug");

    if (!urlEncoding) {
      specBuilder.setUrlEncodingEnabled(false);
    }

    if(isDebug.equalsIgnoreCase("true")) {
      specBuilder.addFilter(new RequestLoggingFilter())
              .addFilter(new ResponseLoggingFilter())
              .addFilter(new ErrorLoggingFilter());
    }

    return specBuilder;
  }
}
