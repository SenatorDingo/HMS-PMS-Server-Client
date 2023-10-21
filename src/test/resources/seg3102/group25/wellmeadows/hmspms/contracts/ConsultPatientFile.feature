Feature: Consult Patient File

  @First @ConsultPatientFile
  Scenario: Successful Patient File Consultation
    Given The Staff Member is logged in
    And The Staff Member is authorized
    When The Staff Member initiates viewing Patient File
    And The HMS requests Patient identification number
    And The Staff Member inputs a valid Patient identification number
    Then The HMS displays the Patients File

  @Second @ConsultPatientFile
  Scenario: Unsuccessful Patient File Consultation
    Given The Staff Member is logged in
    And The Staff Member is authorized
    When The Staff Member initiates viewing Patient File
    And The HMS requests Patient identification number
    And The Staff Member inputs a invalid Patient identification number
    Then The HMS displays a Patient not found error message
