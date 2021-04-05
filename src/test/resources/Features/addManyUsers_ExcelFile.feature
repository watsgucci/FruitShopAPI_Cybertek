Feature: Add user end point test_ExcelFile


  @wip
  Scenario: as a librarian I should be able to add a new user_ExcelFile
    Given Librarian user is successfully logged in with login end point_ExcelFile
    When Librarian adding an new user with an add user end point_ExcelFile
    Then new user is successfully created with status code 200_ExcelFile
