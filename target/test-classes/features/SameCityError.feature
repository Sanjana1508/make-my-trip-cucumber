Feature:Same city error
  Scenario Outline:
    Given User launch chrome browser
    When User opens url "https://www.makemytrip.com/"
    And User enters fromCity "<fromCity>"
    And User enters ToCity "<toCity>"
    Then User should see same city error message
    And User closes the browser
    Examples:
      | fromCity  | toCity |
      | Mumbai | Mumbai |