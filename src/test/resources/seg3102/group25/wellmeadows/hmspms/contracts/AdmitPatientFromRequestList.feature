Feature: Admit Patient from Request List

  @First @AdmitPatientFromRequestList
  Scenario: Successful Patient Admission from Request List
    Given The Staff Member is logged in
    And The Staff Member is a Charge Nurse
    And There is a Patient on the Admission Request List
    When The Staff Member views Admission Request List
    And The Staff Member selects Patient from Admission Request List
    And The HMS displays the Patient Registration
    And The Staff Member invokes admission of the Patient
    Then initiate Admit Patient

  @Second @AdmitPatientFromRequestList
  Scenario: Unable to Admit Patient
    Given The Staff Member is logged in
    And The Staff Member is a Charge Nurse
    And There is a Patient on the Admission Request List
    When The Staff Member views Admission Request List
    And The Staff Member selects Patient from Admission Request List
    And The HMS displays the Patient Registration
    And The Patient Admission not possible
    Then The Staff Member denies Patient Admission
    And The HMS notifies Charge Nurse who requested admission