Feature: Visualize Division

  @First @VisualizeDivision
  Scenario: Successful Division Visualization
    Given The Staff Member is logged in
    And The Staff Member is a Charge Nurse
    When The Staff Member selects to Visualize Division
    And The HMS requests Division Identifier
    And The Staff Member submits correct division identifier
    Then The HMS displays information about division

  @Second @VisualizeDivision
  Scenario: Unsuccessful Division Visualization
    Given The Staff Member is logged in
    And The Staff Member is a Charge Nurse
    When The Staff Member selects to Visualize Division
    And The HMS requests Division Identifier
    And The Staff Member submits incorrect division identifier
    Then The HMS displays a division not found error message

