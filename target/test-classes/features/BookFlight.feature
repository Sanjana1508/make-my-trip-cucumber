Feature: Book flight
  Background:Opening a url and entering from city and to city
    Given User launch chrome browser
    When User opens url "https://www.makemytrip.com/"

  Scenario Outline: Books a flight
    And User enters fromCity "<fromCity>"
    And User enters ToCity "<toCity>"
    And User selects date of departure "<date>"
    And Users selects senior citizen
    When User clicks on search
    Then title is "<title>"
    When User selects Air India
    Then Flight results are Air India
    And User closes the browser
    Examples:
      | fromCity  | toCity | title | date |
      | Mumbai | Delhi | Flights from Mumbai to New Delhi | 14-09-2021 |
      | Chennai | Bengaluru | Flights from Chennai to Bengaluru | 20-08-2021 |

  Scenario Outline:Select round trip
    And User enters fromCity "<fromCity>"
    And User enters ToCity "<toCity>"
    And User selects date of departure "<date>"
    When User clicks round trip
    Then User should see next day
    And User closes the browser
    Examples:
      | fromCity | toCity | date       |
      | Mumbai   | Delhi  | 14-09-2021 |

    Scenario Outline: Select multicity
      And User enters fromCity "<fromCity>"
      And User enters ToCity "<toCity>"
      And User selects date of departure "<date>"
      And User selects multicity
      And User enters another from city "<anotherFromCity>"
      And User enters another to city "<anotherToCity>"
      And User selects date of departure "<date>"
      When User clicks on search
      Then User should see trip1 details as "<trip1Details>"
      Then User should see trip2Details as "<trip2Details>"
      And User closes the browser
      Examples:
        | fromCity | toCity | date       | anotherFromCity | anotherToCity | trip1Details | trip2Details |
        | Mumbai   | Delhi  | 14-09-2021 | Pune            | Hyderabad     | New Delhi to Bengaluru | Pune to Hyderabad |






