Feature: Prescribe Medication

  @First
  Scenario: Successful Prescription
    Given The Staff Member is logged in
    And The Staff Member is a Doctor
    And Consult Patient File
    When The Staff Member requests to add prescription to Patient
    And The HMS requests for required prescription information
    And The Staff Member submits required prescription information
    Then The HMS records the prescription in Patient File

  @Second
  Scenario: Patient Not Assigned to Doctor
    Given The Staff Member is logged in
    And The Staff Member is a Doctor
    And Consult Patient File
    When The Staff Member requests to add prescription to Patient
    And The Selected Patient not assigned to current Doctor
    Then The HMS notifies Doctor that the patient is not under their case

  @Third
  Scenario: Incorrect Prescription Information
    Given The Staff Member is logged in
    And The Staff Member is a Doctor
    And Consult Patient File
    When The Staff Member requests to add prescription to Patient
    And The HMS requests for required prescription information
    And The Staff Member submits incorrect prescription information
    Then The HMS displays an incorrect prescription information error message
