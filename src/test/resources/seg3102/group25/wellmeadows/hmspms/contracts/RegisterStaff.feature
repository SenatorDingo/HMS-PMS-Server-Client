Feature: Register a new Staff to the System

  @First @StaffRegistration
  Scenario: Staff Member provides all the required information
    Given The HMS is ON
    And The Staff Member is not already registered
    When The Staff Member selects to register
    And The HMS asks for Staff Member information
    And The Staff Member provides all the required information
    Then The HMS registers the Staff Member
    And The HMS displays an acknowledgement message
    And The Staff Member is registered

  @Second @StaffRegistration
  Scenario: Staff Member provides incomplete information
    Given The HMS is ON
    And The Staff Member is not already registered
    When The Staff Member selects to register
    And The HMS asks for Staff Member information
    Then The HMS displays an incomplete staff information error message

  @Third @StaffRegistration
  Scenario: Staff Member User not found in the system
    Given The HMS is ON
    And The Staff Member is not already registered
    When The Staff Member selects to register
    And The HMS asks for Staff Member information
    And The Staff Member provides all the required information
    But The user is not found in the system
    Then The HMS displays an invalid uer error message
