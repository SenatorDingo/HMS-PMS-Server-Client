Feature: Admit Patient

  @First @AdmitPatient
  Scenario: Successful Patient Admission
    Given The Staff Member is logged in
    And The Staff Member is a Charge Nurse
    And Consult Patient File
    When The Staff Member initiates Patient Admittance
    And The HMS request room and bed number
    And The Staff browses and selects or enters room and bed number
    And The Staff Member submits room and bed number
    And The HMS asks for remaining admission information
    And The Staff Member submits requested information
    Then The HMS admits the Patient

  @Second @AdmitPatient
  Scenario: Complete Division
    Given The Staff Member is logged in
    And The Staff Member is a Charge Nurse
    And Consult Patient File
    When The Staff Member initiates Patient Admittance
    And The Division is complete
    Then The HMS notifies the Staff Member that the division is complete
    And The HMS displays Request Patient Admission