Feature: Register a new Staff to the System
  @First
  Scenario: Staff Member provides all the required information
    Given The Hospital Management System is ON
    Then The Staff Member is registered
  @Second
  Scenario: Staff Member provides incomplete information
    Given The Hospital Management System is ON
    Then The Staff Member is registered
  @Third
  Scenario: Staff Member User not found in the system
    Given The Hospital Management System is ON
    Then The Staff Member is registered