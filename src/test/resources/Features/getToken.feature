Feature: Add user end point test


  @wip
  Scenario: as a librarian I should be able to add a new user
    Given Librarian user is successfully logged in with login end point
    When Librarian adding an new user with an add user end point
    Then new user is successfully created with status code 200
