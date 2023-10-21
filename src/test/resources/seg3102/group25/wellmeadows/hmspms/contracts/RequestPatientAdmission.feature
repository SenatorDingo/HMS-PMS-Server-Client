Feature: Request Patient Admission

  @First @RequestPatientAdmission
  Scenario: Successful Patient Admission Request
    Given The Staff Member is logged in
    And The Staff Member is a Charge Nurse
    When The Staff Member initiates request patient admission
    And The HMS requests Division Identifier
    And The HMS asks for remaining admission information
    And The Staff Member submits correct request admission information
    Then The HMS assigns patient to ward for awaiting admission
    And The HMS notifies Ward Charge Nurse

  @Second @RequestPatientAdmission
  Scenario: Incorrect Information Provided
    Given The Staff Member is logged in
    And The Staff Member is a Charge Nurse
    When The Staff Member initiates request patient admission
    And The HMS requests Division Identifier
    And The HMS asks for remaining admission information
    And The Staff Member submits incorrect request admission information
    Then The HMS displays an incorrect request patient admission message

  @Third @RequestPatientAdmission
  Scenario: Patient Already Admitted
    Given The Staff Member is logged in
    And The Staff Member is a Charge Nurse
    When The Staff Member initiates request patient admission
    And The HMS requests Division Identifier
    And The HMS asks for remaining admission information
    And The Staff Member submits correct request admission information
    And The Patient is already admitted to division
    Then The HMS displays a patient already in ward error message