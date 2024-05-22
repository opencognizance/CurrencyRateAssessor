package com.currencyrates.stepdefinitions;

import com.currencyrates.facade.CurrencyRatesEndpoints;
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
    @Then("Validate that the API call is successful")
    public void validateThatTheAPICallIsSuccessfull() {
        logger.info("Method validateThatTheAPICallIsSuccessfull called");
        try {
            int statusCode = this.ratesEndpoints.getResponse().statusCode();
            logger.info("Received status code: {}", statusCode);
            Assert.assertEquals("The status code should be 200 for the response", statusCode, 200);
            logger.info("API call validation successful. Status code is 200.");
        } catch (AssertionError e) {
            logger.error("API call validation failed. Expected status code 200 but received {}", this.ratesEndpoints.getResponse().statusCode());
            throw e;
        } catch (Exception e) {
            logger.error("An unexpected error occurred during API call validation: {}", e.getMessage());
            throw e;
        }
    }
}
