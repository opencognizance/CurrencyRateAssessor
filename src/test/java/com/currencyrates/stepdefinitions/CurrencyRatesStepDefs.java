package com.currencyrates.stepdefinitions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.currencyrates.facade.CurrencyRatesEndpoints;
import com.currencyrates.hooks.ExtentManager;
import com.currencyrates.maps.PriceMaps;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

/**
 * Step definitions for currency rate retrieval and validation using Cucumber.
 */
public class CurrencyRatesStepDefs {
    private static final Logger logger = LogManager.getLogger(CurrencyRatesEndpoints.class);
    private String currency;
    private CurrencyRatesEndpoints ratesEndpoints;

    /**
     * Initializes a new instance of the CurrencyRatesStepDefs class,
     * setting up the CurrencyRatesEndpoints for use in step definitions.
     */
    public CurrencyRatesStepDefs() {
        this.ratesEndpoints = new CurrencyRatesEndpoints();
    }

    /**
     * Sets the currency for which the customer wants to get rates.
     *
     * @param currency The currency code for which rates are requested.
     */
    @Given("Customer wants to get rates for {string}")
    public void customer_wants_to_get_rates(String currency) {
        logger.info("Method customer_wants_to_get_rates called with currency: {}", currency);
        this.currency = currency;
        logger.debug("Currency set to: {}", this.currency);
    }

    /**
     * Performs a GET operation to retrieve rates for the specified currency.
     */
    @When("Customer wants to perform a GET operation to get rates")
    public void customerWantsToPerformAGETOperationToGetRates() {
        logger.info("Method customerWantsToPerformAGETOperationToGetRates() called");
        if (this.currency != null) {
            logger.info("Performing GET operation to get rates for currency: {}", this.currency);
            this.ratesEndpoints.getRateAgainstCurrency(this.currency);
            logger.debug("GET operation performed for currency: {}", this.currency);
        } else {
            logger.warn("Currency is not set. GET operation aborted.");
        }
    }

    /**
     * Validates that the API call was successful by checking the status code.
     */
    @Then("Validate that the API call is successfull and status code is {int}")
    public void validateThatTheAPICallIsSuccessfullAndStatusCodeIs(int expectedStatusCode) {
        logger.info("Method validateThatTheAPICallIsSuccessfull called");
        try {
            int statusCode = this.ratesEndpoints.getResponse().statusCode();
            logger.info("Received status code: {}", statusCode);
            Assert.assertEquals("The status code should be {} for the response", expectedStatusCode, statusCode);
            logger.info("API call validation successful. Status code is 200.");
        } catch (AssertionError e) {
            logger.error("API call validation failed. Expected status code {} but received {}", expectedStatusCode,this.ratesEndpoints.getResponse().statusCode());
            throw e;
        } catch (Exception e) {
            logger.error("An unexpected error occurred during API call validation: {}", e.getMessage());
            throw e;
        }
    }

    @Then("Validate that the API call is successfull and the status is {string}")
    public void validateThatTheAPICallIsSuccessfullAndTheStatusIs(String expectedStatus) {
        logger.info("Method validateThatTheAPICallIsSuccessfullAndTheStatusIs called");
        try{
            String actualStatus = this.ratesEndpoints.getCurrencyRatesResponseObject().getResult();
            logger.info("Received status : {}", actualStatus);
            Assert.assertEquals("The status should be {} for the response", expectedStatus, actualStatus.toUpperCase());
            logger.info("API call validation successful. Status is SUCCESS.");
        } catch (AssertionError e){
            logger.error("API call validation failed. Expected status {} but received {}", expectedStatus, this.ratesEndpoints.getCurrencyRatesResponseObject().getResult());
            throw e;
        } catch (Exception e){
            logger.error("An unexpected error occurred during API call validation: {}", e.getMessage());
            throw e;
        }
    }


    @Then("Validate that the price of {string} is range of {double} to {double}")
    public void validateThatThePriceOfUSDIsRangeOfTo(String currency, Double low, Double high) {
        logger.info("Method validateThatThePriceOfUSDIsRangeOfTo called");
        try {
            Double expectedPrice = PriceMaps.getPriceOfCurrency(this.ratesEndpoints.getCurrencyRatesResponseObject().getRates(), currency);
            logger.debug("Expected price retrieved: {}", expectedPrice);

            Assert.assertTrue("The USD price against " + currency + " is within the range", expectedPrice >= low && expectedPrice <= high);
            logger.info("The USD price against {} is within the range of {} to {}", currency, low, high);
        } catch (AssertionError e) {
            logger.error("The {} price against {} is outside the range of {} to {}", currency, this.currency,low, high);
            throw e;
        } catch (Exception e) {
            logger.error("An unexpected error occurred during API call validation: {}", e.getMessage());
            throw e;
        }
    }



    /**
     * Validates that the response time of the API call is more than, equals to, or less than the specified time.
     *
     * @param comparator the comparator to use for validation ("MORE", "EQUALS", "LESS").
     * @param time       the time to compare the response time against.
     * @param unit       the unit of time for the comparison (e.g., "SECONDS", "MILLISECONDS", "MICROSECONDS").
     * @throws IllegalArgumentException if an unsupported comparator is used.
     */
    @Then("Validate that the response time is {string} than {long} {string}")
    public void validateThatTheResponseTimeIsThan(String comparator, long time, String unit) {
        logger.info("Method validateThatTheResponseTimeIsThan called with comparator: {}, time: {}, unit: {}", comparator, time, unit);
        try {
            long responseTime = this.ratesEndpoints.getResponseTime(unit);
            logger.info("Retrieved response time: {} {}", responseTime, unit);

            switch (comparator.toUpperCase()) {
                case "MORE":
                    Assert.assertTrue("The response time should be more than " + time + " " + unit,
                            responseTime > time);
                    logger.info("Validation passed: response time is more than {} {}", time, unit);
                    break;
                case "EQUALS":
                    Assert.assertTrue("The response time should be equal to " + time + " " + unit,
                            responseTime == time);
                    logger.info("Validation passed: response time is equal to {} {}", time, unit);
                    break;
                case "LESS":
                    Assert.assertTrue("The response time should be less than " + time + " " + unit,
                            responseTime < time);
                    logger.info("Validation passed: response time is less than {} {}", time, unit);
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported comparator: " + comparator + ". Supported values are MORE, EQUALS, LESS.");
            }
        } catch (IllegalArgumentException e) {
            logger.error("Error: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("An unexpected error occurred during response time validation: {}", e.getMessage());
            throw e;
        }
    }

    /**
     * Validates that the number of currency pairs returned by the API matches the expected count.
     *
     * @param expectedCount The expected number of currency pairs.
     */
    @Then("validate {int} currency pairs are returned by the API")
    public void validateCurrencyPairsAreReturnedByTheAPI(int expectedCount) {
        logger.info("Method validateCurrencyPairsAreReturnedByTheAPI called with expectedCount: {}", expectedCount);
        try {
            int actualCount = this.ratesEndpoints.getCurrencyRatesResponseObject().getRates().size();
            logger.info("Actual count of currency pairs: {}", actualCount);
            Assert.assertEquals("The expected and actual count of currency pairs are the same", expectedCount, actualCount);
            logger.info("Validation successful: The expected and actual count of currency pairs match.");
        } catch (AssertionError e) {
            logger.error("Validation failed: Expected count: {}, Actual count: {}", expectedCount, this.ratesEndpoints.getCurrencyRatesResponseObject().getRates().size(), e);
            throw e;
        } catch (Exception e) {
            logger.error("An unexpected error occurred during validation: {}", e.getMessage());
            throw e;
        }
    }
}
