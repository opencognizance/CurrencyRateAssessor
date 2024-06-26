package com.currencyrates.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.currencyrates.stepdefinitions","com.currencyrates.hooks"}
)
public class Runner {
}
