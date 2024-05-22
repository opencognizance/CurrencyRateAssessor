package com.currencyrates.stepdefinitions;

import io.cucumber.java.en.Given;

public class CurrencyRatesStepDefs {
    private String currency;

    public CurrencyRatesStepDefs(){

    }

    @Given("Customer wants to get rates for {string}")
    public void customer_wants_to_get_rates(String currency){
        this.currency = currency;
    }


}
