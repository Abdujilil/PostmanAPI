Feature: Delete environments

  @smoke
  Scenario: Delete environment without variable
    Given request for creating new environment without variables
    When request for deleting the environment
    Then verify the "environment" delete response

  Scenario:  Delete environment with variable
    Given request for creating new environment with variables
    When request for deleting the environment
    Then verify the "environment" delete response