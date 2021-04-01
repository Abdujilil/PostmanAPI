Feature: Update collections

  @smoke
  Scenario: Update a collection with PUT request
    Given request for creating new collection without requests inside
    When request for updating collection information
    Then verify the response payload collection information

  Scenario Outline: Update a collection including a request with PUT request
    Given request for creating new collection with "<method>" request inside
    When request for updating collection and "<new_method>" request information
    Then verify the response payload request information
    And verify the response payload collection information

    Examples:
      | method   | new_method |
      | GET      | VIEW       |
      | POST     | PROPFIND   |
      | DELETE   | UNLOCK     |
      | PUT      | LOCK       |
      | PATCH    | UNLINK     |
      | COPY     | LINK       |
      | HEAD     | PUT        |
      | OPTIONS  | PURGE      |
      | LINK     | PATCH      |
      | UNLINK   | COPY       |
      | PURGE    | OPTIONS    |
      | LOCK     | HEAD       |
      | UNLOCK   | DELETE     |
      | PROPFIND | POST       |
      | VIEW     | GET        |