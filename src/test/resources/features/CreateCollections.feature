Feature: Create collections

  @smoke
  Scenario: Create new collection without requests inside
    Given request for creating new collection without requests inside
    Then verify the response payload collection information

  Scenario Outline: Create new collection with <method> request inside  part one
    Given request for creating new collection with "<method>" request inside
    Then verify the response payload request information
    And verify the response payload collection information

    Examples:
      | method   |
      | GET      |
      | POST     |
      | DELETE   |
      | PUT      |
      | PATCH    |
      | COPY     |
      | HEAD     |
      | OPTIONS  |
      | LINK     |
      | UNLINK   |
      | PURGE    |
      | LOCK     |
      | UNLOCK   |
      | PROPFIND |
      | VIEW     |

  @smoke
  Scenario: Create new collection with invalid info
    Given request for creating new collection with invalid information
    Then verify the "malformed Request Error" response information