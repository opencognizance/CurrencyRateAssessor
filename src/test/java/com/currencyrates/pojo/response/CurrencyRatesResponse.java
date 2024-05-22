package com.currencyrates.pojo.response;

import io.cucumber.core.internal.com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;

public class CurrencyRatesResponse {
    private static final Logger logger = LogManager.getLogger(CurrencyRatesResponse.class);

    @JsonProperty("result")
    private String result;

    @JsonProperty("provider")
    private String provider;

    @JsonProperty("documentation")
    private String documentation;

    @JsonProperty("terms_of_use")
    private String termsOfUse;

    @JsonProperty("time_last_update_unix")
    private long timeLastUpdateUnix;

    @JsonProperty("time_last_update_utc")
    private String timeLastUpdateUtc;

    @JsonProperty("time_next_update_unix")
    private long timeNextUpdateUnix;

    @JsonProperty("time_next_update_utc")
    private String timeNextUpdateUtc;

    @JsonProperty("time_eol_unix")
    private long timeEolUnix;

    @JsonProperty("base_code")
    private String baseCode;

    @JsonProperty("rates")
    private HashMap<String, Double> rates;

    // Getters and Setters

    /**
     * Gets the result of the currency rate response.
     *
     * @return The result of the currency rate response.
     */
    public String getResult() {
        return result;
    }

    /**
     * Sets the result of the currency rate response.
     *
     * @param result The result of the currency rate response.
     */
    public void setResult(String result) {
        this.result = result;
    }

    /**
     * Gets the provider of the currency rate data.
     *
     * @return The provider of the currency rate data.
     */
    public String getProvider() {
        return provider;
    }

    /**
     * Sets the provider of the currency rate data.
     *
     * @param provider The provider of the currency rate data.
     */
    public void setProvider(String provider) {
        this.provider = provider;
    }

    // Other getters and setters with JavaDoc comments

    /**
     * Gets the rates of currencies against the base currency.
     *
     * @return The rates of currencies against the base currency.
     */
    public HashMap<String, Double> getRates() {
        return rates;
    }

    /**
     * Sets the rates of currencies against the base currency.
     *
     * @param rates The rates of currencies against the base currency.
     */
    public void setRates(HashMap<String, Double> rates) {
        this.rates = rates;
    }

    /**
     * Logs the details of the currency rates response.
     */
    public void logDetails() {
        logger.info("Currency Rates Response Details:");
        logger.info("Result: {}", result);
        logger.info("Provider: {}", provider);
        logger.info("Documentation: {}", documentation);
        logger.info("Terms of Use: {}", termsOfUse);
        logger.info("Time Last Update (Unix): {}", timeLastUpdateUnix);
        logger.info("Time Last Update (UTC): {}", timeLastUpdateUtc);
        logger.info("Time Next Update (Unix): {}", timeNextUpdateUnix);
        logger.info("Time Next Update (UTC): {}", timeNextUpdateUtc);
        logger.info("Time EOL (Unix): {}", timeEolUnix);
        logger.info("Base Code: {}", baseCode);
        logger.info("Rates: {}", rates);
    }
}
