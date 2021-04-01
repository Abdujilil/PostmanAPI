Feature: Delete collections

  @smoke
  Scenario: Delete a collection without request inside
    Given request for creating new collection without requests inside
    When request for deleting the collection
    Then verify the "collection" delete response

  Scenario Outline: Delete a collection with request inside
    Given request for creating new collection with "<method>" request inside
    When request for deleting the collection
    Then verify the "collection" delete response

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