Feature: Staff Log Out

  @First @StaffLogOut
  Scenario:
    Given The Staff Member is logged in
    When The Staff Member initiates Log Out
    Then The HMS displays a Log Out acknowledgment
    And The Staff Member is not logged in