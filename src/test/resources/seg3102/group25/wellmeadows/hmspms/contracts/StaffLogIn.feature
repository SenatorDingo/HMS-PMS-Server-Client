Feature: Staff Log In
  @First @StaffLogIn
  Scenario: Authorized Staff Member Log In
    Given The HMS is ON
    And The Staff Member is not logged in
    When The Staff Member submits login information
    And The HMS checks Staff Member for authorization
    Then The HMS displays Staff Member operations
    And The Staff Member is logged in
    And The Staff Member operation menu is displayed

  @Second @StaffLogIn
  Scenario: Unauthorized Staff Member Log In
    Given The HMS is ON
    And The Staff Member is not logged in
    When The Staff Member submits login information
    And The HMS checks Staff Member for authorization
    And The Staff Member is not authorized
    Then The HMS displays unauthorized access error message