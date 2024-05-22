package com.currencyrates.hooks;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {
    private ExtentTest extentTest;

    @Before
    public void setupTest(Scenario scenario) {
        extentTest = ExtentManager.getInstance().createTest(scenario.getName());
    }

    @After
    public void tearDown(Scenario scenario){
        if (scenario.isFailed()) {
            extentTest.log(Status.FAIL, "Test Case Failed");
        } else {
            extentTest.log(Status.PASS, "Test Case Passed");
        }
        ExtentManager.getInstance().flush();
    }
}
