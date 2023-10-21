Feature: Register Patient

  @First @RegisterPatient
  Scenario: Successful Patient Registration
    Given The Staff Member is logged in
    And The Staff Member is authorized
    When The Staff Member initiates Patient Registration
    And The HMS request Patient information
    And The Staff Member submits patient information
    And The information provided is complete
    Then The HMS issues new identification for Patient
    And The HMS registers the Patient

  @Second @RegisterPatient
  Scenario: Unsuccessful Patient Registration
    Given The Staff Member is logged in
    And The Staff Member is authorized
    When The Staff Member initiates Patient Registration
    And The HMS request Patient information
    And The Staff Member submits patient information
    And The information provided is incomplete
    Then The HMS displays an incomplete patient information error message
