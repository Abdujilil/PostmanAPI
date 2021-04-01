Feature: Get collections

  @smoke
  Scenario: Get a collection
    Given request for creating new collection without requests inside
    When request for getting new collection
    Then verify the get response payload collection information

  Scenario Outline: Get a collection with request inside
    Given request for creating new collection with "<method>" request inside
    When request for getting new collection
    Then verify the get response payload request information
    And verify the get response payload collection information

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
  Scenario: Get all collections
    Given multiple collections are created
    When request for getting all collections
    Then verify all collections information