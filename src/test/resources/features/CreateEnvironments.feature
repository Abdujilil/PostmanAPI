Feature: Create environments

  @smoke
  Scenario: Create environment without variables
    Given request for creating new environment without variables
    Then verify the response payload environment information

  @smoke
  Scenario: Create environment with variables
    Given request for creating new environment with variables
    Then verify the response payload environment information

  Scenario: Create environment with empty environment name (negative)
    Given request for creating new environment without environment name
    Then verify the "malformed Request Error" response information


  Scenario: Create environment with duplicated variable name (negative)
    Given request for creating new environment with duplicated variables
    Then verify the "invalid Params Error" response information

  Scenario: Create environment without Json body
    Given request for creating new environment without Json body
    Then verify the "malformed Request Error" response information