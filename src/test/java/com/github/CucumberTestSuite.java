package com.github;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
    plugin = {"pretty"},
    features = "src/test/resources/features",
    glue = "com.github.stepdefinition"

)
public class CucumberTestSuite {

}
