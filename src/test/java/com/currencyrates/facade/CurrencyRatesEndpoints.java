package com.currencyrates.facade;

import com.currencyrates.pojo.response.CurrencyRatesResponse;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Facade class to interact with currency rates endpoints.
 */
public class CurrencyRatesEndpoints {
    private static Logger logger =  LogManager.getLogger(CurrencyRatesEndpoints.class);
    private static final String BASE_URL = "https://open.er-api.com/v6/latest/";
    private RequestSpecification request;
    private Response response;

    /**
     * Constructor to initialize the REST API client.
     */
    public CurrencyRatesEndpoints(){
        // Set base URL for REST calls
        RestAssured.baseURI = BASE_URL;
        // Initialize request specification
        this.request = RestAssured.given().contentType("application/json");
    }

    /**
     * Retrieves the currency rates against a specific currency.
     * @param currency The currency code to retrieve rates against.
     */
    public void getRateAgainstCurrency(String currency){
        // Send GET request to retrieve rates against the specified currency
        this.response = this.request.when().get("/" + currency);
        // Log the pretty-printed response body
        logger.info("Response Body Pretty Print :: " + this.response.prettyPrint());
    }

    /**
     * Gets the response from the latest API call.
     * @return The response object.
     */
    public Response getResponse(){
        // Log the response body
        logger.info("Response Body :: " + this.response.getBody().toString());
        return this.response;
    }

    /**
     * Deserializes the response JSON into a CurrencyRatesResponse object.
     * @return The CurrencyRatesResponse object representing the response.
     */
    public CurrencyRatesResponse getCurrencyRatesResponseObject(){
        // Extract JSON from the response
        String json = this.response.getBody().toString();
        CurrencyRatesResponse currencyRatesResponse = null;
        try {
            // Deserialize JSON into CurrencyRatesResponse object
            ObjectMapper objectMapper = new ObjectMapper();
            currencyRatesResponse = objectMapper.readValue(json, CurrencyRatesResponse.class);
            // Log the details of the response
            currencyRatesResponse.logDetails();
        } catch (Exception e) {
            // Log error if deserialization fails
            logger.error(e.getMessage());
        }
        return currencyRatesResponse;
    }
}

