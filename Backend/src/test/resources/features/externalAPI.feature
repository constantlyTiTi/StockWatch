Feature: Testing Stock External API
  User should be able to get data from external API
  Scenario: Testing valid GET endpoint
    Given url "<externalUrl>"
    When method GET
    Then status 200