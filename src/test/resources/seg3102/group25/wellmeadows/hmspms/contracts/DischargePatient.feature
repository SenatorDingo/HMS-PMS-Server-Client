Feature: Discharge Patient

  @First
  Scenario: Successful Patient Discharge
    Given The Staff Member is logged in
    And The Staff Member is a Charge Nurse
    And Consult Patient File
    When The Staff Member selects Patient discharge
    And The Staff Member submits discharge for Patient
    Then The HMS updated bed availability
    And The HMS displays discharge information
    And The Patient is no longer admitted
    And The Patients bed is added to the division availability
    And discharge information slated to be sent to Patient