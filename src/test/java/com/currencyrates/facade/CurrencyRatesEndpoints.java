package com.currencyrates.facade;

import com.currencyrates.pojo.response.CurrencyRatesResponse;
import com.currencyrates.utils.GeneralUtils;
import com.fasterxml.jackson.databind.JsonNode;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

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
        String json = this.response.asString();
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

    /**
     * Retrieves the response time of an API call in the specified time unit.
     *
     * @param timeUnit the time unit in which the response time should be retrieved.
     *                 Supported values: "SECONDS", "MILLISECONDS", "MICROSECONDS".
     * @return the response time in the specified time unit. Returns 0 if the time unit is not supported.
     */
    public double getResponseTime(String timeUnit) {
        logger.info("Method getResponseTime called with time unit: {}", timeUnit);
        double time = this.response.getTimeIn(TimeUnit.MILLISECONDS);
        double responseTime = 0;
        try {
            switch (timeUnit) {
                case "SECONDS":
                    responseTime = time/1000;
                    logger.info("The response time is :: {} seconds", responseTime);
                    logger.info("The response timestamp is :: {} seconds", this.response.getTime());
                    break;
                case "MILLISECONDS":
                    responseTime = time;
                    logger.info("The response time is :: {} milliseconds", responseTime);
                    logger.info("The response timestamp is :: {} milliseconds", this.response.getTime());
                    break;
                case "MICROSECONDS":
                    responseTime = time * 1000;
                    responseTime = this.response.getTimeIn(TimeUnit.MICROSECONDS);
                    logger.info("The response time is :: {} microseconds", responseTime);
                    logger.info("The response timestamp is :: {} microseconds", this.response.getTime());
                    break;
                default:
                    logger.warn("Unsupported time unit: {}. Supported values are SECONDS, MILLISECONDS, MICROSECONDS.", timeUnit);
                    throw new IllegalArgumentException("Unsupported time unit: " + timeUnit + ". Supported values are SECONDS, MILLISECONDS, MICROSECONDS.");
            }
            logger.info("Response time in {}: {}", timeUnit, responseTime);
        } catch (Exception e) {
            logger.error("An error occurred while getting the response time: {}", e.getMessage());
        }
        return responseTime;
    }

    /**
     * Retrieves the JSON schema of the API response.
     *
     * @return The JSON schema as a JsonNode.
     */
    public JsonNode getResponseSchema() {
        logger.info("Method getResponseSchema called");
        try {
            String responseBody = this.response.getBody().asString();
            logger.debug("Response body received: {}", responseBody);

            JsonNode schema = GeneralUtils.getJsonSchema(responseBody);
            logger.info("JSON schema generated successfully: {}", schema.toPrettyString());

            return schema;
        } catch (Exception e) {
            logger.error("An error occurred while generating JSON schema: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to generate JSON schema", e);
        }
    }
}

