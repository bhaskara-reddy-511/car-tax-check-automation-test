Feature: This is to verify the vehicle information

  Scenario Outline: Verify Vehicle Information
    Given I read input file "<input_file>" to extract vehicle registration numbers
    When I Navigate to homepage
    And perform free car check to get the details
    Then compare the vehicle details returned from website with output file "<output_file>"
    Examples:
      |input_file    | output_file    |
      |car_input.txt | car_output.txt |