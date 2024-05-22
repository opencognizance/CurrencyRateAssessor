package com.currencyrates.facade;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CurrencyRatesEndpoints {
     private static final String BASE_URL = "https://open.er-api.com/v6/latest/";
     private RequestSpecification request;

     public CurrencyRatesEndpoints(){
         RestAssured.baseURI = BASE_URL;
         request = RestAssured.given();
     }

     public Response getRateAgainstCurrency(String currency){

     }
}
