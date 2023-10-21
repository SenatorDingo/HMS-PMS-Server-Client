Feature: Update Patient File

  @First @UpdatePatientFile
  Scenario: Successful Patient File Update
    Given The Staff Member is logged in
    And The Staff Member is authorized
    When Consult Patient File
    And The Staff Member modifies the Patient information
    And The Staff Member submits the changes
    Then The HMS updates the Patient File

  @Second @UpdatePatientFile
  Scenario: Unsuccessful Patient File Update
    Given The Staff Member is logged in
    And The Staff Member is not authorized
    When Consult Patient File
    And The Staff Member modifies the Patient information
    Then The HMS displays a modification not allowed error message