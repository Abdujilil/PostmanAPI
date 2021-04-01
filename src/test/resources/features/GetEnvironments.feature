Feature: Get environments

  @smoke
  Scenario: Get environment without variables
    Given request for creating new environment without variables
    When request for getting the environment
    Then verify the get response payload environment information

  @smoke
  Scenario: Get environment with variables
    Given request for creating new environment with variables
    When request for getting the environment
    Then verify the get response payload variable information
    And verify the get response payload environment information

  Scenario: Get environment without variables
    Given request for creating multiple new environment without variables
    When request for getting all environments
    Then verify the get response payload all environment information

  Scenario: Get environment with invalid info
    Given request for creating new environment without variables
    When request for getting the environment with invalid information
    Then verify the environment not found
