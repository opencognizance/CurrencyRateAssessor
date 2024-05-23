Feature: Currency Rates API

  As a user of the Currency Rates API
  I want to ensure that the API provides accurate currency rates
  So that I can use the currency rates data confidently in my application

  Scenario: To test whether the Currency Rates API Call is successfull
    Given Customer wants to get rates for "USD"
    When Customer wants to perform a GET operation to get rates
    Then Validate that the API call is successfull and status code is 200
    And Validate that all the prices have populated correctly

  Scenario: To verify the status for the Currency Rates API Call
    Given Customer wants to get rates for "USD"
    When Customer wants to perform a GET operation to get rates
    Then Validate that the API call is successfull and the status is "SUCCESS"

  Scenario: To verify the USD price against the AED
    Given Customer wants to get rates for "USD"
    When Customer wants to perform a GET operation to get rates
    Then Validate that the price of "AED" is range of 3.6 to 3.7

  Scenario: To verify that the API response time is not less than 3 seconds
    Given Customer wants to get rates for "USD"
    When Customer wants to perform a GET operation to get rates
    Then Validate that the response time is "more" than 3 "SECONDS"

  Scenario: To verify that the API response time is not less than 3 seconds
    Given Customer wants to get rates for "USD"
    When Customer wants to perform a GET operation to get rates
    Then validate 162 currency pairs are returned by the API

  Scenario: To verify that the API response matches the Schema
    Given Customer wants to get rates for "USD"
    When Customer wants to perform a GET operation to get rates
    Then validate whether the API Response matches with the schema