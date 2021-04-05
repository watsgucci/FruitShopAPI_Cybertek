Feature: Add user end point test_CSVFile


  @wip
  Scenario: as a librarian I should be able to add a new user_CSVFile
    Given Librarian user is successfully logged in with login end point_CSVFile
    When Librarian adding an new user with an add user end point_CSVFile
    Then new user is successfully created with status code 200_CSVFile
