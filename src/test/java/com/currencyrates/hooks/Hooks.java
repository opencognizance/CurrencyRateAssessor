package com.currencyrates.hooks;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

/**
 * This class contains setup and teardown methods for handling test scenarios
 * with ExtentReports integration in a Cucumber framework.
 */
public class Hooks {
    private ExtentTest extentTest;

    /**
     * This method is executed before each test scenario.
     * It initializes the ExtentTest object for the current scenario.
     *
     * @param scenario The current test scenario.
     */
    @Before
    public void setupTest(Scenario scenario) {
        extentTest = ExtentManager.getInstance().createTest(scenario.getName());
        ExtentManager.setTest(extentTest);
    }

    /**
     * This method is executed after each test scenario.
     * It logs the result of the test scenario (pass/fail) to the ExtentReport.
     *
     * @param scenario The current test scenario.
     */
    @After
    public void tearDown(Scenario scenario) {
        ExtentTest extentTest = ExtentManager.getTest();
        if (scenario.isFailed()) {
            extentTest.log(Status.FAIL, "Test Case Failed");
        } else {
            extentTest.log(Status.PASS, "Test Case Passed");
        }
        ExtentManager.flush();
    }
}
