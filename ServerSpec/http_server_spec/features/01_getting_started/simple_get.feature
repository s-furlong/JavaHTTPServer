@simple-get @01-getting-started
Feature: Simple GET

  Scenario: Executing a GET request to /simple_get returns a 200
    Given I make a GET request to "/simple_get"
    Then my response should have status code 200
    And my response should have an empty body
