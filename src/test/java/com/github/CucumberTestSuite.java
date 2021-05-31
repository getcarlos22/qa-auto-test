package com.github;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
    plugin = {"pretty"},
    features = "src/test/resources/features",
    glue = "com.github.stepdefinitions"
    // Uncomment the below line to run a single scenario while building or debugging in IDE (Eg: Intellij)
    // ,tags = "@functional-checks"
)
public class CucumberTestSuite {

}
